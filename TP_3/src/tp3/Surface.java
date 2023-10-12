package tp3;

public class Surface extends Depth {
    public static final String ERRORSURFACE = "Nemo is on the surface";

    public boolean isOnSurface(){
        return true;
    }

    public Depth goDown(){
        return new Depth1();
    }

    public void goUp(){
        throw new RuntimeException( ERRORSURFACE );
    }

    public void releaseCapsule(){}
}
