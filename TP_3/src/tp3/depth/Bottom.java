package tp3.depth;

public class Bottom extends Depth{
    public static final String ERRORCAPSULE = "Nemo can't release the capsule";

    public boolean isOnSurface(){
        return true;
    }

    public Depth goDown(){
        return new Bottom();
    }

    public void goUp(){}

    public void releaseCapsule(){
        throw new RuntimeException( ERRORCAPSULE );
    }
}
