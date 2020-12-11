package uet.oop.bomberman.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static uet.oop.bomberman.audio.Music.Loopable.NONELOOP;

public class Music implements Runnable {

    public static final String BACKGROUND_MUSIC = "bgmusic";

    private Clip clip;

    public enum Loopable {
        NONELOOP,
        LOOP;
    }

    private Loopable loopable = NONELOOP;

    public Music(String fileName) {
        String path = "/audio/" + fileName + ".wav";

        try {
            URL defaultSound = getClass().getResource(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(defaultSound);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }

    public Loopable getLoopable() {
        return loopable;
    }

    public void setLoopable(Loopable loopable) {
        this.loopable = loopable;
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    @Override
    public void run() {
        switch (loopable) {
            case LOOP:
                this.loop();
                break;
            case NONELOOP:
                this.play();
                break;
        }
    }
}
