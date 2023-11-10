package linea;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {

    public int base;
    public int altura;
    public Modos modo;
    public boolean redFinished;
    public boolean blueFinished;
    public Turnos turno = new Rojas();
    public int turnosJugados = 0;
    public String winner;
    public static String ERRORPOSICION = "Posicion invalida";
    public static String ERRORTABLERO = "El tablero es demasiado chico";

    public ArrayList<ArrayList<Turnos>> partida = new ArrayList<ArrayList<Turnos>>();
    public int enLinea = 0;
    ArrayList<String> tablero = new ArrayList<>();

    public Linea(int b, int a, char m) {
        if ( b < 4 && a < 4 ) throw new RuntimeException( ERRORTABLERO );
        base = b;
        altura = a;
        modo = Modos.modoElegido(m, this);
        IntStream.range(0, base).forEach( i -> partida.add( new ArrayList<Turnos>() ) );
    }

    public String show() {

        IntStream.range(1, altura + 1).forEach( i -> {
            tablero.add( "\n|");
            IntStream.range(0, base).forEach( j -> {
                if ( indiceFilaExiste( altura - i, j ) ) {
                    tablero.add( partida.get(j).get(altura - i).ficha() );
                } else {
                    tablero.add( " \uD83D\uDD18 " );
                }
            } );
            tablero.add( "|" );
        });
        tablero.add( "\n|" + " \uD83D\uDD3C ".repeat(base) + "|" );
        String tableroString = String.join( "", tablero );
        tablero.clear();
        return tableroString;
    }

    public boolean finished() {
        return redFinished || blueFinished || turnosJugados == base * altura;
    }

    public void playRedAt( int pos ) {
        turno.playRed();

        if (pos <= 0 || pos > base) {
            throw new RuntimeException(ERRORPOSICION);
        }

        partida.get(pos - 1).add( new Rojas() );

        redFinished = modo.estrategiasGanadoras( this, pos );

        if ( redFinished ){
            System.out.println( "Ganan las" + turno.ficha() );
            winner = turno.ficha();
        }

        turno = turno.next();
        turnosJugados++;
    }

    public void playBlueAt( int pos ) {
        turno.playBlue();

        if (pos <= 0 || pos > base) {
            throw new RuntimeException(ERRORPOSICION);
        }

        partida.get(pos - 1).add( new Azules() );

        blueFinished = modo.estrategiasGanadoras( this, pos );

        if ( blueFinished ){
            System.out.println( "Ganan las" + turno.ficha() );
            winner = turno.ficha();
        }

        turno = turno.next();
        turnosJugados++;
    }

    public boolean horizontalWin( int col ) {


        IntStream.range(col - 4, col + 3).forEach( i -> enLinea = enLinea( i, partida.get(col - 1).size() - 1, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
    }

    public boolean verticalWin(int col) {

        IntStream.range(0, partida.get(col - 1).size()).forEach( i -> enLinea = enLinea( col - 1, i, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
    }

    public boolean rightDiagonalWin(int col) {
        int inicio = col - partida.get(col - 1).size();

        IntStream.range(0, base - inicio).forEach( i -> enLinea = enLinea( inicio + i, i, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
        }

    public boolean leftDiagonalWin(int col) {
        int inicio = col + partida.get(col - 1).size() - 2;

        IntStream.range(0, altura).forEach( i -> enLinea = enLinea( inicio - i, i, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
        }

    public int enLinea(int colIndice, int filaIndice, String ficha, int enLinea) {
        if ( indiceColumnaExiste( colIndice )) {
            if (indiceFilaExiste( filaIndice, colIndice )) {
                if ( turno.equals( partida.get(colIndice).get(filaIndice) )) {
                    return enLinea + 1;
                }
                return 0;
            }
            return 0;
        }
        return enLinea;
    }

    public boolean indiceFilaExiste( int i, int col ){
        return i >= 0 && i < partida.get(col).size();
    }

    public boolean indiceColumnaExiste( int i ){
        return i >= 0 && i < base;
    }

    public String winner() {
        return winner;
    }

    public boolean termino(int enLinea){
        return enLinea == 4;
    }
}
