package Tiles;

import java.awt.*;

public class Mine extends Tile {

    public Mine () {
        type = "mine";
    }

    public void buttonPressed() {
        this.setOpaque(true);
        this.setBackground(Color.red);
//        this.setForeground(Color.blue);
        this.setBorderPainted(false);
        System.out.println("Mine pressed");
    }

}
