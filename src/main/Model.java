package main;

import controller.BoatController;
import controller.ControlListener;
import controller.ScubaSamController;
import controller.SoundController;
import model.*;
import view.DrawableObject;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.*;

public class Model implements Serializable {
    ///////////////////////////////////////////////////////////////////////////////
    //Attributes
    private Collection<GameObject> objects;
    private ArrayList<Fish> fish;
    private Collection<DrawableObject> drawQueue;
    private ScubaSam sam; // The scuba diver
    private Boat boat; // research boat

    private transient ControlListener controlListener;
    private transient SoundController soundController;
    public transient FishAI fishAI;

    private DifficultyManager difficulty;
    private InfoManager infoManager;
    private InfoCard infoCard = null;
    private Dimension size; // used for the dimensions of the screen

    private String[] gameStates = {"menu", "instructions", "game", "popup", "summary"};
    private String gameState; //determines the state of the game (e.g. menu, game, summary)
    private int fishCaught = 0;
    private int score = 0;
    private int research = 0;
    private int game_count = 1;
    private boolean isMenu = false;
    private boolean isInstruct = false;
    private boolean isGame = false;
    private boolean isSum = false;
    private boolean paused;
    private long startTime;
    private long timeElapsed;

    private long gamelength = 180000; //this will make the game 180 (3 minutes) seconds long
    ////////////////////////////////////////////////////////////////////////////////////
    //Constructor

