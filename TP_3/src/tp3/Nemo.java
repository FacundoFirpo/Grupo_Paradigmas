package tp3;

import java.util.*;


public class Nemo {
    public static ArrayList<Depth> depth;
    public static Coordinates coordinates;
    public static Orientation orientation;
    public static Orientation[] orientations = { new North(), new East(), new South(), new West() };
    public static Instructions[] instructions = { new Forward(), new Right(), new Left(), new Up(), new Down(), new Capsule(), new Nothing() };

    public static final String ERRORSURFACE = "Nemo is on the surface";
    public static final String ERRORCAPSULE = "Nemo can't release the capsule";

    public Nemo(){
        depth = new ArrayList<>();
        depth.add( new Surface() );
        coordinates = new Coordinates( 0, 0 );
        orientation = orientations[0];
    }
    public Nemo( int x, int y) {
        depth = new ArrayList<>( );
        depth.add( new Surface() );
        coordinates = new Coordinates( x, y );
        orientation = orientations[0];
    }

    public boolean isOnSurface() {
        return depth.get( depth() ).isOnSurface();
    }

    public int[] getPosition() {
        int[] position = {coordinates.xPosition, coordinates.yPosition, depth() };
        return position;
    }

    public void move( String order ) {
        for (int i = 0; i < order.length(); i++){
            applyInstruction( order.charAt(i) );
        }
    }

    public void moveChar( char order ) {
        applyInstruction( order );
    }

    public void applyInstruction( char order ){
        new Instructions().doSomething( order );
    }

    public int getDirection() {
        return orientation.getDirection();
    }

    public static int depth() {
        return depth.size() - 1;
    }
}
