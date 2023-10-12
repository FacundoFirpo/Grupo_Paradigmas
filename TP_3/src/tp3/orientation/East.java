package tp3.orientation;

import tp3.Nemo;
import tp3.orientation.Orientation;

public class East extends Orientation {

    public int changeDirection( char order ){
        if (order == 'r') {
            return 2;
        } return 0;
    }

    public void changePosition( char order ){
        Nemo.coordinates.addXCoo();
    }

    public int getDirection() {
        return 1;
    }
}
