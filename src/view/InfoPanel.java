package view;

import model.InfoCard;
import model.QuizCard;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private String info;
    private String title;
    String picpath1,picpath2;
    JLabel label;
    JLabel information, gPic,rPic;

    /**
     * Constructor for InfoPanel
     */
    public InfoPanel(){

        info = "Loading...";
        title = "Loading...";
        setLayout(new GridLayout(4,1));
        label = new JLabel();
        label.setText(title);
        label.setForeground(Color.WHITE);
        //label.setOutlineColor(Color.BLACK);
        label.setFont(new Font("Serif",Font.BOLD,40));
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, "1");

        information = new JLabel();
        information.setText("<html><font color='white'><center>"+info+" <br><br><br>Press Enter to Continue</center></font></html>");
        information.setForeground(Color.WHITE);
        //howTo.setF(Color.black);
        information.setFont(new Font ("Serif",Font.BOLD, 24));
        information.setHorizontalAlignment(JLabel.CENTER);
        add(information,"2");
        setOpaque(false);

        rPic= new JLabel();
        gPic= new JLabel();
        gPic.setHorizontalAlignment(JLabel.CENTER);
        rPic.setHorizontalAlignment(JLabel.CENTER);
        add(gPic,"2");
        add(rPic,"2");



    }

    /**
     * Updates information to be display on infoCard popup
     * @param infoCard
     */
    public void update(InfoCard infoCard){
        title = infoCard.getTitle();
        info = infoCard.getInfo();
        picpath1= infoCard.getActualImage();
        picpath2= infoCard.getInGameImage();
        rPic.setText("<html><img src=file:"+picpath1+"></html>");
        //System.out.println(picpath1.toString());
        gPic.setText("<html><img src=file:"+picpath2+"></html>");
        // if infoCard is a quizCard then the infoPanel displays as a quiz otherwise it displays as info
        if(infoCard instanceof QuizCard){
            label.setText("<html><font color='white'><center>POWER-UP QUESTION: "+title+"<br>"+infoCard.getInfo()+"</center></font></html>");
            information.setText("<html><font color='white'><center>Press 1<br>"+((QuizCard) infoCard).getAnswerOne()+"<br><br><br>Press 2<br>"+((QuizCard) infoCard).getAnswerTwo()+"</center></font></html>");
        } else {
            label.setText(title);
            information.setText("<html><font color='white'><center>" + info + " <br><br><br>Press Enter to Continue</center></font></html>");
        }
    }

    /**
     * Setter for info
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Setter for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Paints the fish images on screen
     * @param g
     */
    public void paintComponent(Graphics g){
        rPic.setText("<html><img src=file:"+picpath1+"></html>");
        gPic.setText("<html><img src=file:"+picpath2+"></html>");

    }





}