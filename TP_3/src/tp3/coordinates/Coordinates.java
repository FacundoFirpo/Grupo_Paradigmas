package tp3.coordinates;

public class Coordinates {

    public static int xPosition;
    public static int yPosition;

    public Coordinates( int x, int y ){
        xPosition = x;
        yPosition = y;
    }

    public void addXCoo(){
        xPosition++;
    }

    public void addYCoo(){
        yPosition++;
    }

    public void subXCoo(){
        xPosition--;
    }

    public void subYCoo(){
        yPosition--;
    }
}
