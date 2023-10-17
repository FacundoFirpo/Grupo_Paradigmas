package tp3;

public class Depth1 extends Depth {

        public boolean isOnSurface(){
            return false;
        }

        public Depth goDown(){
            return new Bottom();
        }

        public void goUp(){}

        public void releaseCapsule(){}
}
