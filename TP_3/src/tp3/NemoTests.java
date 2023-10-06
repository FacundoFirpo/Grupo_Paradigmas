package tp3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class NemoTests {

    private static final String FAIL_MESSAGE = "Nemo should throw an error when trying to go up from the surface";

    @Test
    public void test01NewNemoIsOnSurface() {
        Nemo nemo = new Nemo();
        assertTrue( nemo.isOnSurface() );
        assertTrue( nemo.getPosition()[2] == 0 );
    }

    @Test
    public void test02NoMovementWhenNoOrderGiven(){
        Nemo nemo = new Nemo();
        nemo.move(" ");
        assertArrayEquals( new int[]{0, 0, 0}, nemo.getPosition() );
    }

    @Test
    public void test03NemoDivesWhenGivenCharOrder(){
        Nemo nemo = new Nemo();
        nemo.moveChar('d');
        assertArrayEquals( new int[]{0,0,1}, nemo.getPosition() );
    }

    @Test
    public void test04NemoDivesCorrectlyWhenGivenOrder(){
        Nemo nemo = new Nemo();
        nemo.move("dddu");
        assertArrayEquals( new int[]{0,0,2}, nemo.getPosition() );
    }

    @Test
    public void test05NemoCantGoUpIfOnSurface(){
        Nemo nemo = new Nemo();
        assertThrowsLike( () -> nemo.moveChar('u') , Nemo.ERRORSURFACE );
        assertThrowsLike( () -> nemo.move("dduuu") , Nemo.ERRORSURFACE );
    }

    @Test
    public void test06NemoCantGoTooDeep() {
        Nemo nemo = new Nemo();
        String d = "d";
        assertThrowsLike( () -> nemo.move( d.repeat( 11035 ) ), Nemo.ERRORDEPTH );
    }

    @Test
    public void test07NemoFacesNorthWhenCreated(){
        Nemo nemo = new Nemo();
        assertEquals( 0, nemo.getDirection() );
    }

    @Test
    public void test08NemoFacesEastWhenTurnedRight(){
        Nemo nemo = new Nemo();
        nemo.moveChar( 'r' );
        assertEquals( 1, nemo.getDirection() );
    }

    @Test
    public void test09NemoFacesWestWhenTurnedLeft(){
        Nemo nemo = new Nemo();
        nemo.moveChar( 'l' );
        assertEquals( 3, nemo.getDirection() );
    }

    @Test
    public void test10NemoFacesCorrectlyWhenMoreThan360RightTurn(){
        Nemo nemo = new Nemo();
        nemo.move("rrrrr");
        assertEquals( 1, nemo.getDirection() );
    }

    @Test
    public void test011NemoFacesCorrectlyWhenMoreThan360LeftTurn(){
        Nemo nemo = new Nemo();
        nemo.move("lllll");
        assertEquals( 3, nemo.getDirection() );
    }

    @Test
    public void test12NemoTurnsDivesCorrectlyWhenGivenMultipleOrders(){
        Nemo nemo = new Nemo();
        nemo.move("dddu");
        nemo.move("rrrl");
        nemo.moveChar('u');
        assertArrayEquals( new int[]{0,0,1}, nemo.getPosition() );
        assertEquals( 2, nemo.getDirection() );
    }

    @Test
    public void test13NemoMovesFowardWhenFacingNorth(){
        Nemo nemo = new Nemo();
        nemo.moveChar('f');
        assertArrayEquals( new int[]{0,1,0}, nemo.getPosition() );
    }
    @Test
    public void test14NemoMovesRightWhenFacingEast(){
        Nemo nemo = new Nemo();
        nemo.moveChar('r');
        nemo.moveChar('f');
        assertArrayEquals( new int[]{1,0,0}, nemo.getPosition() );
    }
    @Test
    public void test15NemoMovesLeftWhenFacingWest(){
        Nemo nemo = new Nemo();
        nemo.moveChar('l');
        nemo.moveChar('f');
        assertArrayEquals( new int[]{-1,0,0}, nemo.getPosition() );
    }

    @Test
    public void test16NemoMovesBackwardWhenFacingSouth(){
        Nemo nemo = new Nemo();
        nemo.move("rr");
        nemo.moveChar('f');
        assertArrayEquals( new int[]{0,-1,0}, nemo.getPosition() );
    }

    @Test
    public void test17NemoTurnsDivesMovesCorrectlyWhenGivenMultipleOrders(){
        Nemo nemo = new Nemo();
        nemo.move("dddu");
        nemo.move("rrrl");
        nemo.moveChar('u');
        nemo.moveChar('f');
        assertArrayEquals( new int[]{0,-1,1}, nemo.getPosition() );
        assertEquals( 2, nemo.getDirection() );
    }

    @Test
    public void test18NemoCanMoveAgainAfterTurning(){
        Nemo nemo = new Nemo();
        nemo.move("dddu");
        nemo.move("rrrl");
        nemo.move("u");
        nemo.move("f");
        nemo.moveChar( 'r');
        nemo.moveChar( 'f');
        assertArrayEquals( new int[]{-1,-1,1}, nemo.getPosition() );
    }

    @Test
    public void test19NemoReleasesCapsule(){
        Nemo nemo = new Nemo();
        nemo.moveChar('m');
    }

    @Test
    public void test20NemoCantReleaseCapsuleBelowDepth1(){
        Nemo nemo = new Nemo();
        assertThrowsLike( () -> nemo.move("dddm") , Nemo.ERRORCAPSULE );
    }

    private void assertThrowsLike( Executable executable, String error ) {
        assertEquals(error,
                assertThrows(RuntimeException.class, executable, FAIL_MESSAGE)
                        .getMessage());
    }
}
