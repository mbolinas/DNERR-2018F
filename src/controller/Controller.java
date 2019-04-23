package controller;

import model.GameObject;

import java.awt.Dimension;
import java.io.Serializable;

public interface Controller extends Serializable {

    /**
     * Interfacce method signature
     * @param itself
     * @param size
     */
    void update(GameObject itself, Dimension size);

}
