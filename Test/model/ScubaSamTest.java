package model;

import controller.ControlListener;
import controller.FishController;
import controller.ScubaSamController;
import controller.SoundController;
import main.Model;
import main.View;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ScubaSamTest {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());
    Fish my_fish = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.STRIPEDBASS, State.IDLE, true, 10, 10);
    ScubaSam sam = new ScubaSam(new ScubaSamController(listener), new Dimension(0,0));
    @Test
    public void getNet() {
        // check to see if all of the initial conditions of Sam's net are met
        assertNotNull(sam.getNet());
        assertEquals(50,sam.getNet().getMaxWeight());
        assertEquals(0,sam.getNet().getWeight());
    }

    @Test
    public void getController() {
        assertNotNull(sam.getController());
    }
}