package Tiles;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {
    public String type;

    boolean pressed = false;

    public Tile () {
        this.setPreferredSize(new Dimension(25, 25));
    }

    public void buttonPressed() {
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
//        this.setForeground(Color.blue);
        this.setBorderPainted(false);
    }
}
