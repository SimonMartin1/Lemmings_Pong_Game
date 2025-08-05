package Proyecto.games.New_Lemmings_game.utils;

import Proyecto.games.New_Pong_game.utils.BallSkin;
import Proyecto.games.New_Pong_game.utils.Difficult;
import Proyecto.games.New_Pong_game.utils.PitchSkin;
import Proyecto.games.New_Pong_game.utils.Track;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigLemmings {
    private Properties properties;
    private boolean musicOff;
    private LemmingSkin lemmingSkin;

    public ConfigLemmings() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("app/src/main/java/Proyecto/games/New_Lemmings_game/config.properties"));
        } catch (IOException err) {
            System.out.println("No se pudo leer el archivo config");
            properties = new Properties(); // Para evitar nulls
        }

        // Lectura con valores por defecto
        lemmingSkin =  LemmingSkin.values()[Integer.parseInt(properties.getProperty("lemmings.skin", "0"))];
        musicOff = Boolean.parseBoolean(properties.getProperty("musicOff", "false"));
    }

    public boolean isMusicOff() {
        return musicOff;
    }

    public void setMusicOff(boolean musicOff) {
        this.musicOff = musicOff;
        properties.setProperty("musicOff", Boolean.toString(musicOff));
        saveToFile();
    }


    public void setLemmingSkin(LemmingSkin lemmingSkin) {
        this.lemmingSkin = lemmingSkin;
        properties.setProperty("lemmings.skin", Integer.toString(lemmingSkin.ordinal()));
        saveToFile();
    }

    public  LemmingSkin getLemmingSkin() {return lemmingSkin;}

    // Opcional: para guardar los cambios en el archivo
    public void saveToFile() {
        try (var out = new java.io.FileOutputStream("app/src/main/java/Proyecto/games/New_Lemmings_game/config.properties")) {
            properties.store(out, "Configuraciones actualizadas");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de configuración");
        }
    }
}