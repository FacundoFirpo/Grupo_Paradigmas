module City ( City, newC, nameC, distanceC )
   where

import Point

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit -- newC name coo = Cit name coo

nameC :: City -> String
nameC (Cit name _) = name 

distanceC :: City -> City -> Float
distanceC (Cit _ coo1) (Cit _ coo2) = difP coo1 coo2
