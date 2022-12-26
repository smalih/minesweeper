package Tiles;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {
    public enum Type {
        EMPTY,
        MINE,
        NUMTILE
    }

    public Type type;
//    public String type;
    public Point location;
    public boolean revealed = false;

    public Tile () {
        type = Type.EMPTY;
        this.setPreferredSize(new Dimension(25, 25));
    }

    public void buttonPressed() {
        revealed = true;
        setOpaque(true);
        setBackground(Color.lightGray);
        setBorderPainted(false);
        setEnabled(false);
    }
}
