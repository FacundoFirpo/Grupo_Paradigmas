package tp3;

import tp3.Nemo;
import tp3.Orientation;

public class West extends Orientation {

    public int goRight(){
        return 0;
    }

    public int goLeft(){
        return 2;
    }

    public void changePosition(){
        Nemo.coordinates.subXCoo();
    }

    public int getDirection() {
        return 3;
    }
}
