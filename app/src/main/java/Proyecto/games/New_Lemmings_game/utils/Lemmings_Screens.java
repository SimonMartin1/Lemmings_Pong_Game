package Proyecto.games.New_Lemmings_game.utils;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Pong_game.utils.Pong_InputEventsTracker;
import Proyecto.games.utils.Screen;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

public abstract class Lemmings_Screens extends Screen {
    protected Lemmings_InputEventsTracker lemmings_inputEvents;
    protected Lemmings game;
    public Lemmings_Screens(int width, int height, Lemmings game) {
        super(width, height);
        lemmings_inputEvents=new Lemmings_InputEventsTracker(width,height,game);
        this.game=game;
    }

}
