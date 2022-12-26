package Tiles;

public class NumTile extends Tile {

    private int number;
//
//    public NumTile () {
//        type = "mine";
//    }


    public NumTile (int number) {
//        type = "numtile";
        type = Type.NUMTILE;
        this.number = number;
    }
//    public String type = "numtile";

//    Type type = Type.NUMTILE;

    private void incrementNumber() {
        this.number++;
    }

    private int getNumber() {
        return this.number;
    }

    public void buttonPressed() {
        revealed = true;
        setText(Integer.toString(number));
        setEnabled(false);
    }
}
