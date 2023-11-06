package linea;

public class A extends Modos{

    public boolean letra( char modo ){
        return modo == 'A';
    }

    public boolean estrategiasGanadoras( Linea juego, int pos ){
        return juego.verticalWin( pos ) || juego.horizontalWin( pos );
    }
}
