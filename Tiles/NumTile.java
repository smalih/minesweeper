package Tiles;

public class NumTile extends Tile {

    private int number;
//
//    public NumTile () {
//        type = "mine";
//    }

    public NumTile (int number) {
        this.number = number;
    }

    private void incrementNumber() {
        this.number++;
    }

    private int getNumber() {
        return this.number;
    }

    public void buttonPressed() {
        this.setText(Integer.toString(number));
    }
}
