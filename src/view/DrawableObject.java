package view;

import model.State;
import model.Type;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public abstract class DrawableObject implements Serializable{

    protected Type type;
    protected State state;
    protected Dimension location;

    private transient BufferedImage[][] states;
    private Dimension imgSize;
    private int frameCap;
    private int frameNum;
    private int currentTick = 0;
    private final int ticksBetweenFrames = 3;

    /**
     * Controller for drawable object
     * @param location
     * @param type
     * @param state
     * @param imgSize
     * @param frameCap
     */
    public DrawableObject(Dimension location, Type type, State state, Dimension imgSize, int frameCap) {
        this.location = location;
        this.type = type;
        this.state = state;
        this.imgSize = imgSize;
        this.frameCap = frameCap;
        frameNum = 0;

        states = new BufferedImage[State.values().length][frameCap];
        for(State imgState : State.values()) {
            BufferedImage pic = createImage(this.type, imgState);
            for (int i = 0; i < frameCap; i++) {
                states[imgState.ordinal()][i] = pic.getSubimage(imgSize.width * i, 0, imgSize.width, imgSize.height);
            }
        }

    }

    /**
     * Getter for type
     * @return
     */
    public Type getType() {
        return type;
    }

    /**
     * Getter for state
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for state
     * @param s
     */
    public void setState(State s){
        this.state = s;
    }

    /**
     * Getter for location
     * @return
     */
    public Dimension getLocation() {
        return location;
    }

    /**
     * Getter for imgsize
     * @return
     */
    public Dimension getImageSize() { return imgSize; }

    /**
     * setter for location
     * @param location
     */
    public void setLocation(Dimension location) {
        this.location = location;
    }

    /**
     * Draws objects to screen
     * @param g
     * @param observer
     */
    public void drawUpdate(Graphics g, ImageObserver observer) {
        currentTick = (currentTick + 1)%ticksBetweenFrames;
        if(currentTick == 0) {
            frameNum = (frameNum + 1) % frameCap;
        }
        g.drawImage(states[state.ordinal()][frameNum], location.width - (imgSize.width / 2), location.height - imgSize.height / 2, observer);
    }

    /**
     * Loads images for the game
     * @param type
     * @param state
     * @return
     */
    private static BufferedImage createImage(Type type, State state) {
        try {
            BufferedImage bufferedImage;

            switch (type) {
                case DIVER:
                    bufferedImage = ImageIO.read(new File("images/" + type.toString() + "/" + state.toString() + ".png"));
                    break;
                case BOAT:
                    bufferedImage = ImageIO.read(new File("images/boat/boat.png"));
                    break;
                case NORTHERNSNAKEHEAD:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/northernSnakeHead/northernsnakeheadleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/northernSnakeHead/northernsnakeheadright.png"));
                    break;
                case FLATHEADCATFISH:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/flatheadCatfish/flatheadcatfishleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/flatheadCatfish/flatheadcatfishright.png"));
                    break;

                case LARGEMOUTHBASS:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/largeMouthBass/largemouthbassleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/largeMouthBass/largemouthbassright.png"));
                    break;

                case CHAINPICKEREL:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/chainPickerel/chainpickerelleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/chainPickerel/chainpickerelright.png"));
                    break;

                case STRIPEDBASS:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/stripedBass/stripedbassleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/stripedBass/stripedbassright.png"));
                    break;

                case BLUECATFISH:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/blueCatfish/bluecatfishleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/blueCatfish/bluecatfishright.png"));
                    break;

                case WHITEPERCH:
                    if(state == State.LEFT) bufferedImage = ImageIO.read(new File("images/whitePerch/whiteperchleft.png"));
                    else bufferedImage = ImageIO.read(new File("images/whitePerch/whiteperchright.png"));
                    break;
                default:
                    bufferedImage = ImageIO.read(new File("images/OtherResources/baseGameObject.png"));
                    break;
            }
            return bufferedImage;

        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }

}
