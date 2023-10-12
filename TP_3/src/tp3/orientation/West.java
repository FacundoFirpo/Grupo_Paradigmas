package tp3.orientation;

import tp3.Nemo;
import tp3.orientation.Orientation;

public class West extends Orientation {

    public int changeDirection( char order ){
        if (order == 'r') {
            return 0;
        } return 2;
    }

    public void changePosition( char order ){
        Nemo.coordinates.subXCoo();
    }

    public int getDirection() {
        return 3;
    }
}
