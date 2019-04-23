package model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class FishAI implements Serializable{
    private Collection<Fish> fish;
    private int xSize;
    private int ySize;

    /**
     * FishAI constructor
     * @param fish
     * @param xSize
     * @param ySize
     */
    public FishAI(Collection<Fish> fish,int xSize, int ySize) {
        this.fish = fish;
        this.xSize = xSize;
        this.ySize = ySize;
    }


    /**
     * The update function is used to determine what AI function needs to be called
     * based on a fish's location
     *
     * @param itself
     * @param size
     */

    public void update(GameObject itself, Dimension size){
        Fish fish1 = (Fish) itself;
        for (int i = 0; i < fish.size(); i++) {
            Fish fish2 = ((ArrayList<Fish>) fish).get(i);
            if (fish1.equals(((ArrayList<Fish>) fish).get(i))) {
                continue;
            }

            if((fish1.getxVel() < 0 && fish2.getxVel() < 0) || (fish1.getxVel() > 0 && fish2.getxVel() > 0)){
                if (calcDistance(fish1, fish2, true)) {
                    cohesion(fish1, fish2);
                }
                if (calcDistance(fish1, fish2, false)) {
                    collide(fish1, fish2);
                }
            }
        }

        if(((Fish) itself).getxVel() > 0) itself.setState(State.RIGHT);
        else itself.setState(State.LEFT);
    }
    /**
     * Returns the distance between two fish objects
     *
     * @param fish1
     * @param fish2
     * @param method
     * @return
     */
    //calculates the distance between two fish
    public boolean calcDistance(GameObject fish1, GameObject fish2, boolean method) {

        if (method) {
            return (Math.abs(fish1.getLocation().getHeight() - fish2.getLocation().getHeight()) < fish2.getImageSize().getHeight()
                    && Math.abs(fish1.getLocation().getWidth() - fish2.getLocation().getWidth()) <
                    fish2.getImageSize().getWidth() + (xSize + ySize) * 0.4);
        } else {
            return (Math.abs(fish1.getLocation().getHeight() - fish2.getLocation().getHeight()) < fish2.getImageSize().getHeight()
                    && Math.abs(fish1.getLocation().getWidth() - fish2.getLocation().getWidth()) <
                    fish2.getImageSize().getWidth() + (xSize + ySize) * 0.10);
        }
    }

    /**
     * If a fish is too far away from it's neighbors, pull it closer
     *
     * @param fish1
     * @param fish2
     */
    public void cohesion(Fish fish1, Fish fish2) {
        //System.out.println("cohesion between: " + fish1.getxVel() + ", " + fish2.getxVel());
        double xDelta = Math.abs(fish1.getxVel() - fish2.getxVel());
        if (fish2.getLocation().width > fish1.getLocation().width) {
            fish2.setNetX(fish2.getxVel() - (xDelta * .1));
            fish1.setNetX(fish1.getxVel() + (xDelta * .1));
        } else {
            fish2.setNetX(fish2.getxVel() + (xDelta * .1));
            fish1.setNetX(fish1.getxVel() - (xDelta * .1));
        }
        double yDelta = Math.abs(fish1.getyVel() - fish2.getyVel());
        if (fish2.getLocation().height > fish1.getLocation().height) {
            fish2.setNetY(fish2.getyVel() - (yDelta * .1));
            fish1.setNetY(fish1.getyVel() + (yDelta * .1));
        } else {
            fish2.setNetY(fish2.getNetY() + (yDelta * .1));
            fish1.setNetY(fish1.getNetY() - (yDelta * .1));
        }
    }

    /**
     * If a fish is to close to another fish, push them apart
     *
     * @param fish1
     * @param fish2
     */
    public void collide(Fish fish1, Fish fish2) {
        //System.out.println("collision!");
        double eDest = Math.random() * .7 + .4;
        if (fish2.getLocation().width > fish1.getLocation().width) {
            fish2.setNetX(fish2.getNetX() + eDest);
            fish1.setNetX(fish1.getNetX() / 1.3);
        } else {
            fish1.setNetX(fish1.getNetX() + eDest);
            fish2.setNetX(fish2.getNetX() / 1.3);
        }
        if (fish2.getLocation().height > fish1.getLocation().height) {
            fish2.setNetY(fish2.getNetY() + eDest);
            fish1.setNetY(fish1.getNetY() / 1.3);
        } else {
            fish1.setNetY(fish1.getNetY() + eDest);
            fish2.setNetY(fish2.getNetY() / 1.3);
        }
    }
}
