package tp3;

public class West extends Nemo{

    public void changeDirection( char order ){
        if (order == 'r') {
            direction = new North();
        } else if (order == 'l') {
            direction = new South();
        }
    }
}
