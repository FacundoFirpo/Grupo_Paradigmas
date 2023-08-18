module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi 

norma :: Point -> Point -> Float
norma (Poi x1 y1) (Poi x2 y2) = sqrt (fromIntegral((x1 - x2) ^ 2 + (y1 - y2) ^ 2))

difP :: Point -> Point -> Float  -- distancia absoluta
difP = norma