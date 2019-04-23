package main;

import controller.ControlListener;
import controller.SoundController;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class ViewTest extends JPanel {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());
    @Test
    public void update() {
        // check that view updates to menu
        model.update(view.getSize());
        view.update(model.getDrawQueue(),model.getGameState(),model.getInfoCard(),model.getScore(),model.getResearch(),model.getTime());
        assertEquals("menu",view.getGameState());
    }

    @Test
    public void getFps() {
        assertNotNull(view.getFps());
    }

    @Test
    public void getDrawQueue() {
        assertNotNull(view.getDrawQueue());
    }

    @Test
    public void getGameState() {
        model.update(view.getSize());
        view.update(model.getDrawQueue(),model.getGameState(),model.getInfoCard(),model.getScore(),model.getResearch(),model.getTime());
        assertEquals("menu",view.getGameState());
    }

    @Test
    public void setGameState() {
        String initial_state = view.getGameState();
        view.setGameState("game");
        assertNotEquals(initial_state,view.getGameState());
    }
}