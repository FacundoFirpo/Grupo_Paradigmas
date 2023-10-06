package tp3;

public class North extends Nemo{

    public void changeDirection( char order ){
        if (order == 'r') {
            direction = new East();
        } else if (order == 'l') {
            direction = new West();
        }
    }
}
