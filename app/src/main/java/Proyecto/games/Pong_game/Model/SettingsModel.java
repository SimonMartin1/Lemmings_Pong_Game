package Proyecto.games.Pong_game.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Proyecto.games.Pong_game.PitchSkins;
import Proyecto.games.Pong_game.View.BallSkins;


public class SettingsModel {
    private static final String setting_FILE = "app\\src\\main\\java\\Proyecto\\games\\Pong_game\\utils\\pong_setting.txt";

    public static void saveSettings(boolean musicOff, Track track, Difficult difficult, int maxPoints, boolean twoPlayers, BallSkins ballSkin, PitchSkins pitchSkin, boolean fullScreen, int[] Player1Keys,int[] Player2Keys ) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(setting_FILE))) {
            writer.println("musicOff=" + musicOff);
            writer.println("track=" + track);
            writer.println("difficult=" + difficult);
            writer.println("maxPoints=" + maxPoints);
            writer.println("twoPlayers=" + twoPlayers);
            writer.println("ballSkin=" + ballSkin);
            writer.println("pitchSkin=" + pitchSkin);
            writer.println("fullScreen=" + fullScreen);
            writer.println("Player1Keys1=" + Player1Keys[0]);
            writer.println("Player1Keys2=" + Player1Keys[1]);
            writer.println("Player2Keys1=" + Player2Keys[0]);
            writer.println("Player2Keys2=" + Player2Keys[1]);
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
                    case "track" -> setting.track = Track.valueOf(kv[1]);
                    case "difficult" -> setting.difficult = Difficult.valueOf(kv[1]);
                    case "maxPoints" -> setting.maxPoints = Integer.parseInt(kv[1]);
                    case "twoPlayers" -> setting.twoPlayers = Boolean.parseBoolean(kv[1]);
                    case "ballSkin" -> setting.ballSkin = BallSkins.valueOf(kv[1]);
                    case "pitchSkin" -> setting.pitchSkin = PitchSkins.valueOf(kv[1]);
                    case "fullScreen" -> setting.fullScreen = Boolean.parseBoolean(kv[1]);
                    case "Player1Keys1" -> setting.Player1Keys[0] = Integer.parseInt(kv[1]);
                    case "Player1Keys2" -> setting.Player1Keys[1] = Integer.parseInt(kv[1]);
                    case "Player2Keys1" -> setting.Player2Keys[0] = Integer.parseInt(kv[1]);
                    case "Player2Keys2" -> setting.Player2Keys[1] = Integer.parseInt(kv[1]);
                }
            }
        } catch (IOException e) {
            saveSettings(setting.musicOff, setting.track, setting.difficult, setting.maxPoints, setting.twoPlayers,setting.ballSkin, setting.pitchSkin,setting.fullScreen, setting.Player1Keys,setting.Player2Keys);
        }
        return setting;
    }

    public static class Settings {
        public boolean musicOff = true;
        public Track track = Track.TRACK3;
        public Difficult difficult = Difficult.EASY;
        public int maxPoints = 5;
        public boolean twoPlayers = false, fullScreen=false;
        public BallSkins ballSkin = BallSkins.NORMAL;
        public PitchSkins pitchSkin = PitchSkins.BLACK;
        public int[] Player1Keys={87,83},Player2Keys={38,40};
    }
}
