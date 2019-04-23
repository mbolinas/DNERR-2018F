package main;

import controller.ControlListener;
import model.InfoCard;
import model.QuizCard;
import model.ScubaSam;
import view.*;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.*;

public class View {
    /////////////////////////////////////////////////////////////////////////////////////
    //Attributes
    private static final int fps = 30;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //private static Dimension screenSize = new Dimension(1920, 1080);
    private InfoCard currentCard;

    private JFrame frame; // the frame
    private int score;
    private JPanel content;
    private CardLayout layouts;
    private Collection<DrawableObject> drawQueue;

    private MenuPanel menu;
    private InstructionsPanel instructions;
    private GamePanel game;
    private InfoPanel infoPanel;
    private SummaryPanel summary;

    private String gameState; //determines the state of the game (e.g. menu, game, summary)
    /////////////////////////////////////////////////////////////////////////////////////
    //Constructor

    /**
     * Constructor for View
     * @param controlListener
     */
    public View(ControlListener controlListener) {
        drawQueue = new LinkedList<>();
        //set background
        // sets up card layout
        content = new BackPanel();
        layouts = new CardLayout();
        content.setLayout(layouts);
        menu = new MenuPanel("./images/OtherResources/logo.png/");
        instructions = new InstructionsPanel();
        game = new GamePanel(drawQueue);
        summary = new SummaryPanel();
        infoPanel = new InfoPanel();
        // this adds different panels that can be viewed
        content.add(menu, "menu");
        content.add(instructions, "instructions");
        content.add(game, "game");
        content.add(summary, "summary");
        content.add(infoPanel, "popup");
        //layouts.show(content, "menu"); // this is how you set which JPanel is shown,
        //use this ^ to skip menu to debug game
        layouts.first(content); //set menu to first in layout.
        this.frame = new JFrame();
        frame.addKeyListener(controlListener);
        frame.setTitle("FISHNITE: ESTUARY CORRAL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize);
        frame.add(content);
        frame.setVisible(true);
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    //Methods

    /**
     * Getter for fps
     * @return
     */
    public int getFps() {
        return fps;
    }

    /**
     * Getter for screenSize
     * @return
     */
    public static Dimension getSize() {
        return screenSize;
    }

    /**
     * Getter for drawQueue
     * @return
     */
    public Collection<DrawableObject> getDrawQueue() {
        return drawQueue;
    }

    /**
     * Getter for gameState
     * @return
     */
    public String getGameState(){
        return this.gameState;
    }
    //updates the view

    /**
     * Updates the view with new information
     * @param drawQueue
     * @param gameState
     * @param infoCard
     * @param score
     * @param research
     * @param timeElapsed
     * @param sam
     */
    public void update(Collection<DrawableObject> drawQueue, String gameState, InfoCard infoCard, int score, int research, long timeElapsed, ScubaSam sam) {
        this.score= score;
        currentCard = infoCard;
        summary.setPoints(this.score);
        game.setPoints(score);
        long countingDown = 180000-timeElapsed;
        long elapsedSeconds = countingDown / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        if (sam!=null) {
            game.setNetpwr((sam.getNet().getMaxWeight()/10)-5);
            game.setSpeed(sam.getController().getVelAmount()-5);
            if((sam.getNet().getMaxWeight()- sam.getNet().getWeight())/10!=0) {
                game.setNetsize("Net Space Left: "+(sam.getNet().getMaxWeight() - sam.getNet().getWeight()) / 10);
            }
            else{
                game.setNetsize("Return to Boat!");
            }
        }


        // clock display
        if (secondsDisplay==0){
            game.setClock(elapsedMinutes+":00");
        }
        else if (secondsDisplay<10){
            game.setClock(elapsedMinutes+":0"+secondsDisplay);
        }
        else {
            game.setClock(elapsedMinutes + ":" + secondsDisplay);
        }
        // Pauses game so that holding enter won't cycle from summary -> menu -> instructions -> game
        if(this.gameState != gameState) {
            setGameState(gameState);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // updates view based on current gameState
        switch (this.gameState) {
            case "menu":
                menu.repaint();
                break;
            case "instructions":
                instructions.repaint();
                break;
            case "game":
                game.repaint();
                break;
            case "popup":
                if (infoCard instanceof QuizCard){
                    infoPanel.update(currentCard);
                } else {
                    infoPanel.update(currentCard);
                }
                game.repaint();
                infoPanel.repaint();
                layouts.show(content, "popup");
                break;
            case "summary":
                summary.repaint();
                break;
        }
    }

    /**
     * Setter for gameState
     * @param gameState
     */
    public void setGameState(String gameState) {
        this.gameState = gameState;

        switch(gameState){
            case "menu":
                layouts.show(content,"menu");
                break;
            case "instructions":
                layouts.show(content, "instructions");
                break;
            case "game":
                layouts.show(content, "game");
                break;
            case "summary":
                layouts.show(content, "summary");
                break;
            case "popup":
                // moved layouts from here to update to prevent previous fish from showing
                break;

        }

    }


}