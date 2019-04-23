package model;

import controller.ControlListener;
import controller.FishController;
import controller.SoundController;
import main.Model;
import main.View;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;

import static org.junit.Assert.*;

public class NetTest {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());
    Fish my_fish = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.STRIPEDBASS, State.IDLE, true, 10, 10);
    Net my_Net = new Net(50);

    @Test
    public void isFull() {
        my_Net.add(my_fish);
        // net is not full
        assertEquals(false, my_Net.isFull());
        my_Net.add(my_fish);
        my_Net.add(my_fish);
        my_Net.add(my_fish);
        my_Net.add(my_fish);
        // now net is full
        assertEquals(true, my_Net.isFull());
    }

    @Test
    public void getFishlist() {
        my_Net.add(my_fish);
        my_Net.add(my_fish);
        my_Net.add(my_fish);
        HashSet<Fish> fishies = new HashSet<>();
        fishies.add(my_fish); fishies.add(my_fish); fishies.add(my_fish);
        assertEquals(fishies,my_Net.getFishlist());
    }

    @Test
    public void setMaxWeight() {
        int initial_weight = my_Net.getMaxWeight();
        my_Net.setMaxWeight(70);
        // checks to make sure the weight was changed
        assertNotEquals(initial_weight,my_Net.getMaxWeight());
        // checks to make sure the weight was changed to the correct amount
        assertEquals(70,my_Net.getMaxWeight());
    }

    @Test
    public void getMaxWeight() {
        assertEquals(50, my_Net.getMaxWeight());
    }

    @Test
    public void getWeight() {
        // checks to make sure the default weight is correct
        assertEquals(0,my_Net.getWeight());
        // checks to make sure that adding fish to the net increases the weight being stored
        my_Net.add(my_fish);
        assertEquals(10,my_Net.getWeight());
        my_Net.add(my_fish);
        assertEquals(20,my_Net.getWeight());
    }

    @Test
    public void add() {
        int initial_caught_fish = my_Net.getFishlist().size();
        my_Net.add(my_fish);
        assertNotEquals(initial_caught_fish,my_Net.getFishlist().size());
        assertEquals(1,my_Net.getFishlist().size());
    }
}