package Proyecto.games.New_Lemmings_game.utils;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.utils.InputEventsTracker;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.event.KeyEvent;

public class Lemmings_InputEventsTracker extends InputEventsTracker {


    public Lemmings_InputEventsTracker(int width, int height, Lemmings game) {
        super(width, height,game.getMouse(),game.getKeyboard());
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

    public boolean detectScoreKeyboard() {
        return k.isKeyPressed(KeyEvent.VK_S);
    }
    public boolean detectLevelEditorKeyboard() {
        return k.isKeyPressed(KeyEvent.VK_E);
    }

    public boolean isMusicOnClicked() {
        return isMouseOverClickArea(width/2-125, 85, 40, 30);
    }
    public boolean isMusicOffClicked() {
        return isMouseOverClickArea(width/2-45, 85, 40, 30);
    }
    public boolean isFullScreenClicked() {
        return isMouseOverClickArea(width/2-125, 107, 40, 40);
    }
    public boolean isFullScreenOffClicked() {
        return isMouseOverClickArea(width/2-45, 107, 40, 30);
    }
    public boolean isSaveClicked() {
        return isMouseOverClickArea(width-325, height-110, 30, 30);
    }
    public boolean isCancelClicked() {
        return isMouseOverClickArea(width-245, height-110, 30, 30);
    }
    public boolean isResetClicked() {
        return isMouseOverClickArea(width-145, height-110, 30, 30);
    }
}
