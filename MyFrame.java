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

        for (int i = 0; i < 100; i++) {

            int randx;
            int randy;
            do {
                randx = (int) (Math.random() * x);
                randy = (int) (Math.random() * y);
            } while(Gameboard[randy][randx] != null);
            System.out.println("Moving this around");
            Gameboard[randy][randx] = new Mine();
        }

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
                    if (adjMines > 0) {
                        Gameboard[j][i] = new NumTile(adjMines);
                    }
                    else {
                        Gameboard[j][i] = new Tile();
                    }

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
            System.out.println(yIndex + "" + xIndex);
            Tile button = Gameboard[yIndex][xIndex];
            button.setMargin(new Insets(0,0,0,0));
            button.addActionListener(e -> {
                button.buttonPressed();
                button.setEnabled(false);
            });
//            button.setBorder(null);
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

}
