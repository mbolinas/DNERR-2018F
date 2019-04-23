package model;

import controller.ControlListener;
import controller.FishController;
import controller.SoundController;
import main.Model;
import main.View;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FishTest {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());
    Fish my_fish1 = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.BLUECATFISH, State.IDLE, false, 10, 10);
    Fish my_fish2 = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.WHITEPERCH, State.IDLE, true, 10, 10);
    @Test
    public void getIsNative() {
        assertEquals(false, my_fish1.getIsNative());
        assertEquals(true,my_fish2.getIsNative());
    }

    @Test
    public void setNative() {
        my_fish1.setNative(true);
        my_fish2.setNative(false);
        assertEquals(true,my_fish1.getIsNative());
        assertEquals(false,my_fish2.getIsNative());
    }

    @Test
    public void getPoints() {
        assertEquals(100,my_fish1.getPoints());
        assertEquals(100,my_fish2.getPoints());
    }

    @Test
    public void getWeight() {
        assertEquals(10,my_fish1.getWeight());
        assertEquals(10,my_fish2.getWeight());
    }

    @Test
    public void setxVel() {
        my_fish1.setxVel(15);
        assertEquals(15,my_fish1.getxVel(),1);
        my_fish2.setxVel(20);
        assertEquals(20,my_fish2.getxVel(),1);

    }

    @Test
    public void getxVel() {
        assertEquals(10,my_fish1.getxVel(),1);
        assertEquals(10,my_fish2.getxVel(),1);
    }

    @Test
    public void getyVel() {
        assertEquals(10,my_fish1.getyVel(),1);
        assertEquals(10,my_fish2.getyVel(),1);
    }

    @Test
    public void setyVel() {
        my_fish1.setyVel(15);
        assertEquals(15,my_fish1.getyVel(),1);
        my_fish2.setyVel(20);
        assertEquals(20,my_fish2.getyVel(),1);
    }

    @Test
    public void getNetX() {
        assertEquals(0.0,my_fish1.getNetX(),1);
        assertEquals(0.0,my_fish2.getNetX(),1);
    }

    @Test
    public void setNetX() {
        my_fish1.setNetX(10);
        my_fish2.setNetX(15);
        assertEquals(10,my_fish1.getNetX(),1);
        assertEquals(15,my_fish2.getNetX(),1);
    }

    @Test
    public void getNetY() {
        assertEquals(0.0,my_fish1.getNetY(),1);
        assertEquals(0.0,my_fish2.getNetY(),1);
    }

    @Test
    public void setNetY() {
        my_fish1.setNetY(10);
        my_fish2.setNetY(15);
        assertEquals(10,my_fish1.getNetY(),1);
        assertEquals(15,my_fish2.getNetY(),1);
    }
}