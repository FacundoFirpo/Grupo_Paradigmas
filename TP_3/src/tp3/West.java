package tp3;

public class West extends Orientation {

    public Orientation goRight(){
        return new North();
    }

    public Orientation goLeft(){
        return new South();
    }

    public void changePosition( Nemo nemo ){
        nemo.subXCoo();
    }

    public String getOrientation() {
        return "West";
    }
}
