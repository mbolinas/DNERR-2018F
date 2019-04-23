package main;

import controller.ControlListener;
import controller.FishController;
import controller.SoundController;
import model.*;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModelTest {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());

    @Test
    public void update() {
        long initial_time = model.getTime();
        // did objects initialize
        assertNotNull(listener);
        assertNotNull(view);
        assertNotNull(model);
        model.update(view.getSize());
        //is time passing correctly while still in menu
        assertEquals(initial_time,model.getTime());
    }

    @Test
    public void getSize() {
        assertNotNull(model.getSize());
    }

    @Test
    public void getFish() {
        assertNotNull(model.getFish());
    }

    @Test
    public void getDrawQueue() {
        assertNotNull(model.getDrawQueue());
    }

    @Test
    public void getGameState() {
        assertEquals("menu",model.getGameState());
    }

    @Test
    public void getScore() {
        assertEquals(0,model.getScore());
    }

    @Test
    public void getTime() {
        assertNotNull(model.getTime());
    }

    @Test
    public void getInfoCard() {
        assertNull(model.getInfoCard());
    }

    @Test
    public void addObject() {
        Fish my_fish = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.NORTHERNSNAKEHEAD, State.IDLE, false, 0, 0);
        model.addObject(my_fish);
        for (Fish i :model.getFish()){
            assertEquals(i,my_fish);
        }
    }

    @Test
    public void removeAll() {
        // add two fish to model and test that they were added
        Fish my_fish = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.NORTHERNSNAKEHEAD, State.IDLE, false, 0, 0);
        model.addObject(my_fish);
        model.addObject(my_fish);
        for (Fish i :model.getFish()){
            assertEquals(i,my_fish);
        }
        // add both fish to a 'to_be_removed' ArrayList and call .removeAll('to_be_removed', then check that they were removed
        ArrayList<GameObject> fish = new ArrayList<>();
        fish.add(my_fish);
        fish.add(my_fish);
        model.removeAll(fish);
        assertEquals(model.getFish(),new ArrayList<Fish>());
    }

    @Test
    public void removeObject() {
        // add fish to model and test that they were added
        Fish my_fish = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.NORTHERNSNAKEHEAD, State.IDLE, false, 0, 0);
        model.addObject(my_fish);
        for (Fish i :model.getFish()){
            assertEquals(i,my_fish);
        }
        // remove fish from model and check that it was removed
        model.removeObject(my_fish);
        assertEquals(model.getFish(),new ArrayList<Fish>());
    }
}