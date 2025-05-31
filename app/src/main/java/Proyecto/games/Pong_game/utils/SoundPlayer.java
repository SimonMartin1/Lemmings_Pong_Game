package Proyecto.games.Pong_game.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    public static synchronized void playSound(String path) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    File soundFile = new File(System.getProperty("user.dir"), path);
                    URL soundURL = soundFile.toURI().toURL();

                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}