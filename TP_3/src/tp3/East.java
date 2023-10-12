package tp3;

public class East extends Orientation {

    public int goRight(){
        return 2;
    }

    public int goLeft(){
        return 0;
    }

    public void changePosition(){
        Nemo.coordinates.addXCoo();
    }

    public int getDirection() {
        return 1;
    }
}
