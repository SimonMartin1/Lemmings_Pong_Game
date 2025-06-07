package Proyecto.games.Pong_game.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class SettingsModel {
    private static final String CONFIG_FILE = "app\\src\\main\\java\\Proyecto\\games\\Pong_game\\utils\\pong_config.txt";

    public static void saveConfig(boolean musicOff, Track track, Difficult difficult, int maxPoints, boolean twoPlayers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE))) {
            writer.println("musicOff=" + musicOff);
            writer.println("track=" + track);
            writer.println("difficult=" + difficult);
            writer.println("maxPoints=" + maxPoints);
            writer.println("twoPlayers=" + twoPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config loadConfig() {
        Config config = new Config();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] kv = line.split("=");
                if (kv.length != 2) continue;
                switch (kv[0]) {
                    case "musicOff" -> config.musicOff = Boolean.parseBoolean(kv[1]);
                    case "track" -> config.track = Track.valueOf(kv[1]);
                    case "difficult" -> config.difficult = Difficult.valueOf(kv[1]);
                    case "maxPoints" -> config.maxPoints = Integer.parseInt(kv[1]);
                    case "twoPlayers" -> config.twoPlayers = Boolean.parseBoolean(kv[1]);
                }
            }
        } catch (IOException e) {
            saveConfig(config.musicOff, config.track, config.difficult, config.maxPoints, config.twoPlayers);
        }
        return config;
    }

    public static class Config {
        public boolean musicOff = false;
        public Track track = Track.TRACK3;
        public Difficult difficult = Difficult.MEDIUM;
        public int maxPoints = 5;
        public boolean twoPlayers = false;
    }
}
