package linea;

import java.util.ArrayList;

public class Linea {

    public int base;
    public int altura;
    public Modos modo;
    public boolean redFinished;
    public boolean blueFinished;
    public Turnos turno = new Rojas();
    public String winner;
    public static String ERRORPOSICION = "Posicion invalida";

    public ArrayList<ArrayList<String>> partida = new ArrayList<ArrayList<String>>();

    public Linea(int b, int a, char m) {
        base = b;
        altura = a;
        modo = Modos.modoElegido(m, this);
        for (int i = 0; i < base; i++) {
            ArrayList<String> columna = new ArrayList<String>();
            partida.add(columna);
        }
    }

    public String show() {
        String tablero = "";
        for (int i = altura - 1; i >= 0; i--) {
            tablero += "\n|";
            for (int j = 0; j < base; j++) {
                if (partida.get(j).size() > i) {
                    tablero += partida.get(j).get(i);
                } else {
                    tablero += " \uD83D\uDD18 ";
                }
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

        partida.get(pos - 1).add(" \uD83D\uDD34 ");

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

        partida.get(pos - 1).add( " \uD83D\uDD35 " );

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
        int enLinea = 0;

        for (int i = col - 4; i < col + 3; i++) {
            enLinea = enLinea(i, filaIndice, turno.ficha(), enLinea );
            if (enLinea == 4) {
                return true;
            }
        }

        return false;
    }


    public boolean verticalWin(int col) {
        int colIndice = col - 1;
        int fila = partida.get(colIndice).size();
        int enLinea = 0;

        for (int i = 0; i < fila; i++) {
            enLinea = enLinea(colIndice, i, turno.ficha(), enLinea);
            if (enLinea == 4) {
                return true;
            }
        }

        return false;
    }

    public boolean rightDiagonalWin(int col) {
        int fila = partida.get(col - 1).size();
        int enLinea = 0;
        int inicio = col - fila;
        for (int i = 0; i < base - inicio; i++) {
            enLinea = enLinea(inicio + i, i, turno.ficha(), enLinea);
            if (enLinea == 4) {
                return true;
            }
        }

        return false;
        }

    public boolean leftDiagonalWin(int col) {
        int fila = partida.get(col - 1).size();
        int enLinea = 0;
        int inicio = col + fila - 2;
        for ( int i = 0; i < altura; i++ ) {
            enLinea = enLinea( inicio - i, i, turno.ficha(), enLinea );
            if ( enLinea == 4 ) {
                return true;
            }
        }

        return false;
        }

    public int enLinea(int colIndice, int filaIndice, String ficha, int enLinea) {
        if ( indiceColumnaExiste( colIndice )) {
            if (indiceFilaExiste( filaIndice, colIndice )) {
                if (partida.get(colIndice).get(filaIndice) == ficha) {
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
}
