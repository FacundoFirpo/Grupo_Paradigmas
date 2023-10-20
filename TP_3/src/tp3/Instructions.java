package tp3;

import java.util.*;

public abstract class Instructions{

    public static ArrayList<Character> orders = new ArrayList<>( Arrays.asList( 'f', 'r', 'l', 'u', 'd', 'm') );
    public static List<Instructions> instructions = new ArrayList<Instructions>( Arrays.asList( new Forward(), new Right(), new Left(), new Up(), new Down(), new Missile() ));

    public void work( Nemo nemo ){}

    public static void instructionFor( char order, Nemo nemo ){
        instructions.stream()
                .filter( instruction -> instruction.letter( order ) )
                .forEach( instruction -> instruction.work( nemo ) );
    }

    public abstract boolean letter(char order);
}
