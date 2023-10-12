package tp3;

public class North extends Orientation {

    public int goRight(){
        return 1;
    }

    public int goLeft(){
        return 3;
    }

    public void changePosition(){
        Nemo.coordinates.addYCoo();
    }

    public int getDirection(){
        return 0;
    }
}
