package tp3;

import tp3.Bottom;
import tp3.Depth;

public class Depth1 extends Depth {

        public boolean isOnSurface(){
            return true;
        }

        public Depth goDown(){
            return new Bottom();
        }

        public void goUp(){}

        public void releaseCapsule(){}
}
