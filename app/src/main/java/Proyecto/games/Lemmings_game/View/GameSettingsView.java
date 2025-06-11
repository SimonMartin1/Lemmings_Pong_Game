package Proyecto.games.Lemmings_game.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.entropyinteractive.Mouse;

import Proyecto.games.Lemmings_game.Lemmings;


public class GameSettingsView {
    private int width;
    private int height;
    public boolean drawOn=true,prevMousePressed,drawFullScreen=true;
    private final Lemmings game;


    public GameSettingsView(int width, int height, Lemmings game) {
        this.width = width;
        this.height = height;
        this.game = game;
    }


    public void activeButtons(Graphics2D g, int xtext, int ytext, String text ,int xfill,int yfill, int width, int height, int arcx, int arcy){
            g.setColor(Color.WHITE);
            g.fillRoundRect(xfill, yfill, width, height, arcx, arcy);
            g.setColor(new Color(0, 0, 0, 255));
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(text, xtext , ytext);
    }

    public void drawmenu(Graphics2D g) {
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


    private boolean isMouseJustPressed(Mouse m) {
    boolean justPressed = m.isLeftButtonPressed() && !prevMousePressed;
    prevMousePressed = m.isLeftButtonPressed();
    return  justPressed && game.getIsinsettings() ;
}

    public boolean mouseTracker(int x, int y, int width,int height, Mouse m){
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && isMouseJustPressed(m) && game.getIsinsettings() ;
    }

    public boolean isMusicOnClicked(Mouse m) {
    return mouseTracker(width/2-125, 85, 40, 30, m);
}
public boolean isMusicOffClicked(Mouse m) {
    return mouseTracker(width/2-45, 85, 40, 30, m);
}
public boolean isFullScreenClicked(Mouse m) {
    return mouseTracker(width/2-125, 107, 40, 40, m);
}
public boolean isFullScreenOffClicked(Mouse m) {
    return mouseTracker(width/2-45, 107, 40, 30, m);
}
public boolean isSaveClicked(Mouse m) {
    return mouseTracker(width-325, height-110, 30, 30, m);
}
public boolean isCancelClicked(Mouse m) {
    return mouseTracker(width-245, height-110, 30, 30, m);
}
public boolean isResetClicked(Mouse m) {
    return mouseTracker(width-145, height-110, 30, 30, m);
}
}
