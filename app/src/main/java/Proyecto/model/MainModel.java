package Proyecto.model;
import java.util.ArrayList;

<<<<<<< HEAD
import Proyecto.Games.JGame;
import Proyecto.Games.Lemmings_game.Lemmings;
import Proyecto.Games.Pong_game.Pong;
=======
import Proyecto.games.JGame;
>>>>>>> d516849ca00f5a4304919fb43b5e8fc5caffd91d

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

