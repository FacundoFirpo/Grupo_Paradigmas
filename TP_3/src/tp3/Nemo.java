package tp3;


import java.util.*;

public class Nemo {
    public Depth depth;
    public Coordinates coordinates;
    public Orientation orientation;

    public Nemo(){
        depth = new Surface();
        coordinates = new Coordinates( 0, 0 );
        orientation = new North();
    }
    public Nemo(int x, int y, Orientation startingOrientation ) {
        depth = new Surface();
        coordinates = new Coordinates( x, y );
        orientation = startingOrientation;
    }

    public boolean isOnSurface() {
        return depth.isOnSurface();
    }

    public int[] getPosition() {
        int[] position = { coordinates.getX(), coordinates.getY(), depth.getDepth() };
        return position;
    }

    public void move( String instruction ) {

        instruction.chars()
                .forEach( order -> move( (char) order ) );
    }

    public void move( char order ) {
        Instructions.instructionFor( order,this );
    }

    public String getOrientation() {
        return orientation.getOrientation();
    }

    public void goRight(){
        orientation = orientation.goRight();
    }

    public void goLeft(){
        orientation = orientation.goLeft();
    }

    public void goUp(){
        depth = depth.goUp();
    }

    public void goDown(){
        depth = depth.goDown();
    }

    public void releaseMissile(){
        depth.releaseMissile();
    }

    public void changePosition(){
        orientation.changePosition( this );
    }

    public void addYCoo(){
        coordinates.addYCoo();
    }

    public void subYCoo(){
        coordinates.subYCoo();
    }

    public void addXCoo(){
        coordinates.addXCoo();
    }

    public void subXCoo(){
        coordinates.subXCoo();
    }
}
