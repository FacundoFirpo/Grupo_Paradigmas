package tp3.orientation;

import tp3.Nemo;
import tp3.orientation.Orientation;

public class South extends Orientation {

    public int changeDirection( char order ){
        if (order == 'r') {
            return 3;
        } return 1;
    }

    public void changePosition( char order ){
        Nemo.coordinates.subYCoo();
    }

    public int getDirection() {
        return 2;
    }
}
