import Point
import City
import Quality
import Link

-- Point

p1 = newP 6 8
p2 = newP 10 3
p3 = newP (-2) 5

testP = [difP p1 p2 == 6.4031243, 
         difP p1 p3 == 8.5440035, 
         difP p2 p3 == 12.165525]

-- City

c1 = newC "Nottingham" p1
c2 = newC "BS AS" p2
c3 = newC "Udesa" p3

testC = [nameC c1 == "Nottingham",
         not (nameC c2 == "Nottingham"), 
         distanceC c1 c2 == difP p1 p2, 
         not (distanceC c1 c3 == difP p1 p2)] 

-- Quality

q1 = newQ "Pro" 5 0.5
q2 = newQ "Medium" 3 1

testQ = [capacityQ q1 == 5,
         not (capacityQ q2 == 5),
         delayQ q2 == 1,
         not (delayQ q1 == 1)]

-- Link

l1 = newL c1 c2 q1
l2 = newL c2 c3 q2

testL = [connectsL c1 l1,
         not (connectsL c1 l2),
         linksL c1 c2 l1,
         linksL c2 c1 l1,
         not (linksL c1 c3 l2),
         not (linksL c1 c2 l2),
         capacityL l1 == 5,
         not (capacityL l1 == 3),
         delayL l1 == 3.2015622,
         not (delayL l2 == 3)]