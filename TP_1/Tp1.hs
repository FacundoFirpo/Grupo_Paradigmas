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
newC = Cit -- newC name coo = Cit name coo

nameC :: City -> String
nameC (Cit name _) = name 

distanceC :: City -> City -> Float
distanceC (Cit _ coo1) (Cit _ coo2) = difP coo1 coo2

-----------------

module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ name capacity delay | capacity < 1 = error"La capacidad debe ser mayor o igual a 1"
                         | delay < 0 = error"El delay no puede ser menor a 0"
                         | otherwise = Qua name capacity delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ cap _) = cap

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay

-------------------

module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin -- newL city1 city2 quality = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 _) = city == city1 || city == city2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 (Lin start finish _) = (start == city1 && finish == city2) || (start == city2 && finish == city1)

capacityL :: Link -> Int -- la capacidad del link
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float    -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 quality) = delayQ quality * distanceC city1 city2

-------------------

module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun -- newT links = Tun links

firstCityInT :: City -> Tunel -> Bool
firstCityInT city (Tun links) = connectsL city (head links) && not (connectsL city (head (tail links)))

lastCityInT :: City -> Tunel -> Bool
lastCityInT city (Tun links) = connectsL city (last links) && not (connectsL city (last (init links)))

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conecta estas dos ciudades distintas
connectsT city1 city2 tun = firstCityInT city1 tun && lastCityInT city2 tun || firstCityInT city2 tun && lastCityInT city1 tun

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links)

-------------------

module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city 
      | city `elem` cities = error"Esa ciudad ya existe"
      | nameC city `elem` map nameC cities = error"Una ciudad con ese nombre ya existe"
      | 0 `elem` map (distanceC city) cities = error"Ya existe una ciudad en ese punto"
      | otherwise = Reg (cities ++ [city]) links tunels

existingLink :: [Link] -> City -> City -> Bool -- indica si existe un link entre dos ciudades
existingLink links city1 city2 = any (linksL city1 city2) links

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality
    | city1 `notElem` cities = error"La primera ciudad no existe"
    | city2 `notElem` cities = error"La segunda ciudad no existe"
    | existingLink links city1 city2 = error"Ese link ya existe"
    | otherwise = Reg cities (links ++ [link]) tunels
   where 
     link = newL city1 city2 quality

findCitiesLink :: [Link] -> City -> City -> Link -- se fija cual link de la region enlaza las dos ciudades
findCitiesLink [] city1 city2 = error"No existe un link entre esas ciudades"
findCitiesLink links city1 city2 | linksL city1 city2 (head links) = head links
                                 | otherwise = findCitiesLink (tail links) city1 city2

createLinksTunel :: [Link] -> [City] -> [Link] -- crea una lista de links para crear un tunel 
createLinksTunel links cities | length cities == 1 = []
                              | otherwise = findCitiesLink links (head cities) (cities !! 1) : createLinksTunel links (tail cities)

checkCapacityLinks :: Region -> [City] -> Bool -- indica si hay capacidad disponible en los links para crear un tunel
checkCapacityLinks (Reg cities links tunels) citiesTunel | length citiesTunel == 1 = True
                                                         | availableCapacityForR (Reg cities links tunels) (head citiesTunel) (citiesTunel !! 1) > 0 = checkCapacityLinks (Reg cities links tunels) (tail citiesTunel)
                                                         | otherwise = error"Alguno de los links utilizados no tiene capacidad disponible"

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) citiesTunel | checkCapacityLinks (Reg cities links tunels) citiesTunel = Reg cities links (tunels ++ [tunel])
                                             | otherwise = error"No se pudo crear el tunel"
    where
        tunel = newT (createLinksTunel links citiesTunel)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) city1 city2 = any (connectsT city1 city2) tunels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = any (linksL city1 city2) links

findTunel :: [Tunel] -> City -> City -> Tunel -- se fija cual tunel de la region conecta las dos ciudades
findTunel tunels city1 city2 | connectsT city1 city2 (head tunels) = head tunels
                             | otherwise = findTunel (tail tunels) city1 city2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg _ _ tunels) city1 city2 = delayT (findTunel tunels city1 city2)

count :: Eq a => a -> [a] -> Int -- cuenta las repeticiones de un elemento en una lista
count target = foldr (\each fold -> if target == each then fold +1 else fold) 0

usedCapacity :: Link -> [Tunel] -> Int -- indica la capacidad usada de un link
usedCapacity link tunels = count True (map (usesT link)tunels) 

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cities links tunels) city1 city2
    | city1 `notElem` cities || city2 `notElem` cities = error"Esas ciudades no existen en la region"
    | otherwise = capacityL link - usedCapacity link tunels
   where
      link = findCitiesLink links city1 city2
