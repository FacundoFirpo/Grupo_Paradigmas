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
    public static String ERRORMODO = "Modo invalido";

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

    public void playRedAt(int pos) {
        turno.playRed();

        if (pos <= 0 || pos > base) {
            throw new RuntimeException(ERRORPOSICION);
        }

        partida.get(pos - 1).add(" \uD83D\uDD34 ");

        redFinished = modo.estrategiasGanadoras( this, pos );

        if ( redFinished ){
            System.out.println( "Ganan las rojas!" );
            winner = "rojas";
        }

        turno = turno.next();
    }

    public void playBlueAt(int pos) {

        turno.playBlue();

        if (pos <= 0 || pos > base) {
            throw new RuntimeException(ERRORPOSICION);
        }

        partida.get(pos - 1).add( " \uD83D\uDD35 " );

        blueFinished = modo.estrategiasGanadoras( this, pos );

        if ( blueFinished ){
            System.out.println( "Ganan las azules!" );
            winner = "azules";
        }

        turno = turno.next();
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
        for ( int i = 0; i < altura; i++ ) {
            if ( inicio - i < base && inicio - i >= 0 ) {
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

        public String winner() {
            return winner;
        }
}
