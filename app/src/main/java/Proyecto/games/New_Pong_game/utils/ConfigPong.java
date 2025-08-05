package Proyecto.games.New_Pong_game.utils;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPong {
    private Properties properties;
    private boolean musicOff;
    private boolean isFullscreen;

    private int playerOneUp;
    private int playerOneDown;
    private int playerTwoUp;
    private int playerTwoDown;

    private boolean isVersusIA;
    private int maxPoints;
    private Difficult difficult;
    private BallSkin ballSkin;
    private PitchSkin pitchSkin;
    private Track track;

    public ConfigPong() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("app/src/main/java/Proyecto/games/New_Pong_game/config.properties"));
        } catch (IOException err) {
            System.out.println("No se pudo leer el archivo config");
            properties = new Properties(); // Para evitar nulls
        }

        // Lectura con valores por defecto
        difficult = Difficult.values()[Integer.parseInt(properties.getProperty("difficult", "0"))];
        maxPoints = Integer.parseInt(properties.getProperty("maxPoints", "10"));
        isVersusIA = Boolean.parseBoolean(properties.getProperty("isVersusIA", "true"));

        playerTwoDown = KeyEvent.getExtendedKeyCodeForChar(properties.getProperty("player2.down", "K").charAt(0));
        playerTwoUp = KeyEvent.getExtendedKeyCodeForChar(properties.getProperty("player2.up", "I").charAt(0));
        playerOneDown = KeyEvent.getExtendedKeyCodeForChar(properties.getProperty("player1.down", "S").charAt(0));
        playerOneUp = KeyEvent.getExtendedKeyCodeForChar(properties.getProperty("player1.up", "W").charAt(0));

        isFullscreen = Boolean.parseBoolean(properties.getProperty("isFullscreen", "false"));
        musicOff = Boolean.parseBoolean(properties.getProperty("musicOff", "false"));

        track = Track.values()[Integer.parseInt(properties.getProperty("track", "0"))];
        pitchSkin = PitchSkin.values()[Integer.parseInt(properties.getProperty("skin.pitch", "0"))];
        ballSkin = BallSkin.values()[Integer.parseInt(properties.getProperty("skin.ball", "0"))];
    }

    public boolean isMusicOff() {
        return musicOff;
    }

    public void setMusicOff(boolean musicOff) {
        this.musicOff = musicOff;
        properties.setProperty("musicOff", Boolean.toString(musicOff));
        saveToFile();
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        isFullscreen = fullscreen;
        properties.setProperty("isFullscreen", Boolean.toString(fullscreen));
        saveToFile();
    }

    public int getPlayerOneUp() {
        return playerOneUp;
    }

    public void setPlayerOneUp(int playerOneUp) {
        this.playerOneUp = playerOneUp;
        properties.setProperty("player1.up", Character.toString((char) playerOneUp));
        saveToFile();
    }

    public int getPlayerOneDown() {
        return playerOneDown;
    }

    public void setPlayerOneDown(int playerOneDown) {
        this.playerOneDown = playerOneDown;
        properties.setProperty("player1.down", Character.toString((char) playerOneDown));
        saveToFile();
    }

    public int getPlayerTwoUp() {
        return playerTwoUp;
    }

    public void setPlayerTwoUp(int playerTwoUp) {
        this.playerTwoUp = playerTwoUp;
        properties.setProperty("player2.up", Character.toString((char) playerTwoUp));
        saveToFile();
    }

    public int getPlayerTwoDown() {
        return playerTwoDown;
    }

    public void setPlayerTwoDown(int playerTwoDown) {
        this.playerTwoDown = playerTwoDown;
        properties.setProperty("player2.down", Character.toString((char) playerTwoDown));
        saveToFile();
    }

    public boolean isVersusIA() {
        return isVersusIA;
    }

    public void setVersusIA(boolean versusIA) {
        isVersusIA = versusIA;
        properties.setProperty("isVersusIA", Boolean.toString(versusIA));
        saveToFile();
    }

    public int getMaxPoints() {
        switch (maxPoints){
            case 2: return 10;
            case 3: return 15;

            default: return 5;
        }
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
        properties.setProperty("maxPoints", Integer.toString(maxPoints));
        saveToFile();
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public void setDifficult(Difficult difficult) {
        this.difficult = difficult;
        properties.setProperty("difficult", Integer.toString(difficult.ordinal()));
        saveToFile();
    }

    public BallSkin getBallSkin() {
        return ballSkin;
    }

    public void setBallSkin(BallSkin ballSkin) {
        this.ballSkin = ballSkin;
        properties.setProperty("skin.ball", Integer.toString(ballSkin.ordinal()));
        saveToFile();
    }

    public PitchSkin getPitchSkin() {
        return pitchSkin;
    }

    public void setPitchSkin(PitchSkin pitchSkin) {
        this.pitchSkin = pitchSkin;
        properties.setProperty("skin.pitch", Integer.toString(pitchSkin.ordinal()));
        saveToFile();
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
        properties.setProperty("track", Integer.toString(track.ordinal()));
        saveToFile();
    }

    // Opcional: para guardar los cambios en el archivo
    public void saveToFile() {
        try (var out = new java.io.FileOutputStream("app/src/main/java/Proyecto/games/New_Pong_game/config.properties")) {
            properties.store(out, "Configuraciones actualizadas");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de configuración");
        }
    }
}