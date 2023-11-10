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
        assertThrowsLike( () -> game.playBlueAt( 1 ), Turnos.ERRORTURNO );
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

    @Test
    public void test06RedCantPlayTwice() {
        Linea game = new Linea( 4, 4, 'C' );
        game.playRedAt( 1 );
        assertThrowsLike( () -> game.playRedAt( 1 ), Turnos.ERRORTURNO );
    }

    @Test
    public void test07BlueCantPlayTwice() {
        Linea game = new Linea( 4, 4, 'C' );
        game.playRedAt( 1 );
        game.playBlueAt( 1 );
        assertThrowsLike( () -> game.playBlueAt( 1 ), Turnos.ERRORTURNO );
    }

    @Test
    public void test08RedCantPlayOutOfBoard() {
        Linea game = new Linea( 4, 4, 'C' );
        assertThrowsLike( () -> game.playRedAt( 0 ), Linea.ERRORPOSICION );
        assertThrowsLike( () -> game.playRedAt( 5 ), Linea.ERRORPOSICION );
    }

    @Test
    public void test09BlueCantPlayOutOfBoard() {
        Linea game = new Linea( 4, 4, 'C' );
        game.playRedAt( 1 );
        assertThrowsLike( () -> game.playBlueAt( 0 ), Linea.ERRORPOSICION );
        assertThrowsLike( () -> game.playBlueAt( 5 ), Linea.ERRORPOSICION );
    }

    @Test
    public void test10RedWinsVerticallyInC() {
        Linea game = new Linea( 4, 4, 'C' );
        redWinsV(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test11BlueWinsVerticallyInC() {
        Linea game = new Linea(4, 4, 'C');
        blueWinsV(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test12RedWinsHorizontallyInC() {
        Linea game = new Linea(4, 4, 'C');
        redWinsH(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test13BlueWinsHorizontallyInC() {
        Linea game = new Linea(4, 4, 'C');
        blueWinsH(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test14RedWinsRightDiagonallyInC() {
        Linea game = new Linea(4, 4, 'C');
        redWinsRightD(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test15BlueWinsRightDiagonallyInC() {
        Linea game = new Linea(4, 4, 'C');
        blueWinsRightD(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test16RedWinsLeftDiagonallyInC() {
        Linea game = new Linea(4, 4, 'C');
        redWinsLeftD(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test17BlueWinsLeftDiagonallyInC() {
        Linea game = new Linea(4, 4, 'C');
        blueWinsLeftD(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test18RedWinsVerticallyInA() {
        Linea game = new Linea(4, 4, 'A');
        redWinsV(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test19BlueWinsVerticallyInA() {
        Linea game = new Linea(4, 4, 'A');
        blueWinsV(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test20RedWinsHorizontallyInA() {
        Linea game = new Linea(4, 4, 'A');
        redWinsH(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test21BlueWinsHorizontallyInA() {
        Linea game = new Linea(4, 4, 'A');
        blueWinsH(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test22RedDoesntWinRightDiagonallyInA() {
        Linea game = new Linea(4, 4, 'A');
        redWinsRightD(game);
        assertFalse(game.finished());
    }

    @Test
    public void test23BlueDoesntWinRightDiagonallyInA() {
        Linea game = new Linea(4, 4, 'A');
        blueWinsRightD(game);
        assertFalse(game.finished());
    }

    @Test
    public void test24RedDoesntWinLeftDiagonallyInA() {
        Linea game = new Linea(4, 4, 'A');
        redWinsLeftD(game);
        assertFalse(game.finished());
    }

    @Test
    public void test25BlueDoesntWinLeftDiagonallyInA() {
        Linea game = new Linea(4, 4, 'A');
        blueWinsLeftD(game);
        assertFalse(game.finished());
    }

    @Test
    public void test26RedWinsRightDiagonallyInB() {
        Linea game = new Linea(4, 4, 'B');
        redWinsRightD(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test27BlueWinsRightDiagonallyInB() {
        Linea game = new Linea(4, 4, 'B');
        blueWinsRightD(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test28RedWinsLeftDiagonallyInB() {
        Linea game = new Linea(4, 4, 'B');
        redWinsLeftD(game);
        assertWinner(game, " \uD83D\uDD34 ");
    }

    @Test
    public void test29BlueWinsLeftDiagonallyInB() {
        Linea game = new Linea(4, 4, 'B');
        blueWinsLeftD(game);
        assertWinner(game, " \uD83D\uDD35 ");
    }

    @Test
    public void test30RedDoesntWinVerticallyInB() {
        Linea game = new Linea(4, 4, 'B');
        redWinsV(game);
        assertFalse(game.finished());
    }

    @Test
    public void test31BlueDoesntWinVerticallyInB() {
        Linea game = new Linea(4, 4, 'B');
        blueWinsV(game);
        assertFalse(game.finished());
    }

    @Test
    public void test32RedDoesntWinHorizontallyInB() {
        Linea game = new Linea(4, 4, 'B');
        redWinsH(game);
        assertFalse(game.finished());
    }

    @Test
    public void test33BlueDoesntWinHorizontallyInB() {
        Linea game = new Linea(4, 4, 'B');
        blueWinsH(game);
        assertFalse(game.finished());
    }

    @Test
    public void test34ModeThatDoesntExistCantStartGame(){
        assertThrowsLike( () -> new Linea( 4, 4, 'D' ), Modos.ERRORMODO );
    }

    @Test
    public void test35BoardCantBeTooSmall(){
        assertThrowsLike( () -> new Linea( 3, 3, 'C' ), Linea.ERRORTABLERO );
    }

    private void assertThrowsLike( Executable executable, String error ) {
        assertEquals(error,
                assertThrows(RuntimeException.class, executable, "Debería haber lanzado una excepción")
                        .getMessage());
    }

    private void redWinsV(Linea game) {
        game.playRedAt( 1 );
        game.playBlueAt( 2 );
        game.playRedAt( 1 );
        game.playBlueAt( 2 );
        game.playRedAt( 1 );
        game.playBlueAt( 2 );
        game.playRedAt( 1 );
    }

    private void blueWinsV(Linea game) {
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(3);
        game.playBlueAt(1);
    }

    private void redWinsH(Linea game) {
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);
    }

    private void blueWinsH(Linea game) {
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(1);
        game.playBlueAt(4);
        game.playRedAt( 1 );
        game.playBlueAt( 4 );
    }

    private void redWinsRightD(Linea game) {
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(4);
        game.playBlueAt(1);
        game.playRedAt(4);
    }

    private void blueWinsRightD(Linea game) {
        game.playRedAt(4);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);
        game.playBlueAt(3);
        game.playRedAt(4);
        game.playBlueAt(4);
    }

    private void redWinsLeftD(Linea game) {
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);
    }

    private void blueWinsLeftD(Linea game) {
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
    }

    private void assertWinner(Linea game, String winner) {
        assertTrue(game.finished());
        assertEquals(game.winner(), winner);
    }
}
