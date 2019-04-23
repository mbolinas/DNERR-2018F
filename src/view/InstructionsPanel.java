package view;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {
    private  OutLineLabel start;
    private JLabel howTo;
    private JLabel middle;
    private JLabel pics;


    /**
     * Constructor for InstructionsPanel
     * create instance and get filepath
     */
    public InstructionsPanel() {
        // create a label for the top of the Jpanel
        setLayout(new GridLayout(4, 1));
        start = new OutLineLabel();
        start.setText(" Instructions ");
        start.setForeground(Color.WHITE);
        start.setOutlineColor(Color.BLACK);
        start.setFont(new Font("Sanserif", Font.BOLD, 38));
        start.setHorizontalAlignment(JLabel.CENTER);
        add(start, "1");

        howTo = new JLabel();
        //using HTML to auto wrap text inside JLabels
        howTo.setText("<html><font white='orange'><center>" +
                " You're here to help DNERR sample species in the estuary by catching lots of fish and solving scientific questions!<br>" +
                " Use the arrow keys to catch different fish species using your net.<br> When your net is full return to the research boat to empty it.<br>" +
                " Solve periodic questions correctly for power ups!</center></font></html>");
        howTo.setForeground(Color.WHITE);
        howTo.setFont(new Font("Sanserif", Font.BOLD, 30));
        howTo.setHorizontalAlignment(JLabel.CENTER);
        add(howTo, "2");
        setOpaque(false);

        middle = new JLabel();
        middle.setText("<html><font color='white'><center>Press Enter to Continue</center></font></html>");
        middle.setFont(new Font("Sanserif", Font.BOLD, 32));
        middle.setHorizontalAlignment(JLabel.CENTER);
        add(middle, "4");

        pics = new JLabel();
        pics.setText("<html><img src=file:images/OtherResources/arrows.png><html>");
        pics.setHorizontalAlignment(JLabel.CENTER);
        add(pics, "3");

    }



}