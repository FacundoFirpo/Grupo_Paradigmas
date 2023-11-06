package linea;

public class B extends Modos {

    public boolean letra( char modo ){
        return modo == 'B';
    }

    public boolean estrategiasGanadoras( Linea juego, int pos ){
        return juego.rightDiagonalWin( pos ) || juego.leftDiagonalWin( pos );
    }
}
