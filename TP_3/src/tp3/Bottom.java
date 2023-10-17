package tp3;

public class Bottom extends Depth {

    public boolean isOnSurface(){
        return false;
    }

    public Depth goDown(){
        return new Bottom();
    }

    public void goUp(){}

    public void releaseCapsule(){
        throw new RuntimeException( Nemo.ERRORCAPSULE );
    }
}
