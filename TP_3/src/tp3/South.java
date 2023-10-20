package tp3;

public class South extends Orientation {

    public Orientation goRight(){
        return new West();
    }

    public Orientation goLeft(){
        return new East();
    }

    public void changePosition( Nemo nemo ){
        nemo.subYCoo();
    }

    public String getOrientation() {
        return "South";
    }
}
