package linea;

public class Rojas extends Turnos{

    public Turnos next(){
        return new Azules();
    }

    public void playRed(){}

    public void playBlue(){
        throw new RuntimeException( ERRORTURNO );
    }

    public String ficha(){
        return " \uD83D\uDD34 ";
    }

    public boolean equals( Object obj ){
        return obj instanceof Rojas;
    }
}
