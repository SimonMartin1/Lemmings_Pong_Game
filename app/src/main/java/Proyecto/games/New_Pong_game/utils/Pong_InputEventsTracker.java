package Proyecto.games.New_Pong_game.utils;

import Proyecto.games.utils.InputEventsTracker;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;


//Clase para mapear los botones dibujados y teclado en Pong

public class Pong_InputEventsTracker extends InputEventsTracker {

    public Pong_InputEventsTracker(int width, int height, Mouse m, Keyboard k){
        super(width,height,m,k);
    }

    public boolean mouseTrackerSetKeys(int x, int y, int width,int height){
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && isMouseJustPressed();
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



    //mapeo de botones configuracion/opciones

    // --- TRACK NAME ---
    public boolean isTrackNameClicked() {
        return isMouseOverClickArea(width/2-120, 85, 105, 25);
    }

    // --- OFF ---
    public boolean isOffClicked() {
        return isMouseOverClickArea(width/2-5, 40, 60, 60);
    }

    // --- HARD ---
    public boolean isHardClicked() {
        return isMouseOverClickArea(width/2-15, 130, 80, 45);
    }

    // --- MEDIUM ---
    public boolean isMediumClicked() {
        return isMouseOverClickArea(width/2+85, 130, 80, 45);
    }

    // --- EASY ---
    public boolean isEasyClicked() {
        return isMouseOverClickArea(width/2+195, 130, 80, 45);
    }

    // --- ON ---
    public boolean isOnClicked() {
        return isMouseOverClickArea(width/2-160, 175, 60, 40);
    }

    // --- WINPOINTS 15 ---
    public boolean isWinPoints15Clicked() {
        return isMouseOverClickArea(width/2-140, 220, 40, 20);
    }

    // --- WINPOINTS 10 ---
    public boolean isWinPoints10Clicked() {
        return isMouseOverClickArea(width/2-80, 220, 40, 40);
    }

    // --- WINPOINTS 5 ---
    public boolean isWinPoints5Clicked() {
        return isMouseOverClickArea(width/2-40, 220, 40, 40);
    }


    public boolean isFullScreenClicked() {
        return isMouseOverClickArea(width/2-120, height/2+50, 30, 30);
    }

    public boolean isFullScreenOffClicked() {
        return isMouseOverClickArea(width/2-60, 375, 60, 30);
    }

    public boolean isPitchSkinClicked() {
        return  isMouseOverClickArea(width/2-140, 260, 45, 35);
    }

    public boolean isBallSkinClicked() {
        return isMouseOverClickArea(width/2-140, 290, 85, 35);
    }

    public boolean isChangeKeysClicked() {
        return isMouseOverClickArea(width/2+100,390,70,30);
    }

    public boolean isCancelSetKeysClicked() {
        return mouseTrackerSetKeys(width/2+100, 381, 70,45);
    }

    // --- Change Keys Events --

    public boolean isPlayer1UpClicked() {
        return mouseTrackerSetKeys(width/2+20, 170, 65,40);
    }

    public boolean isPlayer1DownClicked() {
        return mouseTrackerSetKeys(width/2-80, 170, 65,40);
    }

    public boolean isPlayer2UpClicked() {
        return mouseTrackerSetKeys(width/2+20, 292, 65,40);
    }

    public boolean isPlayer2DownClicked() {
        return mouseTrackerSetKeys(width/2-80, 292, 65,40);
    }

    // -----------------

    // --- SAVE ---
    public boolean isSaveClicked() {
        return isMouseOverClickArea(width-325, (int) (height * .85), 30, 30);
    }

    // --- CANCEL ---
    public boolean isCancelClicked() {
        return isMouseOverClickArea(width-245, (int) (height * .85), 60, 30);
    }

    // --- RESET ---
    public boolean isResetClicked() {
        return isMouseOverClickArea(width-145, (int) (height * .85), 60, 30);
    }
}
