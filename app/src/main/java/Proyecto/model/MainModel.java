package Proyecto.Model;
import java.util.ArrayList;

import Proyecto.Games.JGame;
import Proyecto.Games.Lemmings_game.Lemmings;
import Proyecto.Games.Pong_game.Pong;

public class MainModel {
    final private ArrayList<JGame> games;
    public MainModel() {
        games = new ArrayList<>();
        // Add game instances to the list
        // You can add more games here
        games.add(new Lemmings("", 800, 600));
        games.add(new Pong("", 800, 600));
    }

    public ArrayList<JGame> getGames() {
        return games;
    }
}

