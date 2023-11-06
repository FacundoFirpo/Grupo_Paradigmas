package linea;

public class C extends Modos {

    public boolean letra( char modo ){
        return modo == 'C';
    }

    public boolean estrategiasGanadoras( Linea juego, int pos ){
          return juego.verticalWin( pos ) || juego.horizontalWin( pos ) || juego.rightDiagonalWin( pos ) || juego.leftDiagonalWin( pos );
    }
}
