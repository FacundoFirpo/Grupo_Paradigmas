package linea;

public class Azules extends Turnos{

    public Turnos next(){
        return new Rojas();
    }

    public void playRed(){
        throw new RuntimeException( ERRORTURNO );
    }

    public void playBlue(){}
}
