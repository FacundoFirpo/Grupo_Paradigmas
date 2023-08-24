module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where
   
import Point
import City
import Quality

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

cA = newC "Nottingham" (newP 5 8)
cB = newC "BS AS" (newP 10 40)
cC = newC "Udesa" (newP 8 3)
qA = newQ "Pro" 5 1.0
lA = newL cA cB qA