package tp3;

public class Right extends Instructions {

    public void work( Nemo nemo ){
        nemo.goRight();
    }

    public boolean letter( char order ){
        return order == 'r';
    }
}
