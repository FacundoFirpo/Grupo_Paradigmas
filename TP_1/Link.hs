module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City
import Quality

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