package model;

import controller.ControlListener;
import controller.SoundController;
import main.Model;
import main.View;
import org.junit.Test;

import static org.junit.Assert.*;

public class DifficultyManagerTest {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());
    DifficultyManager manager = new DifficultyManager(model);
    @Test
    public void update() {
        // indicates he player is doing very well by increasing difficulty to the max
        assertEquals(300,manager.update(10000,30));
        // indicates that the player is under performing by decreasing the difficulty by a massive margin
        assertEquals(-4800,manager.update(80000,20));
    }

    @Test
    public void addNewFish() {
        // player is doing reasonably well so adds 4 fish to the game based on their current score at the current time
        manager.update(10000,10);
        assertEquals(4,model.getFish().size());

    }

    @Test
    public void update_Complexity() {
        manager.update(10000,10);
        assertEquals(4,manager.getMIN_FISH());
        assertEquals(5,manager.getMAX_FISH());
    }
}