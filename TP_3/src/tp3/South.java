package tp3;

import tp3.Nemo;
import tp3.Orientation;

public class South extends Orientation {

    public int goRight(){
        return 3;
    }

    public int goLeft(){
        return 1;
    }

    public void changePosition(){
        Nemo.coordinates.subYCoo();
    }

    public int getDirection() {
        return 2;
    }
}
