package linea;

public abstract class Turnos {

    public static String ERRORTURNO = "No es tu turno!";

    public abstract Turnos next();

    public abstract void playRed();

    public abstract void playBlue();
}
