package tp3;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Instructions{

    public static ArrayList<Instructions> instructions = new ArrayList<Instructions>( Arrays.asList( new Forward(), new Right(), new Left(), new Up(), new Down(), new Missile() ));

    public abstract void work( Nemo nemo );

    public static void instructionFor( char order, Nemo nemo ){
        instructions.stream()
                .filter( instruction -> instruction.letter( order ) )
                .forEach( instruction -> instruction.work( nemo ) );
    }

    public abstract boolean letter(char order);
}
