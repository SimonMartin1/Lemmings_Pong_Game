package Proyecto.games.New_Lemmings_game.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigLemmings {
    private Properties properties;

    public void ConfigLemmings(){
        try {
            properties = new Properties();
            properties.load(new FileInputStream("app/src/main/java/Proyecto/games/New_Pong_game/config.properties"));
        } catch (IOException err) {
            System.out.println("No se pudo leer el archivo config");
            properties = new Properties(); // Para evitar nulls
        }
    }
    public void saveToFile() {
        try (var out = new java.io.FileOutputStream("app/src/main/java/Proyecto/games/New_Pong_game/config.properties")) {
            properties.store(out, "Configuraciones actualizadas");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de configuración");
        }
    }
}
