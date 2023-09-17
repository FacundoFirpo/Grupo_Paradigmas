package queue;

public class Empty {

    public static String ERROR = "Queue is empty";

    public boolean isEmpty(){
        return true;
    }

    public Object head() {
        throw new Error( ERROR );
    }
}