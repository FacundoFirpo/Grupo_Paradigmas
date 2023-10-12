package tp3;

public class Capsule extends Instructions {

    public void work(){
        Nemo.depth.get( Nemo.depth() ).releaseCapsule();
    }
}
