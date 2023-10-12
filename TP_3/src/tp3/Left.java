package tp3;

import tp3.Instructions;

public class Left extends Instructions {

    public void work(){
        Nemo.orientation = Nemo.orientations[ Nemo.orientation.goLeft() ];
    }
}
