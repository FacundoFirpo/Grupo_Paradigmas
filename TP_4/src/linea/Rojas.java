package linea;

public class Rojas extends Turnos{

    public Turnos next(){
        return new Azules();
    }

    public void playRed(){}

    public void playBlue(){
        throw new RuntimeException( ERRORTURNO );
    }
}
