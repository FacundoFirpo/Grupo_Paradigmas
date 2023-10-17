package tp3;

public class Up extends Instructions {

    public void work(){
        Nemo.depth.get( Nemo.depth() ).goUp();
        Nemo.depth.remove( Nemo.depth() );
    }
}
