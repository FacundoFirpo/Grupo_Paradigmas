package queue;

import java.util.ArrayList;
import java.util.Arrays;

public class Queue {
	private ArrayList<Empty> queue = new ArrayList( Arrays.asList( new Empty() ) );

	public boolean isEmpty(){ return queue.get( 0 ).isEmpty(); }

	public Queue add( Object cargo ){
		queue.add( this.size() , new Item( cargo ) );
		return this;
	}

	public Object head(){
		return queue.get( 0 ).head();
	}

	public Object take(){
		Object item = this.head();
		queue.remove( 0 );
		return item;
	}

	public int size(){
		return queue.size() - 1;
	}

}