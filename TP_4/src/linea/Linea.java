package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea {

    public int base;
    public int altura;
    public Modos modo;
    public boolean redFinished;
    public boolean blueFinished;
    public Turnos turno = new Rojas();
    public String winner;
    public static String ERRORPOSICION = "Posicion invalida";

    public ArrayList<ArrayList<Turnos>> partida = new ArrayList<ArrayList<Turnos>>();
    public int enLinea = 0;

    public Linea(int b, int a, char m) {
        base = b;
        altura = a;
        modo = Modos.modoElegido(m, this);
        IntStream.range(0, base).forEach( i -> partida.add( new ArrayList<Turnos>() ) );
    }

    public String show() {
        String tablero = "";
        for (int i = altura - 1; i >= 0; i--) {
            tablero += "\n|";
            for (int j = 0; j < base; j++) {
                tablero += indiceFilaExiste( i, j ) ? partida.get(j).get(i).ficha() : " \uD83D\uDD18 ";
            }
            tablero += "|";
        }
        tablero += "\n|" + " \uD83D\uDD3C ".repeat(base) + "|";
        return tablero;
    }

    public boolean finished() {
        return redFinished || blueFinished;
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
    }

    public boolean horizontalWin( int col ) {
        int colIndice = col - 1;
        int fila = partida.get(colIndice).size();
        int filaIndice = fila - 1;

        IntStream.range(col - 4, col + 3).forEach( i -> enLinea = enLinea( i, filaIndice, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
    }

    public boolean verticalWin(int col) {
        int colIndice = col - 1;
        int fila = partida.get(colIndice).size();

        IntStream.range(0, fila).forEach( i -> enLinea = enLinea( colIndice, i, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
    }

    public boolean rightDiagonalWin(int col) {
        int fila = partida.get(col - 1).size();
        int inicio = col - fila;

        IntStream.range(0, base - inicio).forEach( i -> enLinea = enLinea( inicio + i, i, turno.ficha(), enLinea ) );

        boolean estado = termino( enLinea );
        enLinea = 0;
        return estado;
        }

    public boolean leftDiagonalWin(int col) {
        int fila = partida.get(col - 1).size();
        int inicio = col + fila - 2;

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
