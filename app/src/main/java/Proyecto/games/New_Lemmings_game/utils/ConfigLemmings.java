package Proyecto.games.New_Lemmings_game.utils;

import java.io.*;


public class ConfigLemmings {
    private static final String setting_FILE = "app\\src\\main\\java\\Proyecto\\games\\Lemmings_game\\utils\\Lemmings_setting.txt";

    public static void saveSettings(boolean musicOff, boolean fullScreen) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(setting_FILE))) {
            writer.println("musicOff=" + musicOff);
            writer.println("fullScreen=" + fullScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings loadSettings() {
        Settings setting = new Settings();
        try (BufferedReader reader = new BufferedReader(new FileReader(setting_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] kv = line.split("=");
                if (kv.length != 2) continue;
                switch (kv[0]) {
                    case "musicOff" -> setting.musicOff = Boolean.parseBoolean(kv[1]);
                    case "fullScreen" -> setting.fullScreen = Boolean.parseBoolean(kv[1]);
                }
            }
        } catch (IOException e) {
            saveSettings(setting.musicOff,setting.fullScreen);
        }
        return setting;
    }

    public static class Settings {
        public boolean musicOff = true;
        public boolean fullScreen=false;
    }
}
