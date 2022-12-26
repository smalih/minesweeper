package Tiles;

import java.awt.*;

public class Mine extends Tile {

    public Mine () {
//        type = "mine";
        type = Type.MINE;
    }


//    public String type = "mine";

//    Type type = Type.MINE;

    public void buttonPressed() {
        revealed = true;
        setOpaque(true);
        setBackground(Color.red);
        setBorderPainted(false);
        setEnabled(false);
    }

}
