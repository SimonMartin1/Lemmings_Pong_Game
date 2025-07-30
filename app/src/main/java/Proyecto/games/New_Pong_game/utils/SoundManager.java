package Proyecto.games.New_Pong_game.utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private boolean muted;
    private Clip musicClip;

    public SoundManager(boolean muted) {
        this.muted = muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
        if (muted) {
            stopMusic();
        }
    }

    public boolean isMuted() {
        return muted;
    }

    // 🎵 Música de fondo
    public void playMusic(String path, boolean loop) {
        if (muted) return;

        stopMusic(); // Detener música anterior si hay

        try {
            File musicFile = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);

            if (loop) {
                musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                musicClip.start();
            }
        } catch (Exception e) {
            System.err.println("Error al reproducir música: " + e.getMessage());
        }
    }

    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.close();
        }
    }

    public void playSoundEffect(String path) {
        if (muted) return;

        new Thread(() -> {
            try {
                File soundFile = new File(path);
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                // Cerramos el clip al terminar
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

            } catch (Exception e) {
                System.err.println("Error al reproducir efecto: " + e.getMessage());
            }
        }).start();
    }
}