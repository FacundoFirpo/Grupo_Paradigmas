module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Point
import City
import Quality
import Link


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

cA = newC "Nottingham" (newP 5 8)
cB = newC "BS AS" (newP 10 40)
qA = newQ "Pro" 5 1.0
cC = newC "Udesa" (newP 8 3)
lA = newL cA cB qA
lB = newL cB cC qA
tA = newT [lA, lB]
lC = newL cA cC qA
