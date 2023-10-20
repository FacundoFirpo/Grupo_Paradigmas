package tp3;

public class East extends Orientation {

    public Orientation goRight(){
        return new South();
    }

    public Orientation goLeft(){
        return new North();
    }

    public void changePosition( Nemo nemo ){
        nemo.addXCoo();
    }

    public String getOrientation() {
        return "East";
    }
}
