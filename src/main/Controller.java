package main;

import controller.ControlListener;
import controller.SoundController;
import model.FishAI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {

    //Attributes
    private Model model;
    private View view;
    private ControlListener controlListener;
    private SoundController soundController;
    private FishAI fishAI;
    private int fps;
    private int target_fps;
    private Thread t1;
    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Constructor for Controller
     */
    public Controller(){
        controlListener = new ControlListener();
        soundController = new SoundController("menu");
        view = new View(controlListener);
        target_fps = view.getFps();
        fps = target_fps;
        model = new Model(controlListener, soundController,view.getSize(), view.getDrawQueue());

    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Method
    /**
     * Runs the game
     */
    public void run(){
        long time_before;
        long time_after;
        long time_end;
        int ms_wait;
        int frame_delta = 1000/target_fps;
        System.out.println(soundController.getsEffect());
        while(true){
            for (Integer i : controlListener.getPressed()) {
                // press s to save game
                if(i==83) {
                    try {
                        String filename = "savedGame.ser";
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        out.writeObject(model);
                        out.close();
                        file.close();
                        System.out.println("game saved");
                    } catch (Exception ex) {
                        System.out.println("failed to save");
                        ex.printStackTrace();
                    }
                }
                // press l to load game
                if(i==76) {
                    // Deserialization
                    try {
                        // Reading the object from a file
                        String filename = "savedGame.ser";
                        FileInputStream file = new FileInputStream(filename);
                        ObjectInputStream in = new ObjectInputStream(file);
                        model = (Model) in.readObject();
                        model.setControlListener(controlListener);
                        model.setSoundController(soundController);
                        model.setFishAI(fishAI);
                        in.close();
                        file.close();
                    }
                    catch (Exception ex) {
                        System.out.println("No saved Game");
                        ex.printStackTrace();
                    }
                }
            }
            time_before = System.currentTimeMillis();
            model.update(view.getSize());
            view.update(model.getDrawQueue(),model.getGameState(),model.getInfoCard(),model.getScore(),model.getResearch(),model.getTime(),model.getSam());
            time_after = System.currentTimeMillis();
            ms_wait = frame_delta - (int) (time_after - time_before);
            /*
            the problem was that fps was getting overwritten when we wanted to print it out
            so we needed a way to distinguish between our target_fps and our current fps
             */
            try {
                if(ms_wait > 0) Thread.sleep(ms_wait);
                time_end = System.currentTimeMillis();
                fps = 1000/(int)(time_end - time_before);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("fps: " + fps);
        }
    }
}