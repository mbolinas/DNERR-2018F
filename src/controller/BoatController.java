package controller;

import model.GameObject;

import java.awt.Dimension;
import java.io.Serializable;

public class BoatController implements Controller, Serializable {

    /**
     * Update the location of the boat
     * @param itself
     * @param size
     */
    @Override
    public void update(GameObject itself, Dimension size){
            itself.setLocation(new Dimension((int) size.getWidth()/2,0));
    }
}
