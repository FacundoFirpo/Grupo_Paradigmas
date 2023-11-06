package linea;

import java.util.ArrayList;

public abstract class Fichas {

    public abstract boolean isRed();

    public abstract Fichas fichaAbajo(ArrayList<ArrayList< Fichas >> tablero, int columna, int fila );
}
