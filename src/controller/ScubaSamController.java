package controller;

import model.GameObject;
import model.State;

import java.awt.Dimension;

public class ScubaSamController implements Controller{

    private int velAmount = 6;
    private ControlListener controlListener;
    //velocity
    private long xVel;
    private long yVel;
    private double friction = 1.3;

    /**
     * Constructor for the ScubaSameController
     * @param controlListener
     */
    public ScubaSamController(ControlListener controlListener) {
        this.controlListener = controlListener;
        xVel = 0;
        yVel = 0;
    }

    /**
     * Setter for velocity
     * @param newVelAmount
     */
    public void setVelAmount(int newVelAmount){
        velAmount = newVelAmount;
    }

    /**
     * Getter for velocity
     * @return
     */
    public int getVelAmount(){
        return velAmount;
    }

    /**
     * Getter for friction
     * @return
     */
    public double getFriction(){
        return friction;
    }

    /**
     * Setter for friction
     * @param f
     */
    public void setFriction(double f){
        friction = f;
    }

    /**
     * Update function that controllers Scuba Sam's movement and direction
     * @param itself
     * @param size
     */
    @Override
    public void update(GameObject itself, Dimension size) {
        xVel /= friction;
        yVel /= friction;

        for(Integer i : controlListener.getPressed()) {
            switch (i) {
                case 38: // UP
                    yVel -= velAmount;
                    break;
                case 40: // DOWN
                    yVel += velAmount;
                    break;
                case 37: // LEFT
                    xVel -= velAmount;
                    break;
                case 39: // RIGHT
                    xVel += velAmount;
                    break;
                default:
            }
        }

        if(Math.abs(xVel) < .5) xVel = 0;
        if(Math.abs(yVel) < .5) yVel = 0;

        int newX = (int) (itself.getLocation().width + xVel);
        int newY = (int) (itself.getLocation().height + yVel);

        if (newX>size.getWidth()) { newX = (int) size.getWidth(); }
        if (newX<0) { newX = 0; }
        if (newY + (int) (itself.getImageSize().getHeight() * .5)>size.getHeight()) { newY = (int) size.getHeight() - (int) (itself.getImageSize().getHeight() * .5); }
        if (newY<size.getHeight() * .1) { newY = (int) (size.getHeight() * .1); }

        String state = "";

        if(yVel < 0) state = "UP";
        else if(yVel > 0 ) state = "DOWN";


        if(xVel < 0 && !state.isEmpty()) state += "_LEFT";
        else if(xVel < 0) state = "LEFT";

        if(xVel > 0 && !state.isEmpty()) state += "_RIGHT";
        else if(xVel > 0) state = "RIGHT";

        if(xVel == 0 && yVel == 0) state = "IDLE";

        itself.setState(State.valueOf(state));

        itself.setLocation(new Dimension(newX, newY));
    }
}
