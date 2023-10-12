package tp3.depth;

public class Depth1 extends Depth{

        public boolean isOnSurface(){
            return true;
        }

        public Depth goDown(){
            return new Bottom();
        }

        public void goUp(){}

        public void releaseCapsule(){}
}
