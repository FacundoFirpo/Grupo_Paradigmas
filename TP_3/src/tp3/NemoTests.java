package tp3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NemoTests {
    @Test
    public void test01NewNemoIsOnSurface() {
        Nemo nemo = new Nemo();
        assertTrue(nemo.isOnSurface());
    }
    @Test
    public void test02NoMovementWhenNoOrderGiven(){
        Nemo nemo = new Nemo();
        nemo.move(" ");
        assertArrayEquals(new int[]{0, 0, 0}, nemo.getPosition());
    }
    @Test
    public void test03NemoMovesDownWhenGivenOrder(){
        Nemo nemo = new Nemo();
        nemo.move("dddu");
        assertArrayEquals(new int[]{0,0,2}, nemo.getPosition());
    }
    @Test
    public void test04NemoMovesWhenGivenCharOrder(){
        Nemo nemo = new Nemo();
        nemo.moveChar('d');
        assertArrayEquals(new int[]{0,0,1}, nemo.getPosition());
    }
    @Test
    public void test05NemoFacesNorthWhenCreated(){
        Nemo nemo = new Nemo();
        assertEquals("North", nemo.getdirection());
    }
}
