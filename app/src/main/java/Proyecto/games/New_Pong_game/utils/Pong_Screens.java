package Proyecto.games.New_Pong_game.utils;

import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.utils.Screen;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

public abstract class Pong_Screens extends Screen {
    protected Pong game;
    public Pong_Screens(Pong game) {
        super(game.getWidth(),game.getHeight());
        this.game=game;
    }



}
