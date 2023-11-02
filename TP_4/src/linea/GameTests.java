package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    @Test
    public void test01NewGameIsNotFinished() {
        Linea game = new Linea( 4, 4, 'C' );
        assertFalse( game.finished() );
    }

    @Test
    public void test02RedCanPlay() {
        Linea game = new Linea( 4, 4, 'C' );
        game.playRedAt( 1 );
        assertEquals( game.show(), "\n| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD34  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD3C  \uD83D\uDD3C  \uD83D\uDD3C  \uD83D\uDD3C |" );
    }

    @Test
    public void test03BlueCanPlay() {
        Linea game = new Linea( 4, 4, 'C' );
        game.playRedAt( 1 );
        game.playBlueAt( 2 );
        assertEquals( game.show(), "\n| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD34  \uD83D\uDD35  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD3C  \uD83D\uDD3C  \uD83D\uDD3C  \uD83D\uDD3C |" );
    }

    @Test
    public void test04BlueCantStart() {
        Linea game = new Linea( 4, 4, 'C' );
        assertThrowsLike( () -> game.playBlueAt( 1 ) );
    }

    @Test
    public void test05ChipsCanStack() {
        Linea game = new Linea( 4, 4, 'C' );
        game.playRedAt( 1 );
        game.playBlueAt( 1 );
        assertEquals( game.show(), "\n| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD35  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD34  \uD83D\uDD18  \uD83D\uDD18  \uD83D\uDD18 |\n" +
                "| \uD83D\uDD3C  \uD83D\uDD3C  \uD83D\uDD3C  \uD83D\uDD3C |" );
    }

    private void assertThrowsLike( Executable executable ) {
        assertEquals(Linea.ERRORTURNO,
                assertThrows(RuntimeException.class, executable, "Debería haber lanzado una excepción")
                        .getMessage());
    }
}
