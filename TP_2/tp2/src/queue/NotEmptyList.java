package queue;

import java.util.*;

public class NotEmptyList extends Queue {

    Status previous;
    NotEmptyList( Status previous, ArrayList<Object> queue ){
        this.previous = previous;
        this.queue = queue;
    }

    @Override
    public void createStatus() {}

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Status previousStatus(){
        return previous;
    }

    @Override
    public Object take(){
        Object head = this.head();
        queue.remove(0);
        return head;
    }

    @Override
    public Object head(){
        return queue.get(0);
    }
}
