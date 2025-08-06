package Proyecto.games.New_Lemmings_game.utils;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.utils.Screen;

public abstract class Lemmings_Screens extends Screen {
    protected Lemmings game;
    public Lemmings_Screens(Lemmings game) {
        super(game.getWidth(), game.getHeight());
        this.game=game;
    }

}