    /**
     * Constructor for model
     *
     * @param controlListener
     * @param soundController
     * @param size
     * @param drawQueue
     */
    public Model(ControlListener controlListener, SoundController soundController, Dimension size, Collection<DrawableObject> drawQueue) {
        this.size = size;
        this.drawQueue = drawQueue;
        this.controlListener = controlListener;
        this.soundController = soundController;
        gameState = gameStates[0];
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Getters

    /**
     * Getter for size
     *
     * @return
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * Getter for fish
     *
     * @return
     */
    public Collection<Fish> getFish() {
        return fish;
    }

    /**
     * Getter for drawQueue
     *
     * @return
     */
    public Collection<DrawableObject> getDrawQueue() {
        return drawQueue;
    }

    /**
     * Getter for gameState
     *
     * @return
     */
    public String getGameState() {
        return gameState;
    }

    /**
     * Getter for score
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for timeElapsed
     *
     * @return
     */
    public long getTime() {
        return timeElapsed;
    }

    /**
     * Getter for research
     *
     * @return
     */
    public int getResearch() {
        return research;
    }

    /**
     * Getter for infoCard
     *
     * @return
     */
    public InfoCard getInfoCard() {
        return infoCard;
    }

    /**
     * Getter for sam
     *
     * @return
     */
    public ScubaSam getSam() {
        return sam;
    }

    //Setters

    /**
     * Setter for gameLength
     *
     * @param gamelength
     */
    public void setGamelength(long gamelength) {
        this.gamelength = gamelength;
    }

    /**
     * Setter for controlListener used for serialization
     * @param c
     */
    public void setControlListener(ControlListener c) {
        this.controlListener = c;
    }

    /**
     * Setter for soundController used for serialization
     * @param s
     */
    public void setSoundController(SoundController s) {
        this.soundController = s;
    }

    /**
     * Setter for fishAI used for serialization
     * @param fa
     */
    public void setFishAI(FishAI fa){
        this.fishAI = fa;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methods
    // updates game based on its state

    /**
     * Updates the gameState
     *
     * @param size
     */
    public void update(Dimension size) {
        this.size = size;
        switch (gameState) {
            case "menu":
                if (!isMenu) {
                    game_count++;
                    soundController.update(gameState);
                }
                isMenu = true;
                menu();
                break;
            case "instructions":
                if (!isInstruct) {
                    game_count++;
                    soundController.update(gameState);
                }
                isInstruct = true;
                instructions();
                break;
            case "game":
                if (!isGame) {
                    game_count++;
                    soundController.update(gameState);
                }
                isGame = true;
                game();
                if (timeElapsed >= gamelength) { // checks if the time is up
                    gameState = gameStates[4];
                    break;
                }
                break;
            case "popup":
                pause();
                break;
            case "summary":
                if (!isSum) {
                    game_count++;
                    soundController.update(gameState);
                }
                isSum = true;
                summary();
                break;
        }
        if (game_count == 4) {
            game_count = 1;
            isMenu = isInstruct = isGame = isSum = false;
        }

    }

    /**
     * Sets gameState to menu
     */
    private void menu() {
        for (Integer i : controlListener.getPressed()) {
            if (i == 10) {
                gameState = gameStates[1];
            } //press Enter to go to next screen
        }
    }

    /**
     * Initialize game
     */

    private void instructions() {
        for (Integer i : controlListener.getPressed()) {
            if (i == 10) {
                gameState = gameStates[2];
                objects = new HashSet<>();
                fish = new ArrayList<>();
                this.addObject(new ScubaSam(new ScubaSamController(controlListener), new Dimension(size.width / 2, size.height / 2)));
                this.addObject(new Boat(new BoatController(), new Dimension(size.width / 2, 50), Type.BOAT, State.IDLE));
                difficulty = new DifficultyManager(this);
                infoManager = new InfoManager();
                fishCaught = 0;
                score = 0;
                research = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * Updates game object information such as net, score, and fish information
     */
    private void game() {
        if (!(paused)) { //if the game is not paused
            timeElapsed = System.currentTimeMillis() - startTime; //updates time elapsed:
            /*
            remove all out-of-bounds fish
             */
            ArrayList<GameObject> removeFish = new ArrayList<>();
            for (Fish f : fish) {
                if (f.getLocation().width - 50 > size.width || f.getLocation().width < 0) {
                    removeFish.add(f);
                }
            }
            removeAll(removeFish);

            int avoidanceDistance = difficulty.update(timeElapsed, fishCaught);
            //System.out.println(avoidanceDistance);
            /*
            for each object, call it's update() method to update location and other necessary parameters
             */

            //boat.getController().update(boat,size);
            sam.update(size);

            for (Iterator<GameObject> obj = objects.iterator(); obj.hasNext(); )
                drawQueue.add(obj.next());

            for (Fish f : fish) {
                f.setNetY(f.getyVel());
                f.setNetX(f.getxVel());
                f.update(size);
            }

            updateAndCatchFish(avoidanceDistance);
            // if ScubaSam collided with the boat, empty the net, tally points
            if (sam.getNet().getWeight() != 0 &&
                    Math.abs(boat.getLocation().getHeight() + 40 - sam.getLocation().getHeight()) < sam.getImageSize().getHeight() / 2
                    && Math.abs(boat.getLocation().getWidth() - sam.getLocation().getWidth()) < boat.getImageSize().getWidth() / 2) {
                System.out.println("sam has emptied his net");
                for (Fish fish : sam.getNet().getFishlist()) {
                    score += fish.getPoints();
                    System.out.println(score);
                    if (fish.getIsNative()) {
                        research++;
                    }
                }
                sam.getNet().empty();
            }
                    /*
                    some fish give research points!
                    get enough research points and you get a quiz for potential power ups
                     */
            if (research >= 8) {
                research -= 8;
                infoCard = infoManager.createQuiz();
                System.out.println("taking quiz");
                gameState = gameStates[3];
            }
        } else { //if the game is paused
            startTime += System.currentTimeMillis();
        }
    }

    /**
     * Changes game state to summary
     */
    private void summary() {
        System.out.println("You've made it to the summary");
        for (Integer i : controlListener.getPressed()) {
            if (i == 10) {
                gameState = gameStates[0];
            } //press Enter to go to next screen and reset time
        }
    }

    /**
     * Update function for catching fish
     * Removes fish when required
     *
     * @param avoidanceDistance
     */
    private void updateAndCatchFish(int avoidanceDistance) {
        ArrayList<GameObject> toRemove = new ArrayList<>();
        for (Fish f : fish) {
            if (f.getxVel() > 0)
                f.setNetX((f.getNetX() + 1.7) / 1.3);
            else
                f.setNetX((f.getNetX() - 1.7) / 1.3);

            if (f.getyVel() > 0)
                f.setNetY((f.getNetY() + 1.7) / 1.3);
            else
                f.setNetY((f.getNetY() - 1.7) / 1.3);

            if (Math.abs(f.getLocation().width - sam.getLocation().width) < avoidanceDistance) {
                if (f.getLocation().width < sam.getLocation().width) {
                    f.setNetX(f.getNetX() - (sam.getController().getVelAmount() / 5));
                } else
                    f.setNetX(f.getNetX() + (sam.getController().getVelAmount() / 5));
            }
            if (Math.abs(f.getLocation().height - sam.getLocation().height) < avoidanceDistance) {
                if (f.getLocation().height < sam.getLocation().height) {
                    f.setNetY(f.getNetY() - (sam.getController().getVelAmount() / 8));
                } else
                    f.setNetY(f.getNetY() + (sam.getController().getVelAmount() / 8));
            }

            if (f.getLocation().height > (.8 * size.height) && f.getNetY() > 0) {
                f.setNetY((f.getNetY() / 1.3) - 2);
            } else if (f.getLocation().height < (.35 * size.height) && f.getNetY() < 0) {
                f.setNetY((f.getNetY() / 1.3) + 2);
            }

            f.setyVel(f.getNetY());
            f.setxVel(f.getNetX());
            if (f.getxVel() < 0)
                f.setState(State.LEFT);
            else
                f.setState(State.RIGHT);
            f.setLocation(new Dimension((int) (f.getLocation().width + f.getxVel()), (int) (f.getLocation().height + f.getyVel())));


            if (Math.abs(sam.getLocation().getHeight() - f.getLocation().getHeight()) < sam.getImageSize().getHeight() / 2 &&
                    Math.abs(sam.getLocation().getWidth() - f.getLocation().getWidth()) < sam.getImageSize().getWidth() / 2) {

                if (sam.getNet().add((Fish) f)) {
                    fishCaught++;
                    //System.out.println(sam.getNet().getMaxWeight() + " : " + sam.getNet().getWeight());
                    //System.out.println("sam caught a " + object.getType()); //prints fish type if caught

                    if (infoManager.discover((Fish) f)) {
                        //System.out.println("this is the first time Sam discovered a "+object.getType());
                        toRemove.add(f);
                        infoCard = infoManager.getCard();
                        gameState = gameStates[3];

                    } else {

                        toRemove.add(f);
                    }
                }
            }

        }
        removeAll(toRemove);
    }

    // used to "stop" the in game timer

    /**
     * Pauses the game for infoCards and the in quizzes
     */
    private void pause() {
        paused = true;
        while (paused) {
            /*
            if we have a QuizCard popup, the user selects the answer they want via
            left or right arrow key
             */
            if (infoCard instanceof QuizCard) { //if it is a quiz occurring
                int answer = 0;
                try {
                    for (Integer i : controlListener.getPressed()) {
                        switch (i) {
                            case 49: //1 key corresponding to answerOne
                                System.out.println("we had a quiz! answered: 1");
                                paused = false;
                                gameState = gameStates[2];
                                answer = 1;
                                break;
                            case 50: //2 key corresponding to answerTwo
                                System.out.println("we had a quiz! answered: 2");
                                paused = false;
                                gameState = gameStates[2];
                                answer = 2;
                        }
                    }
                } catch (ConcurrentModificationException e) {

                }
                /*
                if the user got the correct answer, get a random powerup
                supported powerups: net size increase, movement speed increase
                 */
                if (answer == ((QuizCard) infoCard).getCorrectAnswerIndex()) {
                    switch ((int) (Math.random() * 2)) {
                        case 0:
                            sam.getNet().setMaxWeight(20 + sam.getNet().getMaxWeight());
                            System.out.println("sam has a bigger net now! It can now hold " + sam.getNet().getMaxWeight() + " pounds!");
                            break;
                        case 1:
                            sam.getController().setVelAmount(sam.getController().getVelAmount() + 1);
                            sam.getController().setFriction(sam.getController().getFriction() + .05);
                            System.out.println("sam received better gear and can swim much faster now!");
                    }
                }
            } else { //if it is only a popup occurring
                try {
                    for (Integer i : controlListener.getPressed()) {
                        if (i != 37 && i != 38 && i != 39 && i != 40) {
                            paused = false;
                            gameState = gameStates[2];
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    /*
                    when the user unpresses a key, the event interrupt might occur while the code
                    is iterating through the HashMap of keypresses. the event will then modify the HashMap,
                    causing a ConcurrentModificationException
                    solution: just don't check user inputs for this frame, and do it next frame
                     */
                    //paused = false;
                }
            }
        }
    }

    // used to add objects to the appropriate HashSets

    /**
     * Adds objects to the correct HashSet
     *
     * @param object
     */
    public void addObject(GameObject object) {
        switch (object.getType()) {
            case DIVER:
                sam = (ScubaSam) object;
                objects.add(object);
                break;
            case BOAT:
                boat = (Boat) object;
                objects.add(object);
                break;
            default:    //default case: the object is a Fish
                fish.add((Fish) object);
                objects.add(object);
                break;
        }
    }

    /*
    when difficulty.update() removes fish, it sends the Collection of fish to remove back here
    to remove them from the correct Collections
     */

    /**
     * when difficulty.update() removes fish, it sends the Collection of fish to remove back here
     * to remove them from the correct Collections
     *
     * @param remove
     */
    public void removeAll(ArrayList<GameObject> remove) {
        objects.removeAll(remove);
        fish.removeAll(remove);
    }

    // used to remove an object from all of the HashSets it might be in

    /**
     * used to remove an object from all of the HashSets it might be in
     * @param object
     */
    public void removeObject(GameObject object) {
        switch (object.getType()) {
            case DIVER:
                sam = null;
                objects.remove(object);
                break;
            case BOAT:
                break;
            default:    //default case: the object is a Fish
                fish.remove(object);
                objects.remove(object);
        }
    }

}



