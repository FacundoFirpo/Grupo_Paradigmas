package tp3.orientation;

import tp3.Nemo;
import tp3.orientation.Orientation;

public class North extends Orientation {

    public int changeDirection( char order ){
        if (order == 'r') {
            return 1;
        } return 3;
    }

    public void changePosition( char order ){
        Nemo.coordinates.addYCoo();
    }

    public int getDirection() {
        return 0;
    }
}
