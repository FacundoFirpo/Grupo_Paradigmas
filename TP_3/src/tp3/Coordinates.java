package tp3;

public class Coordinates {

    public int xPosition;
    public int yPosition;

    public Coordinates( int x, int y ){
        xPosition = x;
        yPosition = y;
    }

    public int getX(){
        return xPosition;
    }

    public int getY(){
        return yPosition;
    }

    public void addXCoo(){ add( new Coordinates( 1, 0) ); }

    public void addYCoo(){
        add( new Coordinates( 0, 1) );
    }

    public void subXCoo(){
        sub( new Coordinates( 1, 0) );
    }

    public void subYCoo(){
        sub( new Coordinates( 0, 1) );
    }

    public void add( Coordinates coordinates ){
        xPosition += coordinates.getX();
        yPosition += coordinates.getY();
    }

    public void sub( Coordinates coordinates ){
        xPosition -= coordinates.getX();
        yPosition -= coordinates.getY();
    }
}
