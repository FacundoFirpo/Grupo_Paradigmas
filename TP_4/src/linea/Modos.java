package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Modos {

    public static ArrayList<Modos> modos = new ArrayList<Modos>( Arrays.asList( new A(), new B(), new C() ) );

    public static String ERRORMODO = "Modo invalido";

    public static Modos modoElegido(char modo, Linea juego){
        List<Modos> elegido = modos.stream()
                    .filter( m -> m.letra( modo ) )
                    .toList();

        if ( elegido.size() == 0 ){
            throw new RuntimeException( ERRORMODO );
        }

        return elegido.get(0);
    }

    public abstract boolean estrategiasGanadoras( Linea juego, int pos );

    public abstract boolean letra( char modo );

}
