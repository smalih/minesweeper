import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    Image image;

    MyPanel() {
        image = new ImageIcon("diepair.jpeg").getImage();
        this.setPreferredSize(new Dimension(500, 500));
    }
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, 0, 0, null);

        g2d.setPaint(Color.blue);
//        g2d.setStroke(new BasicStroke(5));
//        g2d.drawLine(0, 0, 500, 500);
//        g2d.setPaint(Color.pink);
//        g2d.drawRect(0, 0, 100, 350);
//        g2d.fillRect(0, 0, 100, 300);
//        g2d.setPaint(Color.orange);
//        g2d.drawOval(0, 0, 100, 100);
//        g2d.fillOval(0, 0, 100, 100);

//        g2d.drawArc(0, 0, 100, 100, 180, 180);
//        g2d.setPaint(Color.red);
//        g2d.fillArc(0, 0, 100, 100, 0, 180);
//        g2d.setPaint(Color.white);
//        g2d.fillArc(0, 0, 100, 100, 180, 180);
//
        g2d.setPaint(Color.black);
        g2d.fillOval(35, 35, 30, 30);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, 50, 98, 50);
        g2d.setPaint(Color.white);
        g2d.fillOval(40, 40, 20, 20);
        g2d.setPaint(Color.lightGray);
        g2d.fillOval(44, 44, 12, 12);

//        int[] xPoints = {150, 250, 350};
//        int[] yPoints = {300, 150, 300};
//        g2d.setPaint(Color.yellow);
//        g2d.fillPolygon(xPoints, yPoints, 3);

//        g2d.setPaint(Color.magenta);
//        g2d.setFont(new Font("Ink Free", Font.BOLD, 50));
//        g2d.drawString("YOU ARE A SUPERSTAR! :D", 10, 50);


    }



}
