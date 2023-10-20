package tp3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class NemoTests {

    private static final String FAIL_MESSAGE = "Nemo should throw an error when trying to go up from the surface";

    @Test
    public void test01NewNemoIsOnSurface() {
        Nemo nemo = new Nemo( 0, 0, new North() );
        assertTrue( nemo.isOnSurface() );
    }

    @Test
    public void test02NoMovementWhenNoOrderGiven(){
        Nemo nemo = new Nemo( 0, 0, new North() );
        nemo.move(' ');
        assertArrayEquals( new int[]{0, 0, 0}, nemo.getPosition() );
        assertEquals( "North", nemo.getOrientation() );
    }

    @Test
    public void test03NemoInitiatorWorks(){
        Nemo nemo = new Nemo( -5, 19, new South() );
        assertArrayEquals( new int[]{-5, 19, 0}, nemo.getPosition() );
        assertEquals( "South", nemo.getOrientation() );
    }

    @Test
    public void test04NemoDivesWhenGivenCharOrder(){
        Nemo nemo = new Nemo( 0, 0, new North() );
        nemo.move('d');
        assertArrayEquals( new int[]{0,0,1}, nemo.getPosition() );
    }

    @Test
    public void test05NemoDivesCorrectlyWhenGivenOrder(){
        Nemo nemo = new Nemo( 0, 0, new North() );
        nemo.move("dddu");
        assertArrayEquals( new int[]{0,0,2}, nemo.getPosition() );
    }

    @Test
    public void test06NemoCantGoUpIfOnSurface() {
        Nemo nemo = new Nemo( 0, 0, new North() );
        nemo.move('u');
        assertArrayEquals( new int[]{0,0,0}, nemo.getPosition() );
    }

    @Test
    public void test08NemoTurnsRight(){
        Nemo nemoN = new Nemo( 0, 0, new North() );
        Nemo nemoE = new Nemo( 0, 0, new East() );
        Nemo nemoS = new Nemo( 0, 0, new South() );
        Nemo nemoW = new Nemo( 0, 0, new West() );
        nemoN.move('r');
        nemoE.move('r');
        nemoS.move('r');
        nemoW.move('r');
        assertEquals( "East", nemoN.getOrientation() );
        assertEquals( "South", nemoE.getOrientation() );
        assertEquals( "West", nemoS.getOrientation() );
        assertEquals( "North", nemoW.getOrientation() );
    }

    @Test
    public void test09NemoTurnsLeft(){
        Nemo nemoN = new Nemo( 0, 0, new North() );
        Nemo nemoE = new Nemo( 0, 0, new East() );
        Nemo nemoS = new Nemo( 0, 0, new South() );
        Nemo nemoW = new Nemo( 0, 0, new West() );
        nemoN.move('l');
        nemoE.move('l');
        nemoS.move('l');
        nemoW.move('l');
        assertEquals( "West", nemoN.getOrientation() );
        assertEquals( "North", nemoE.getOrientation() );
        assertEquals( "East", nemoS.getOrientation() );
        assertEquals( "South", nemoW.getOrientation() );
    }

    @Test
    public void test10NemoMovesForward(){
        Nemo nemoN = new Nemo( 0, 0, new North() );
        Nemo nemoE = new Nemo( 0, 0, new East() );
        Nemo nemoS = new Nemo( 0, 0, new South() );
        Nemo nemoW = new Nemo( 0, 0, new West() );
        nemoN.move('f');
        nemoE.move('f');
        nemoS.move('f');
        nemoW.move('f');
        assertArrayEquals( new int[]{0,1,0}, nemoN.getPosition() );
        assertArrayEquals( new int[]{1,0,0}, nemoE.getPosition() );
        assertArrayEquals( new int[]{0,-1,0}, nemoS.getPosition() );
        assertArrayEquals( new int[]{-1,0,0}, nemoW.getPosition() );
    }

    @Test
    public void test11WhenNemoReleasesMissileNothingChanges(){
        Nemo nemo = new Nemo( 10, -24, new East() );
        nemo.move('m');
        assertArrayEquals( new int[]{10,-24,0}, nemo.getPosition() );
        assertEquals( "East", nemo.getOrientation() );
    }

    @Test
    public void test12NemoCanReleaseMissileInShallowWater(){
        Nemo nemo = new Nemo( 0, 0, new North() );
        nemo.move("dm");
        assertArrayEquals( new int[]{0,0,0}, nemo.getPosition() );
    }
    
    @Test
    public void test13NemoCantReleaseMissile(){

    }

    private void assertThrowsLike( Executable executable, String error ) {
        assertEquals(error,
                assertThrows(RuntimeException.class, executable, FAIL_MESSAGE)
                        .getMessage());
    }
}
