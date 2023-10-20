package tp3;

public class Missile extends Instructions {

    public void work( Nemo nemo ){
        nemo.releaseMissile();
    }

    public boolean letter( char order ){
        return order == 'm';
    }
}
