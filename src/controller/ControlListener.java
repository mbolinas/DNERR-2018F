package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ControlListener implements KeyListener, Serializable {

    //Attributes
    private Set<Integer> pressed;
    ////////////////////////////////////////////////////////////////////////

    //Methods

    /**
     * Constructor for ControlListener
     */
    public ControlListener(){
        pressed = new HashSet<>();
    }

    /**
     * Getter for pressed
     * @return
     */
    public Set<Integer> getPressed() {
        return pressed;
    }

    /**
     * Key even typed
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * Key event pressed
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
    }

    /**
     * Key event released
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(pressed.contains(e.getKeyCode())) pressed.remove(e.getKeyCode());
    }

}
