package tp3;
//
public class West extends Orientation{

    public int changeDirection( char order ){
        if (order == 'r') {
            return 0;
        } return 2;
    }

    public void changePosition( char order ){
        Nemo.xPosition--;
    }

    public int getDirection() {
        return 3;
    }
}
