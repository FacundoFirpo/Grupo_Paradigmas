package tp3;

public class Nemo {
    private int depth;
    private int xPosition;
    private int yPosition;
    protected Nemo direction;

    public static final String ERRORSURFACE = "Nemo is on the surface";
    public static final String ERRORDEPTH = "Nemo is too deep";
    public static final String ERRORCAPSULE = "Nemo can't release the capsule";

    public Nemo() {
        depth = 0;
        xPosition = 0;
        yPosition = 0;
        direction = new North();
    }

    public boolean isOnSurface() {
        return depth == 0;
    }

    public int[] getPosition() {
        int[] position = {xPosition, yPosition, depth};
        return position;
    }

    public void move( String order ) {
        for (int i = 0; i < order.length(); i++){
            changeDepth( order.charAt(i) );
            changeDirection( order.charAt(i) );
            changePosition( order.charAt(i) );
            if ( order.charAt(i) == 'm'){
                releaseCapsule();
            }
        }
        if (direction < 0) {
            direction = direction + 4;
        }
    }

    public void moveChar( char order ) {
        changeDepth( order );
        changeDirection( order );
        changePosition( order );
        if ( order == 'm'){
            releaseCapsule();
        }
    }

    public Nemo getDirection() {
        return direction;
    }

    public void changeDepth( char order ){
        if (order == 'd') {
            if ( depth < 11034 ){
                depth++;
            } else {
                throw new RuntimeException( ERRORDEPTH );
            }
        } else if (order == 'u') {
            if (depth > 0) {
                depth--;
            } else {
                throw new RuntimeException( ERRORSURFACE );
            }
        }
    }

    public void changeDirection( char order ){
    }

    public void changePosition( char order ){
        if (order == 'f') {
            if (direction == 0) {
                yPosition++;
            } else if (direction == 1) {
                xPosition++;
            } else if (direction == 2) {
                yPosition--;
            } else if (direction == 3) {
                xPosition--;
            }
        }
    }

    public void releaseCapsule() {
        if (depth > 1) {
            throw new RuntimeException( ERRORCAPSULE );
        }
    }

}
