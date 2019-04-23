package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BackPanel extends JPanel {


    BufferedImage bf;

    /**
     * Constructor for BackPanel
     */
    public BackPanel() {
        try {
            bf = ImageIO.read(new File("images/OtherResources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints BackPanel
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bf, 0, 0, this.getWidth(), this.getHeight(), this);
    }


}
