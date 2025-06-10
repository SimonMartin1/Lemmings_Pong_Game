package Proyecto.games.Pong_game.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
    private static Clip clip;

    public static void playSound(String soundFilePath) {
        try {
            // Si ya hay un clip reproduciendo, detenerlo
            stopSound();
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al reproducir sonido: " + e.getMessage());
        }
    }

    public static void stopSound() {
    if (clip != null) {
        if (clip.isRunning()) {
            clip.stop();
        }
        // Cerrar el clip en un hilo aparte para evitar bloquear el hilo principal
        Clip oldClip = clip;
        clip = null;
        new Thread(() -> {
            oldClip.close();
        }).start();
    }
}
}
