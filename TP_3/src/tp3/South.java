package tp3;

public class South extends Nemo{

    public void changeDirection( char order ){
        if (order == 'r') {
            direction = new West();
        } else if (order == 'l') {
            direction = new East();
        }
    }
}
