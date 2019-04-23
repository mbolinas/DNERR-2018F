package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GamePanel extends JPanel {
    private OutLineLabel time, score, net,powerups;
    private String clock;
    private int points,netpwr,speed;
    private String netsize;

    private Collection<DrawableObject> drawQueue;

    /**
     * Constructor for GamePanel
     * @param drawQueue
     */
    public GamePanel(Collection<DrawableObject> drawQueue) {
        setLayout(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        time= new OutLineLabel();
        time.setForeground(Color.WHITE);
        time.setOutlineColor(Color.BLACK);
        time.setFont(new Font("Sanserif", Font.BOLD,40));
        time.setHorizontalAlignment(JLabel.CENTER);
        cst.ipadx=50;
        cst.weightx=1;
        cst.weighty =1;
        cst.ipady=50;
        cst.anchor= GridBagConstraints.NORTHWEST;
        add(time,cst);


        score = new OutLineLabel();

        score.setForeground(Color.WHITE);
        score.setOutlineColor(Color.BLACK);
        score.setFont(new Font("Sanserif",Font.BOLD,40));
        score.setHorizontalAlignment(JLabel.CENTER);
        cst.anchor= GridBagConstraints.NORTH;


        net = new OutLineLabel();

        net.setForeground(Color.WHITE);
        net.setOutlineColor(Color.BLACK);
        net.setFont(new Font("Sanserif",Font.BOLD,40));
        net.setHorizontalAlignment(JLabel.CENTER);
        cst.anchor = GridBagConstraints.NORTHEAST;

        powerups = new OutLineLabel();

        powerups.setForeground(Color.WHITE);
        powerups.setOutlineColor(Color.BLACK);
        powerups.setFont(new Font("Sanserif",Font.BOLD,40));
        powerups.setHorizontalAlignment(JLabel.CENTER);
        cst.anchor = GridBagConstraints.NORTH;

        add(net,cst);
        add(time,cst);
        add(score,cst);
        add(powerups,cst);

        this.drawQueue = drawQueue;
        setOpaque(false);
    }

    /**
     * Draws GamePanel objects
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        time.setText(clock);
        score.setText("Score: "+points);
        net.setText(netsize);
        powerups.setText("Net size= +"+netpwr+"   Speed= Level "+speed);


        for (Iterator<DrawableObject> i = drawQueue.iterator(); i.hasNext(); ) {

               i.next().drawUpdate(g, this);
               i.remove();


        }

       // drawQueue.clear();

    }

    /**
     * Setter for clock
     * @param clock
     */
    public void setClock(String clock) {
        this.clock = clock;
    }

    /**
     * Setters for points
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Setter for net
     * @param net
     */
    public void setNetsize(String net) {
        this.netsize = net;
    }

    /**
     * Setter for netpwr
     * @param netpwr
     */
    public void setNetpwr(int netpwr) {
        this.netpwr = netpwr;
    }

    /**
     * Setter for speed
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

