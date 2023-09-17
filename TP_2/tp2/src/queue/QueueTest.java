package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QueueTest {

  private static String ADDEDOBJECT = "Something";
  private static String FIRST = "First";
  private static String SECOND = "Second";
  private static String FAIL_MESSAGE = "Expected Error was not thrown.";
  private static Queue queue;

  @BeforeEach public void createQueue() { queue = new Queue(); }

  @Test public void test01QueueShouldBeEmptyWhenCreated() { assertTrue( queue.isEmpty() ); }

  @Test public void test02AddElementsToTheQueue() { assertFalse( queueWithOne().isEmpty() ); }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( ADDEDOBJECT, queueWithOne().head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() { assertTrue( queueAddOneTakeOne().isEmpty() ); }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( ADDEDOBJECT, queueWithOne().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithTwo();
    assertEquals( queue.take(), FIRST );
    assertEquals( queue.take(), SECOND );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( queueWithTwo().head(), FIRST );
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
    assertThrowsLike( () -> queue.take() );
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() { assertThrowsLike( () -> queueAddOneTakeOne().take() ); }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike( () -> queue.head() );
  }

  private Queue queueWithOne( ) {
    return queue.add( ADDEDOBJECT );
  }

  private Queue queueWithTwo(  ) {
    return queue.add( FIRST ).add( SECOND );
  }

  private Queue queueAddOneTakeOne() {
    Queue queue = queueWithOne();
    queue.take();
    return queue;
  }

  private void assertThrowsLike( Executable executable ) {
    assertEquals(Empty.ERROR,
            assertThrows(Error.class, executable, FAIL_MESSAGE)
                    .getMessage());
  }
}