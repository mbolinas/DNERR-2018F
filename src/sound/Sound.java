package sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound implements Runnable {

    private String sound;
    private File music;

    public Sound(String sound) {
        this.sound = sound;
    }


    void PlaySound(File sound) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (InterruptedException ex) {
            clip.stop();
            this.music = new File(getSound());
            PlaySound(music);
        } catch (Exception e) {

        }
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void run() {
        this.music = new File(sound);
        System.out.println(music.toString());
        PlaySound(music);
    }


}
