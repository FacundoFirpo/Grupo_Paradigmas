package tp3;

import tp3.Instructions;

public class Left extends Instructions {

    public void work( Nemo nemo ){
        nemo.goLeft();
    }

    public boolean letter( char order ){
        return order == 'l';
    }
}
