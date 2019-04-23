package model;

import controller.Controller;

import java.awt.Dimension;
/*
the game's Boat!
practically a GameObject, but has it's own respective image
 */

public class Boat extends GameObject{

    /**
     * Boat Constructor
     * @param controller
     * @param location
     * @param type
     * @param state
     */
    public Boat(Controller controller, Dimension location, Type type, State state){
        super(controller,location,type,state, new Dimension(512, 459), 1);
    }

    /**
     * toString method
     * @return
     */
    @Override
    public String toString(){ return "boat"; }
}