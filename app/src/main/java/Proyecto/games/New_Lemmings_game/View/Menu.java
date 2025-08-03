package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.utils.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Menu extends Lemmings_Screens{
    private double blinkTime;
    private boolean showPressText = true,prevMousePressed;

    public Menu(int width, int height, Lemmings game) {
        super(width,height,game);
    }

    public void draw(Graphics2D g) {

        Image background = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_back.png").getImage();
            g.drawImage(background, 0, 0, width, height,null);
            
            Image lemmings = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_title.png").getImage();
            g.drawImage(lemmings,width/2-width/4-90 , 125, width/2+200, height/4,null);

            Image lemmings_button = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_button.png").getImage();
            g.drawImage(lemmings_button,width/2-50 , height/2-40, 120, 120,null);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("Settings", width-250 , height-60);
            g.drawString("Score", 250 , height-60);

        if (showPressText) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click or Enter", width/2 - 71, 420);
        }
    }

    public void update(double delta){
        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }

        if(detecSettings(width - 250, height - 110, 150, 80)  || game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_CONFIG);
        }

        if(detecPlay(width / 2 - 100, 300, 200, 60) || game.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            game.setGameState(GameState.PRE_LEVEL);
        }

        if(detectScore() || game.getKeyboard().isKeyPressed(KeyEvent.VK_S)){
            game.setGameState(GameState.ON_SCORE);
        }

        if(detectLevelEditor() || game.getKeyboard().isKeyPressed(KeyEvent.VK_E)){
            game.setGameState(GameState.ON_EDITOR);
        }

    }

    protected boolean detecPlay(int x, int y, int width,int height){
        return isMouseOverClickArea(width / 2 - 100, 300, 200, 60);
    }

    protected boolean detecSettings(int x, int y, int width,int height){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }


    public boolean detectScore(){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }

    public boolean detectLevelEditor(){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
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