package controller;

import main.View;
import model.Fish;
import model.GameObject;
import model.FishAI;
import java.util.Collection;
import java.awt.*;


public class FishController implements Controller {

    private View view;
    private FishAI fishAI;
    private Collection<Fish> fish;
    private int xSize;
    private int ySize;

    /**
     * Fish Controller Constructor
     * @param fish
     */
    public FishController(Collection<Fish> fish) {
        this.fish = fish;
        this.xSize = view.getSize().width;
        this.ySize = view.getSize().height;
        this.fishAI = new FishAI(fish,xSize, ySize);

    }

    /**
     * Updates Fish AI
     * @param itself
     * @param size
     */
    @Override
    public void update(GameObject itself, Dimension size) {

        fishAI.update(itself, size);

    }
}
