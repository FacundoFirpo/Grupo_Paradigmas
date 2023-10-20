package tp3;

public class Bottom extends Depth {


    public static final String ERRORCAPSULE = "Nemo can't release the capsule. EXPLOSION!!!";
    private Depth previous;
    private int depth;

    public Bottom( Depth currentDepth, int depth ){
        previous = currentDepth;
        this.depth = depth + 1;
    }
    public boolean isOnSurface(){
        return false;
    }

    public Depth goDown(){
        return new Bottom( this, depth );
    }

    public Depth goUp(){
        return previous;
    }

    public void releaseMissile(){
        throw new RuntimeException( ERRORCAPSULE );
    }

    public int getDepth(){
        return depth;
    }
}
