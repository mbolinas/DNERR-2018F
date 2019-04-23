package view;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JLayeredPane {
    private int points;
    private OutLineLabel score;


    /**
     * Constructor for SummaryPanel
     */
    public SummaryPanel(){
        // Displays summary pane, with option to restart
        setLayout(new GridLayout(2,1));

        score = new OutLineLabel();

        score.setForeground(Color.WHITE);
        score.setOutlineColor(Color.BLACK);
        score.setFont(new Font("Sanserif",Font.BOLD,40));
        score.setHorizontalAlignment(JLabel.CENTER);
        add(score);

        OutLineLabel restart = new OutLineLabel();
        restart.setText("Press Enter to Restart");
        restart.setForeground(Color.WHITE);
        restart.setOutlineColor(Color.BLACK);
        restart.setFont(new Font("Sanserif",Font.BOLD,40));
        restart.setHorizontalAlignment(JLabel.CENTER);
        add(restart);



    }

    /**
     * Setter for points
     * @param points
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     * Paint the summary (score)
     * @param g
     */
    public void paintComponent(Graphics g) {
        score.setText("Your Score is: "+ points);
    }
}
