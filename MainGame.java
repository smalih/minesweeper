import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Tiles.*;
public class MainGame extends JFrame {

    MyPanel mainPanel;
    MyPanel menuPanel;
    int mines = 80;

    int x = 20;
    int y = 20;
    GridLayout GameLayout = new GridLayout(x, y, 0, 0);
    Tile[][] Gameboard = new Tile[y][x];

    MainGame(String title) {
        super(title);
        setPreferredSize(new Dimension(500, 500));
//        setResizable(false);

        setBounds(100,100,645,470);
        Container c = getContentPane();
//        c.setLayout(new BorderLayout(8,6));
//        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GREEN));

        // randomly place mines in grid
        for (int i = 0; i < mines; i++) {

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

        // mainPanel will hold the mine grid
        mainPanel = new MyPanel();


        JButton b = new JButton();

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
            mainPanel.add(button);
        }

//        mainPanel.setMaximumSize(new Dimension(500, 500));
        MyPanel wrapperPanel = new MyPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.setPreferredSize(new Dimension(500, 500));
//        mainPanel.setPreferredSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


////        wrapperPanel.setPreferredSize(new Dimension(500, 500));
//        wrapperPanel.setMaximumSize(new Dimension(500, 500));


//        c.setPreferredSize(new Dimension(500, 500));
//        c.setMaximumSize(new Dimension(500, 500));


        JButton button1 = new JButton("Button1");
        JButton button2 = new JButton("Button2");
        JButton button3 = new JButton("Button3");
        JButton button4 = new JButton("Button4");
        JButton button5 = new JButton("Button5");
        JButton button6 = new JButton("Button6");
        JButton button8 = new JButton("Button8");
        JButton button9 = new JButton("Button9");
        JButton button10 = new JButton("Button10");
        JButton button11 = new JButton("Button11");


        mainPanel.setLayout(GameLayout);



        menuPanel = new MyPanel();
        menuPanel.setPreferredSize(new Dimension(100, 100));
        menuPanel.setBorder(new LineBorder(Color.BLACK));
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.add(button1);
        menuPanel.add(button2);
        menuPanel.add(button3);
        menuPanel.add(button4);
        c.setPreferredSize(new Dimension(500, 500));


        c.add(menuPanel, BorderLayout.NORTH);
        c.add(mainPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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


