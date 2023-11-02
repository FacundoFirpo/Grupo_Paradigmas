package linea;

import java.util.ArrayList;

public class Linea {

    public int base;
    public int altura;
    public char modo;
    public boolean redFinished;
    public boolean blueFinished;
    public String turno = "rojas";

    public static String ERRORTURNO = "No es tu turno!";

    public ArrayList<ArrayList<String>> partida = new ArrayList<ArrayList<String>>();

    public Linea(int b, int a, char m) {
        base = b;
        altura = a;
        modo = m;
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

    public void playRedAt(int pos) {
        if (turno == "azules"){
            throw new RuntimeException(ERRORTURNO);
        }

        if (pos > 0 && pos <= base) {
            partida.get(pos - 1).add(" \uD83D\uDD34 ");
        }

        redFinished = horizontalWin(pos) || verticalWin(pos) || rightDiagonalWin(pos) ||leftDiagonalWin(pos);
        if ( redFinished ){
            System.out.println( "Ganan las rojas!" );
        }

        turno = "azules";
    }

    public void playBlueAt(int pos) {

        if (turno == "rojas"){
            throw new RuntimeException(ERRORTURNO);
        }

        if (pos > 0 && pos <= base) {
            partida.get(pos - 1).add(" \uD83D\uDD35 ");
        }

        blueFinished = horizontalWin(pos) || verticalWin(pos) || rightDiagonalWin(pos) ||leftDiagonalWin(pos);
        if ( blueFinished ){
            System.out.println( "Ganan las azules!" );
        }

        turno = "rojas";
    }

    public boolean horizontalWin(int col) {
        int fila = partida.get(col - 1).size();
        String ficha = partida.get(col - 1).get(fila - 1);
        int enLinea = 0;
        for (int i = 0; i < base; i++) {
            if (partida.get(i).size() >= fila) {
                if (partida.get(i).get(fila - 1) == ficha) {
                    enLinea++;
                } else {
                    enLinea = 0;
                }
            } else {
                enLinea = 0;
            }
            if (enLinea == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean verticalWin(int col) {
        int fila = partida.get(col - 1).size();
        String ficha = partida.get(col - 1).get(fila - 1);
        int enLinea = 0;
        if (fila > 3) {
            for (int i = 0; i < fila; i++) {
                if (partida.get(col - 1).get(i) == ficha) {
                    enLinea++;
                } else {
                    enLinea = 0;
                }
                if (enLinea == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rightDiagonalWin(int col) {
        int fila = partida.get(col - 1).size();
        String ficha = partida.get(col - 1).get(fila - 1);
        int enLinea = 0;
        int inicio = col - fila;
        for (int i = 0; i < base - inicio; i++) {
            if (inicio + i >= 0) {
                if (partida.get(inicio + i).size() > i) {
                    if (partida.get(inicio + i).get(i) == ficha) {
                        enLinea++;
                    } else {
                        enLinea = 0;
                    }
                    if (enLinea == 4) {
                        return true;
                    }
                } else {
                    enLinea = 0;
                }
            }
        }
        return false;
        }

    public boolean leftDiagonalWin(int col) {
        int fila = partida.get(col - 1).size();
        String ficha = partida.get(col - 1).get(fila - 1);
        int enLinea = 0;
        int inicio = col + fila - 2;
        for ( int i = 0; i < altura - ( inicio - base ); i++ ) {
            if ( inicio - i < base && inicio - i > 0 ) {
                if ( partida.get(inicio - i).size() > i ) {
                    if ( partida.get(inicio - i).get(i) == ficha ) {
                        enLinea++;
                    } else {
                        enLinea = 0;
                    }
                    if (enLinea == 4) {
                        return true;
                    }
                } else {
                    enLinea = 0;
                }
            }
        }
        return false;
        }
}
