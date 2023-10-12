package tp3;

public class Down extends Instructions {

    public void work(){
        Nemo.depth.add( Nemo.depth.get( Nemo.depth() ).goDown()  );
    }
}
