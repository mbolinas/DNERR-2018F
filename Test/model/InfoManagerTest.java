package model;

import com.sun.org.apache.xpath.internal.operations.Mod;
import controller.ControlListener;
import controller.FishController;
import controller.SoundController;
import main.Model;
import main.View;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class InfoManagerTest {
    ControlListener listener = new ControlListener();
    SoundController soundController = new SoundController("menu");
    View view = new View(listener);
    Model model = new Model(listener,soundController,view.getSize(),view.getDrawQueue());
    Fish my_fish = new Fish(new FishController(model.getFish()), new Dimension(10, 10), Type.STRIPEDBASS, State.IDLE, true, 10, 10);
    InfoManager infoManager = new InfoManager();
    InfoCard infoCard = new InfoCard("Striped Bass", "Striped Bass are a native species of Delaware and the entire East Coast of the United States. " +
                        "They can be greater than 44 inches in length and are anadromous, which means they spend the majority of their lives at sea and only " +
                        "enter freshwater in the spring to lay eggs.",Type.STRIPEDBASS);

    @Test
    public void getCard() {
        infoManager.discover(my_fish);
        assertEquals(infoCard.getTitle(),infoManager.getCard().getTitle());
        assertEquals(infoCard.getInfo(),infoManager.getCard().getInfo());

    }

    @Test
    public void discover() {
        infoManager.discover(my_fish);
        assertNotNull(infoManager.getCard());
    }

    @Test
    public void createQuiz() {
        infoManager.createQuiz();
        assertNotNull(infoManager.getCard());
    }
}