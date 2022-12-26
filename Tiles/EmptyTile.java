package Tiles;

import java.awt.*;

public class EmptyTile extends Tile {

    public EmptyTile () {
//        type = "empty";

    }

    public void buttonPressed() {
        this.setOpaque(true);
        this.setBackground(Color.red);
//        this.setForeground(Color.blue);
        this.setBorderPainted(false);
        System.out.println("Mine pressed");
    }

}
