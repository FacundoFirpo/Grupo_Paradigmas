package tp3;

public class East extends Nemo{

    public void changeDirection( char order ){
        if (order == 'r') {
            direction = new South();
        } else if (order == 'l') {
            direction = new North();
        }
    }
}
