package controller;

import sound.Sound;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class SoundController {


    //"menu","instructions","game","summary"

    //Attributes
    private String gameState;
    private String sEffect;
    private Sound sound;
    private String track;
    private Thread t1;

    //Getter

    /**
     * Getter for sEffect (sound effect variable)
     * @return
     */
    public String getsEffect() {
        return sEffect;
    }

    /**
     * Constructor for SoundController that initializes thread t1 and the menu track
     * @param gameState
     */
    //Constructor
    public SoundController(String gameState) {
        this.sEffect = "sounds/music/menuAlt.wav";
        this.gameState = gameState;
        this.sound = new Sound(sEffect);
        this.t1 = new Thread(sound);
        t1.start();
    }

    /**
     * Update method that determines what track should be played
     * depending on the current GameState
     * @param gameState
     */
    //Method
    public void update(String gameState) {
        setSound();
        switch (gameState) {
            case "menu":
                if (!sound.getSound().equals(this.sEffect)) {
                    sound.setSound("sounds/music/menuAlt.wav");
                    t1.interrupt();
                }
                break;
            case "instructions":
                sound.setSound("sounds/music/instructions.wav");
                t1.interrupt();
                break;
            case "game":
                sound.setSound(track);
                t1.interrupt();
                break;
            case "summary":
                sound.setSound("sounds/music/summary.wav");
                t1.interrupt();
                break;
        }
    }

    /**
     * Sets sound randomly so game music is not repetitive
     * @return
     */
    public String setSound(){
        int rand = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        System.out.println(rand);
        switch(rand){
            case 0:
                return track = "sounds/music/game1.wav";

            case 1:
                return track = "sounds/music/game2.wav";

            case 2:
                return track = "sounds/music/game3.wav";

            case 4:
                return track = "sounds/music/game4.wav";

            case 5:
                return track = "sounds/music/game5.wav";

        }
        return  "";
    }

}
