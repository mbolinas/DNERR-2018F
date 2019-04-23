package view;

import javax.swing.*;
import java.awt.*;

import main.View;

public class MenuPanel extends JPanel {
    private OutLineLabel start;
    private Image img;

    /**
     * Constructor for MenuPanel
     * create instance and get filepath
     * @param img
     */
    public MenuPanel(String img) {
        this(new ImageIcon(img).getImage());
        start = new OutLineLabel();
        start.setText(" Press enter to Start! ");
        start.setForeground(Color.WHITE);
        start.setOutlineColor(Color.BLACK);
        start.setFont(new Font("Sanserif",Font.BOLD,40));
        start.setHorizontalAlignment(JLabel.CENTER);
        add(start, BorderLayout.NORTH);
        setOpaque(false);
    }

    /**
     * Resize and convert image
     * @param img
     */
    public MenuPanel(Image img) {
        this.img = img.getScaledInstance((int)View.getSize().getWidth()-50,(int)View.getSize().getHeight()-50,Image.SCALE_FAST);
    }


    /**
     * Draw and add label for main menu
     * @param g
     */
    public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
    }
}