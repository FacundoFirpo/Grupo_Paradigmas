package tp3;

public class Down extends Instructions {

    public void work( Nemo nemo ){
        nemo.goDown();
    }

    public boolean letter( char order ){
        return order == 'd';
    }
}
