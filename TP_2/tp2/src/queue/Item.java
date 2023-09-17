package queue;

public class Item extends Empty {

    public Object item;
    Item( Object cargo ){
        item = cargo;
    }

    public boolean isEmpty(){
        return false;
    }

    public Object head(){
        return item;
    }
}