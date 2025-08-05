package Proyecto.games.New_Lemmings_game.View;


import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.utils.Drawable;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.Screen;
import com.entropyinteractive.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Settings extends Lemmings_Screens {
    public boolean drawOn=true,drawFullScreen=true;

    private final Lemmings game;


    public Settings(int width, int height, Lemmings game) {
        super(width, height,game);
        this.game = game;
    }


    public void activeButtons(Graphics2D g, int xtext, int ytext, String text ,int xfill,int yfill, int width, int height, int arcx, int arcy){
            g.setColor(Color.WHITE);
            g.fillRoundRect(xfill, yfill, width, height, arcx, arcy);
            g.setColor(new Color(0, 0, 0, 255));
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(text, xtext , ytext);
    }
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width/2-50 , 70);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Music", width/2-250 , 125);
        g.drawString("On", width/2-120 , 125);
        g.drawString("Off", width/2-40 , 125);
        g.drawString("Full Screen", width/2-265 , 170);
        g.drawString("On", width/2-120 , 170);
        g.drawString("Off", width/2-40 , 170);
        g.drawString("Keys:   P - Pause  Enter - init Game / Start Next Level", width/2-265 , 305);
        g.drawString("Save", width-325 , height-65);
        g.drawString("Cancel", width-245 , height-65);
        g.drawString("Reset", width-145 , height-65);
        //g.fillRoundRect(width-325, height-80, 60, 30, 20, 20);
    

        if(drawOn){
        activeButtons(g,width/2-120, 125,"On", width/2-125, 105, 40, 30, 10, 10);
        }
        else{
            activeButtons(g,width/2-40, 125,"Off", width/2-45, 105, 40, 30, 10, 10);
        }
        if(drawFullScreen){
            activeButtons(g, width/2-120 , 170,"On", width/2-125, 150, 40, 30, 10, 10);
        }else{
            activeButtons(g, width/2-40 , 170,"Off", width/2-45, 150, 40, 30, 10, 10);
        }        
    }

    public void update(double delta) {
        if(isSaveClicked() || game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_MENU);
        }
    }


    public void setDraw(String name) {
        switch(name){

            case "On" -> {
                drawOn=true;
            }
            case "Off" -> {
            drawOn = false;
            }
            case "reset" -> {
            drawOn = true;
            drawFullScreen=false;
            }
            case "fullscreen" ->{drawFullScreen=true;}
            case "fullscreenOff" ->{drawFullScreen=false;}
        }
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

    protected boolean isMouseOverClickArea(int x, int y, int width, int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && game.getMouse().isLeftButtonPressed();
    }
}
