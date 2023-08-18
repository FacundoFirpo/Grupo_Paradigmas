module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

import City
import Link
import Tunel
import Quality

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR = Reg -- newR cities links tunels = Reg cities links tunels

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities _ _) = flip (:) cities -- foundR (Reg cities _ _) city = city : cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg _ links _) = flip (:) links newL -- linkR (Reg _ links _) city1 city 2 =  newL city1 city2 : links 

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg _ _ tunels) = flip (:) tunels -- tunelR (Reg _ _ tunels) cities = cities : tunels

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) city1 city2 = elem True (map (connectsT city1 city2 each) tunels)

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas


delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora


availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades