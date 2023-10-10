package tp3;

public class North extends Orientation{

    public int changeDirection( char order ){
        if (order == 'r') {
            return 1;
        } return 3;
    }

    public void changePosition( char order ){
        Nemo.yPosition++;
    }

    public int getDirection() {
        return 0;
    }
}
