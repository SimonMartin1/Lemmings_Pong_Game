package Proyecto.games.New_Lemmings_game.utils;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.utils.Screen;

public abstract class Lemmings_Screens extends Screen {
    protected Lemmings game;
    public Lemmings_Screens(int width, int height, Lemmings game) {
        super(width, height);
        this.game=game;
    }

}
