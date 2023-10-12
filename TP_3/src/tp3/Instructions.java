package tp3;

import java.util.*;

public class Instructions{
    public static ArrayList<Character> orders = new ArrayList<>( Arrays.asList( 'f', 'r', 'l', 'u', 'd', 'm', ' ' ) );

    public void doSomething( char order ){
        int index = orders.indexOf( order );
        Nemo.instructions[ index ].work();
    }

    public void work(){}
}
