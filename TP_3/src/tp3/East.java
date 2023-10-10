package tp3;

public class East extends Orientation{

    public int changeDirection( char order ){
        if (order == 'r') {
            return 2;
        } return 0;
    }

    public void changePosition( char order ){
        Nemo.xPosition++;
    }

    public int getDirection() {
        return 1;
    }
}
