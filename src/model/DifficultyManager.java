package model;

import controller.FishController;
import main.Model;

import java.awt.Dimension;
import java.io.Serializable;

public class DifficultyManager implements Serializable {
    private Model model;
    private FishController controller;

    private int MIN_FISH = 3;
    private int MAX_FISH = 5;
    private int MAX_FISH_SPEED = 6;
    private int MIN_FISH_SPEED = 2;

    private final int SPAWN_INTERVAL = 30;
    private int spawn_tick;

    private int fish_unlocked = 2;
    private int fish_unlock_interval = 10000;   //milliseconds between new species
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CONSTRUCTOR

    /**
     * Difficulty manager Constructor
     * @param model
     */
    public DifficultyManager(Model model) {
        this.model = model;
        controller = new FishController(model.getFish());
        spawn_tick = -1;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GETTERS

    /**
     * MIN_FISH getter
     * @return
     */
    public int getMIN_FISH() { return MIN_FISH; }

    /**
     * MAX_FISH getter
     * @return
     */
    public int getMAX_FISH() { return MAX_FISH; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //METHODS
    /*
    adds fish as necessary, removes out-of-bounds fish*/

    /**
     * Update method for difficulty manager
     * @param time_elapsed
     * @param score
     * @return
     */
    public int update(long time_elapsed, int score) {

        //add new fish EVERY game tick while there aren't enough
        while(model.getFish().size() < MIN_FISH)
            addNewFish();

        /*
        every SPAWN_INTERVAL game ticks, update complexity and add a fish*/
        spawn_tick = (spawn_tick+1)%SPAWN_INTERVAL;
        if(spawn_tick == 0 && model.getFish().size() <= MAX_FISH) {
            update_complexity(time_elapsed);

            addNewFish();
        }
        return Math.min((int) (score - (1 * (time_elapsed / 1000))) * 50, 300);
    }


    /**
     * Adds a new fish to the game
     */
    private void addNewFish(){
        /*
            determine where the fish is going to spawn*/
        int w, h;
        long xv, yv;
        if(Math.random() < .5){
            w = 0;
            xv = (int) (Math.random() * (MAX_FISH_SPEED - MIN_FISH_SPEED)) + MIN_FISH_SPEED;
        }
        else{
            w = model.getSize().width;
            xv = (int) ((Math.random() * (MAX_FISH_SPEED - MIN_FISH_SPEED)) + MIN_FISH_SPEED) * -1;
        }
        yv = (int) (Math.random() * MAX_FISH_SPEED) - (MAX_FISH_SPEED / 2);
        h = (int) ((Math.random() * model.getSize().height * .6) + (model.getSize().height * .35));

        State s;
        if(xv > 0)
            s = State.RIGHT;
        else
            s = State.LEFT;

        switch((int) (Math.random() * fish_unlocked)){
            case 0:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.BLUECATFISH, s, false, xv, yv));
                break;
            case 1:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.STRIPEDBASS, s, true, xv, yv));
                break;
            case 2:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.CHAINPICKEREL, s, false, xv, yv));
                break;
            case 3:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.FLATHEADCATFISH, s, false, xv, yv));
                break;
            case 4:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.WHITEPERCH, s, true, xv, yv));
                break;
            case 5:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.LARGEMOUTHBASS, s, false, xv, yv));
                break;
            case 6:
                model.addObject(new Fish(controller, new Dimension(w, h), Type.NORTHERNSNAKEHEAD, s, false, xv, yv));
                break;
        }
    }

    /*
    allows different species of fish to spawn, adding more as time goes on
    also increases max fish count*/

    /**
     * allows different species of fish to spawn, adding more as time goes on
     * also increases max fish count
     * @param time_elapsed
     */
    private void update_complexity(long time_elapsed){
        switch((int) (time_elapsed / fish_unlock_interval)){
            case 1:
                fish_unlocked = 3;
                MAX_FISH = 7;
                break;
            case 2:
                fish_unlocked = 4;
                MIN_FISH = 4;
                break;
            case 3:
                fish_unlocked = 5;
                MAX_FISH = 9;
                break;
            case 4:
                fish_unlocked = 6;
                MIN_FISH = 5;
                break;
            case 5:
                fish_unlocked = 7;
                MAX_FISH = 11;
                break;
        }
    }


}
