module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import City
import Link
import Data.Bool (Bool(True))

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