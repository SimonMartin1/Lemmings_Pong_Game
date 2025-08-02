package Proyecto.games.New_Pong_game.utils;

import Proyecto.games.utils.Screen;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

public abstract class Pong_Screens extends Screen {
    protected Pong_InputEventsTracker pong_inputEvents;

    public Pong_Screens(int width, int height, Mouse m, Keyboard k) {
        super(width, height);
        pong_inputEvents= new Pong_InputEventsTracker(width,height,m,k);
    }



}
