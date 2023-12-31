package tp3;

public abstract class Depth {

    public abstract boolean isOnSurface();

    public abstract Depth goDown();

    public abstract Depth goUp();

    public abstract void releaseMissile();

    public abstract int getDepth();
}
