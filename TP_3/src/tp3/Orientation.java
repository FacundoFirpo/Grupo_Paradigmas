package tp3;

public abstract class Orientation {

    public abstract Orientation goRight();

    public abstract Orientation goLeft();

    public abstract void changePosition( Nemo nemo );

    public abstract String getOrientation();
}
