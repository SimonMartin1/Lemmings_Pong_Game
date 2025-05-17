package Proyecto.Model;
import java.util.ArrayList;

import Proyecto.Games.JGame;

public class MainModel {
    final private ArrayList<JGame> games;

    public MainModel() {
        games = new ArrayList<>();
    }

    public void addGame(JGame game) {
        games.add(game);
    }

    public void removeGame(JGame game) {
        games.remove(game);
    }

    public ArrayList<JGame> getGames() {
        return games;
    }
}

