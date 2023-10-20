package tp3;

public class Shallow extends Depth {

        private int depth = 1;

        public boolean isOnSurface(){
            return false;
        }

        public Depth goDown(){
            return new Bottom( this, depth );
        }

        public Depth goUp(){ return new Surface();}

        public void releaseMissile(){}

        public int getDepth(){
            return depth;
        }
}
