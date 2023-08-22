module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
   where

import City
import Link
import Tunel
import Quality


data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city 
      | city `elem` cities = error"Esa ciudad ya existe"
      | nameC city `elem` map nameC cities = error"Una ciudad con ese nombre ya existe"
      | 0 `elem` map (distanceC city) cities = error"Ya existe una ciudad en ese punto"
      | otherwise = Reg (cities ++ [city]) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 quality
    | city1 `notElem` cities = error"La primera ciudad no existe"
    | city2 `notElem` cities = error"La segunda ciudad no existe"
    | link `elem` links = error"Ese link ya existe"
    | otherwise = Reg cities (links ++ [link]) tunels
   where 
     link = newL city1 city2 quality

checkCitiesLink :: [Link] -> City -> City -> Link -- se fija cual link de la region enlaza las dos ciudades
checkCitiesLink [] city1 city2 = error"No existe un link entre esas ciudades"
checkCitiesLink links city1 city2 | linksL city1 city2 (head links) = head links
                                  | otherwise = checkCitiesLink (tail links) city1 city2

createTunel :: [Link] -> [City] -> [Link] -- crea una lista de links para crear un tunel 
createTunel links cities | length cities == 1 = []
                         | otherwise = checkCitiesLink links (head cities) (cities !! 1) : createTunel links (tail cities) 

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) citiesTunel | length citiesTunel == 1 = Reg cities links (tunels ++ [tunel])
                                             | availableCapacityForR (Reg cities links tunels) (head citiesTunel) (citiesTunel !! 1) > 0 = tunelR (Reg cities links tunels) (tail citiesTunel)
    where
        tunel = newT (init (createTunel links citiesTunel))

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
      link = checkCitiesLink links city1 city2