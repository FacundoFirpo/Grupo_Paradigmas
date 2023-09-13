package queue;

import java.util.*;
public class Queue extends Status{

	public ArrayList<Object> queue = new ArrayList<Object>();
	public static Status status;
	Queue(){
		createStatus();
	}

	@Override
	public Status previousStatus() {
		return null;
	}

	public void createStatus(){
		status = new EmptyList( );
	}

	public boolean isEmpty(){
		return status.isEmpty();
	}

	public Queue add( Object cargo ){
		status = new NotEmptyList( status, queue );
		queue.add( cargo );
		return this;
	}

	public Object take(){
		Object head = status.take();
		status = status.previousStatus( );
	    return head;
	}
	public Object head(){
		return status.head();
	}
	public int size(){
		return queue.size();
	}

}
