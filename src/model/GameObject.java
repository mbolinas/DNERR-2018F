package model;

import controller.Controller;
import view.DrawableObject;

import java.awt.Dimension;
import java.io.Serializable;

/*
building block for every object seen in the main game loop
each GameObject has a controller that manipulates itself, as well as a location in the game,
and Type and State if necessary
 */
public abstract class GameObject extends DrawableObject implements Serializable {

    //Attributes
    private Controller controller;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //Getters

    /**
     * Getter for Controller
     * @return
     */
    public Controller getController() {
        return controller;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Constructor for GameObject
     * @param controller
     * @param location
     * @param type
     * @param state
     * @param imgSize
     * @param frameCap
     */
    public GameObject(Controller controller, Dimension location, Type type, State state, Dimension imgSize, int frameCap) {
        super(location, type, state, imgSize, frameCap);
        this.controller = controller;
    }

    /**
     * Update method for GameObject
     * @param size
     */
    public void update(Dimension size) { controller.update(this,size); }

}