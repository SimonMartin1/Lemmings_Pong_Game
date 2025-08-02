package Proyecto.games.New_Lemmings_game.utils;

import Proyecto.games.utils.InputEventsTracker;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

public class Lemmings_InputEventsTracker extends InputEventsTracker {


    public Lemmings_InputEventsTracker(int width, int height, Mouse mouse, Keyboard k) {
        super(width, height, mouse,k);
    }

    @Override
    public boolean detecPlay(int x, int y, int width, int height) {
        return super.detecPlay(x, y, width, height);
    }

    @Override
    public boolean detecSettings(int x, int y, int width, int height) {
        return super.detecSettings(x, y, width, height);
    }

    @Override
    public boolean detectPlayKeyboard() {
        return super.detectPlayKeyboard();
    }

    @Override
    public boolean detectSettingsKeyboard() {
        return super.detectSettingsKeyboard();
    }

    @Override
    public boolean detectPauseKeyboard() {
        return super.detectPauseKeyboard();
    }


    public boolean detectScore(){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }

    public boolean detectLevelEditor(){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }

}
