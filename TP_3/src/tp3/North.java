package tp3;

public class North extends Orientation {

    public Orientation goRight(){
        return new East();
    }

    public Orientation goLeft(){
        return new West();
    }

    public void changePosition( Nemo nemo ){ nemo.addYCoo(); }

    public String getOrientation(){
        return "North";
    }
}
