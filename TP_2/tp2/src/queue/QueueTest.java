package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class QueueTest {

  public String addedObject = "Something";
  public String firstAddedObject = "First";
  public String secondAddedObject = "Second";

  @Test public void test01QueueShouldBeEmptyWhenCreated() { assertTrue( queueIsEmpty( createEmptyQueue() ) ); }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueIsEmpty( queueWithOne() ) );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( addedObject, queueWithOne().head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithOne();
    queue.take();
    assertTrue( queueIsEmpty( queue ) );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( addedObject, queueWithOne().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithTwo();
    assertEquals( queue.take(), firstAddedObject );
    assertEquals( queue.take(), secondAddedObject );
    assertTrue( queueIsEmpty( queue ) );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( queueWithTwo().head(), firstAddedObject );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithOne();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithTwo().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    catchTakeError( createEmptyQueue() );
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithOne();
    queue.take();
    catchTakeError( queue );
  }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    catchHeadError( createEmptyQueue() );
  }

  private Queue createEmptyQueue() {
    return new Queue();
  }

  private boolean queueIsEmpty( Queue queue ){
    return queue.isEmpty();
  }

  private Queue queueWithOne() {
    return createEmptyQueue().add( addedObject );
  }

  private Queue queueWithTwo() {
    return createEmptyQueue().add( firstAddedObject ).add( secondAddedObject );
  }

  private void catchTakeError( Queue queue ) {
    try {
      queue.take();
      fail( "Expected Error was not thrown." );
    } catch (Error e) {
      assertTrue( e.getMessage().equals( "Queue is empty" ) );
    }
  }

  private void catchHeadError( Queue queue ) {
    try {
      queue.head();
      fail( "Expected Error was not thrown." );
    } catch (Error e) {
      assertTrue( e.getMessage().equals( "Queue is empty" ) );
    }
  }
}