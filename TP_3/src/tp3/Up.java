package tp3;

public class Up extends Instructions {

    public void work( Nemo nemo ){
        nemo.goUp();
    }

    public boolean letter( char order ){
        return order == 'u';
    }
}
