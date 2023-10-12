package tp3;

import tp3.coordinates.*;
import tp3.orientation.*;
import tp3.depth.*;
import java.util.*;


public class Nemo {
    public static ArrayList<Depth> depth;
    public static Coordinates coordinates;
    public static Orientation orientation;
    public static Orientation[] orientations = { new North(), new East(), new South(), new West() };

    public static final String ERRORSURFACE = "Nemo is on the surface";
    public static final String ERRORDEPTH = "Nemo is too deep";
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
        if ( order == 'u' || order == 'd' ){
            changeDepth( order );
        }
        if ( order == 'r' || order == 'l' ){
            changeDirection( order );
        }
        if ( order == 'f' ){
            changePosition( order );
        }
        if ( order == 'm'){
            releaseCapsule();
        }
    }

    public int getDirection() {
        return orientation.getDirection();
    }

    public void changeDepth( char order ){
        if (order == 'd') {
            depth.add( depth.get( depth() ).goDown()  );
        } else {
            depth.get( depth() ).goUp();
            depth.remove( depth() );
        }
    }

    public void changeDirection( char order ){
        orientation = orientations[ orientation.changeDirection( order ) ];
    }

    public void changePosition( char order ){
        orientation.changePosition( order );
    }

    public void releaseCapsule() {
        depth.get( depth() ).releaseCapsule();
    }

    private int depth() {
        return depth.size() - 1;
    }
}
