package tp3;

import tp3.Instructions;

public class Right extends Instructions {

    public void work( Nemo nemo ){
        nemo.goRight();
    }

    public boolean letter( char order ){
        return order == 'r';
    }
}
