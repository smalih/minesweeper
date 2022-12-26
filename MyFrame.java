import java.awt.*;
import javax.swing.*;
import Tiles.*;
public class MyFrame extends JFrame {

    MyPanel panel;

    int x = 20;
    int y = 20;
    GridLayout GameLayout = new GridLayout(x, y);
    Tile[][] Gameboard = new Tile[y][x];

    MyFrame() {

        // randomly place mines in grid
        for (int i = 0; i < 80; i++) {

            int randx;
            int randy;
            do {
                randx = (int) (Math.random() * x);
                randy = (int) (Math.random() * y);
            } while(Gameboard[randy][randx] != null); // check that tile is not already mine
            Gameboard[randy][randx] = new Mine();
            Gameboard[randy][randx].location = new Point(randx, randy);
        }

        // fill remaining tiles as numtiles/empties
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                if (!(Gameboard[j][i] instanceof Mine)) {
                    int adjMines = 0;
                    // check adjSquares
                    int[] diff = {-1, 0, 1};
                    for (int diff1: diff) {
                        for (int diff2: diff) {
                            if (diff1 == 0 && diff2 == 0) {
                                continue;
                            }
                            try {
                                if (Gameboard[j + diff1][i + diff2] instanceof Mine) {
                                    adjMines++;
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException e) {
                            }
                        }
                    }

                    // if at least one adj mine, tile is numtile
                    if (adjMines > 0) {
                        Gameboard[j][i] = new NumTile(adjMines);
                    }
                    else { // else, tile is empty tile
                        Gameboard[j][i] = new Tile();
                    }
                    Gameboard[j][i].location = new Point(i, j);

                }
            }
        }
//        this.setPreferredSize(new Dimension(500,500));

        panel = new MyPanel();
        panel.setLayout(GameLayout);
        JButton b = new JButton();
//        Dimension buttonSize = b.getPreferredSize();
////        panel.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * (int) (buttonSize.getHeight() * 3.5))));
//        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5),
//                (int)(buttonSize.getHeight() * 3.5) ));
        for (int i = 0; i < x * y; i++) {
            int yIndex = (int) Math.floor(i /x);
            int xIndex = i % x;
            Tile button = Gameboard[yIndex][xIndex];
            button.setMargin(new Insets(0,0,0,0));
            button.addActionListener(e -> {

                // when an empty tile is clicked on, reveal all adj. empty tiles
                if (button.type == Tile.Type.EMPTY) {
                    findAdjEmptyTiles(button);
                }
                button.buttonPressed();
            });
            panel.add(button);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameLayout.setHgap(0);
        GameLayout.setVgap(0);
        GameLayout.layoutContainer(panel);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void findAdjEmptyTiles(Tile tile) {
        // skip tile if already encountered
        if (tile.revealed) {
            return;
        }

        // if tile is mine or numtile
        if (!(tile.type == Tile.Type.EMPTY)) {
            // if tile is numtile, press (essentially reveal it)
            if (tile.type == Tile.Type.NUMTILE) {
                tile.buttonPressed();
            }
            return;
        }

        // reveal current tile
        tile.buttonPressed();
        int x = tile.location.x;
        int y = tile.location.y;
        int[] diffs = {-1, 0, 1};

        // loop through adj tiles (8 compass directions
        for (int diff1: diffs) {
            for (int diff2: diffs) {
                if (!(diff1 == 0 && diff2 == 0)) {
                    try {
                        // if adj tile is in a corner, only reveal if tile is numtile
                        if ((Math.abs(diff1 + diff2) == 1 || (Gameboard[y + diff1][x + diff2].type != Tile.Type.EMPTY)))
                        findAdjEmptyTiles(Gameboard[y + diff1][x + diff2]);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
        }
    }

}
