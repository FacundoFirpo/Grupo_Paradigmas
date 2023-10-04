package tp3;

public class Nemo {
    private int depth;
    private int xPosition;
    private int yPosition;
    private int direccion;
    public Nemo() {
        depth = 0;
        xPosition = 0;
        yPosition = 0;
        direccion = 0;
    }

    public boolean isOnSurface() {
        return depth == 0;
    }

    public int[] getPosition() {
        int[] position = {xPosition, yPosition, depth};
        return position;
    }

    public void move(String order) {
        for (int i = 0; i < order.length(); i++) {
            if (order.charAt(i) == 'd') {
                depth++;
            } else if (order.charAt(i) == 'u') {
                if (depth > 0) {
                    depth--;
                }
            }
        }
    }
    public void moveChar(char order) {
        if (order == 'd') {
            depth++;
        } else if (order == 'u') {
            if (depth > 0) {
                depth--;
            }
        }
    }
}
