package tp3;
//
public class South extends Orientation{

    public int changeDirection( char order ){
        if (order == 'r') {
            return 3;
        } return 1;
    }

    public void changePosition( char order ){
        Nemo.yPosition--;
    }

    public int getDirection() {
        return 2;
    }
}
