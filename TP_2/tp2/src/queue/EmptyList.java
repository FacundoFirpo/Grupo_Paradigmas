package queue;

public class EmptyList extends Queue {

    private static String error = "Queue is empty";

    @Override
    public void createStatus() {}

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Status previousStatus(){
        return this;
    }

    @Override
    public Object take() {
        throw new Error( error );
    }

    @Override
    public Object head() {
        throw new Error( error );
    }

    @Override
    public int size() {
        return 0;
    }
}
