package model;

import controller.Controller;

import java.awt.Dimension;
/*
represents the fish seen throughout the main game loop
adds to the GameObject class by adding a native boolean and points system
 */
public class Fish extends GameObject {
    ///////////////////////////////////////////////////////////////////////////////
    //Attributes
    private boolean isNative;
    private int points = 100;
    private double xVel;
    private double yVel;
    private double netX = 0;
    private double netY = 0;
    ///////////////////////////////////////////////////////////////////////////////
    //Getters

    /**
     * Getter for isNative
     * @return
     */
    public boolean getIsNative() { return isNative; }

    /**
     * Setter for isNative
     * @param aNative
     */
    public void setNative(boolean aNative) {isNative = aNative; }

    /**
     * Getter for points
     * @return
     */
    public int getPoints() { return points; }

    /**
     * Getter for getWeight
     * @return
     */
    public int getWeight(){ return 10; }

    /**
     * Setter for xVel
     * @param xVel
     */
    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    /**
     * Getter for xVel
     * @return
     */
    public double getxVel() {
        return xVel;
    }

    /**
     * Getter for yVel
     * @return
     */
    public double getyVel() {
        return yVel;
    }

    /**
     * Setter for yVel
     * @param yVel
     */
    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    /**
     * Getter for netX
     * @return
     */
    public double getNetX() {
        return netX;
    }

    /**
     * Setter for netX
     * @param netX
     */
    public void setNetX(double netX) {
        this.netX = netX;
    }

    /**
     * Getter for netY
     * @return
     */
    public double getNetY() {
        return netY;
    }

    /**
     * Setter for netY
     * @param netY
     */
    public void setNetY(double netY) {
        this.netY = netY;
    }
    ///////////////////////////////////////////////////////////////////////////////
    //CONSTRUCTOR

    /**
     * Constructor for Fish
     * @param controller
     * @param location
     * @param type
     * @param state
     * @param isNative
     * @param xv
     * @param yv
     */
    public Fish(Controller controller, Dimension location, Type type, State state, boolean isNative, double xv, double yv) {
        super(controller, location, type, state, new Dimension(98, 64), 11);
        xVel = xv;
        yVel = yv;
        this.isNative = isNative;



    }

}