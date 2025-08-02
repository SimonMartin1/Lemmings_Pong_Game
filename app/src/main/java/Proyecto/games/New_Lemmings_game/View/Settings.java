package Proyecto.games.New_Lemmings_game.View;


import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.utils.Drawable;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.Screen;
import com.entropyinteractive.Mouse;

import java.awt.*;



public class Settings extends Screen {
    private int width;
    private int height;
    public boolean drawOn=true,prevMousePressed,drawFullScreen=true;

    private final Lemmings game;


    public Settings(int width, int height, Lemmings game) {
        super(width, height);
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

    public void update(double delta) {}


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


    private boolean isMouseJustPressed() {
    boolean justPressed = game.getMouse().isLeftButtonPressed() && !prevMousePressed;
    prevMousePressed = game.getMouse().isLeftButtonPressed() && game.getGameState().equals(GameState.ON_CONFIG);
    return  justPressed;
}

    public boolean mouseTracker(int x, int y, int width,int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && isMouseJustPressed();
    }

    public boolean isMusicOnClicked() {
    return mouseTracker(width/2-125, 85, 40, 30);
}
public boolean isMusicOffClicked() {
    return mouseTracker(width/2-45, 85, 40, 30);
}
public boolean isFullScreenClicked() {
    return mouseTracker(width/2-125, 107, 40, 40);
}
public boolean isFullScreenOffClicked() {
    return mouseTracker(width/2-45, 107, 40, 30);
}
public boolean isSaveClicked() {
    return mouseTracker(width-325, height-110, 30, 30);
}
public boolean isCancelClicked() {
    return mouseTracker(width-245, height-110, 30, 30);
}
public boolean isResetClicked() {
    return mouseTracker(width-145, height-110, 30, 30);
}
}
