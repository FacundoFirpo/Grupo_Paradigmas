package tp3.depth;

public abstract class Depth {

    public abstract boolean isOnSurface();

    public abstract Depth goDown();

    public abstract void goUp();

    public abstract void releaseCapsule();
}
