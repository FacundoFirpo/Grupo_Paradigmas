--Telco

--Es una compañia que se dedica a comunicar las ciudades que se susbcriben a su servicio.
--Primero las ingresa al mapa de la región. 
--Luego establece vínculos entre ellas de cierta calidad y capacidad.
--Finalmente establece canales que conectan distintas ciudades ocupando una unidad de 
--capacidad por cada enlace recorrido.

--Para sostener este modelo se cuenta con las siguientes entidades:

module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi 

norma :: Point -> Point -> Float
norma (Poi x1 y1) (Poi x2 y2) = sqrt (fromIntegral((x1 - x2) ^ 2 + (y1 - y2) ^ 2))

difP :: Point -> Point -> Float  -- distancia absoluta
difP = norma
-----------------
module City ( City, newC, nameC, distanceC )
   where

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ coo1) (Cit _ coo2) = difP coo1 coo2
-----------------
module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ = Qua

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ cap _) = cap

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay
-------------------
module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL (Cit name _) (Lin city1 city2 _) = name == city1 || name == city2

cityPositionInL :: City -> Link -> Int
cityPositionInL (Cit name _) (Lin city1 city2 _) | name == city1 = 1
                                                 | name == city2 = 2
                                                 | otherwise = 0

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL (Cit name1 _) (Cit name2 _) (Lin city1 city2 _) = (name1 == city1 && name2 == city2) || (name1 == city2 && name2 == city1)

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ quality) = delayQ quality
-------------------
module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun -- newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun links) = (cityPositionInL city1 (head links) == 1 && cityPositionInL city2 (last links) == 2) || 
                                    (cityPositionInL city2 (head links) == 1 && cityPositionInL city1 (last links) == 2)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT (Lin city1 city2 _) (Tun links) = elem True (map (linksL city1 city2) links)

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links)
-------------------
module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

data Region = Reg [City] [Link] [Tunel]

newR :: Region
newR = Reg -- newR cities links tunels = Reg cities links tunels

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities _ _) = flip (:) cities -- foundR (Reg cities _ _) city = city : cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg _ links _) = flip (:) links newL -- linkR (Reg _ links _) city1 city2 quality =  newL city1 city2 quality : links 

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg _ _ tunels) = flip (:) tunels -- tunelR (Reg _ _ tunels) cities = cities : tunels

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) city1 city2 = elem True (map (connectsT city1 city2 each) tunels)

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = elem True (map (linksL city1 city2 each) links)

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg _ _ tunels) city1 city2 = sum (map (delayT each) tunels)

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg _ links _) city1 city2 | map (linksL city1 city2 each) links = capacityQ
