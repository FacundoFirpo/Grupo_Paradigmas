package queue;

public abstract class Status {

    public abstract boolean isEmpty();

    public abstract Status previousStatus();

    public abstract Object take();

    public abstract Object head();
}
