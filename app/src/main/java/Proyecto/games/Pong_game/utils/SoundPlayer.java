package Proyecto.games.Pong_game.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    private static Clip currentClip;
    private static Thread soundThread;

    public static synchronized void playSound(String path) {
        stopSound(); // Detiene el sonido anterior si hay uno
        soundThread = new Thread(() -> {
            try {
                File soundFile = new File(System.getProperty("user.dir"), path);
                URL soundURL = soundFile.toURI().toURL();

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                currentClip = clip;

                // Espera mientras el clip está sonando y el hilo no está interrumpido
                while (clip.isRunning() && !Thread.currentThread().isInterrupted()) {
                    Thread.sleep(50);
                }
                clip.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                // Si es InterruptedException, solo cerramos el clip
                if (currentClip != null && currentClip.isOpen()) {
                    currentClip.close();
                }
            }
        });
        soundThread.start();
    }

    public static synchronized void stopSound() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
        }
        if (soundThread != null && soundThread.isAlive()) {
            soundThread.interrupt();
            soundThread = null;
        }
    }
}