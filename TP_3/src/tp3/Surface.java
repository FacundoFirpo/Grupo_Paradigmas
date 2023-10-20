package tp3;

public class Surface extends Depth {

    public boolean isOnSurface(){
        return true;
    }

    public Depth goDown(){
        return new Shallow();
    }

    public Depth goUp(){
        return new Surface();
    }

    public void releaseMissile(){}

    public int getDepth(){
        return 0;
    }
}
