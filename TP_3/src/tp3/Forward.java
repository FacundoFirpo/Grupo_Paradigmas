package tp3;

public class Forward extends Instructions {

    public void work( Nemo nemo ){
        nemo.changePosition();
    }

    public boolean letter( char order ){
        return order == 'f';
    }
}
