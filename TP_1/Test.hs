import Point
import City
import Quality
import Link
import Tunel
import Region

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
l3 = newL c1 c3 q1

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

-- Tunnel

t1 = newT [l1, l2]
t2 = newT [l1, l3]
t3 = newT [l2, l3]

testT = [connectsT c1 c3 t1,
         connectsT c3 c1 t1,
         not (connectsT c2 c3 t1),
         usesT l1 t1,
         not (usesT l3 t1),
         delayT t1 == 15.367087,
         not (delayT t2 == 6.4015627)] 

-- Region

r1 = tunelR (linkR (linkR (foundR (foundR (foundR newR c1) c2) c3) c1 c2 q1) c2 c3 q2) [c1, c2, c3]

testR = [connectedR r1 c1 c3,
         connectedR r1 c3 c1,
         not (connectedR r1 c2 c3),
         linkedR r1 c1 c2,
         not (linkedR r1 c1 c3),
         delayR r1 c1 c3 == 15.367087,
         delayR r1 c3 c1 == 15.367087,
         not (delayR r1 c1 c3 == 10),
         availableCapacityForR r1 c1 c2 == 4,
         not (availableCapacityForR r1 c2 c3 == 3)]
