package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.utils.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Menu extends Lemmings_Screens{
    private double blinkTime;
    private boolean showPressText = true,prevKeyPressed=false;

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
            g.drawString("Score", 140 , height-60);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Level " +chooseLevel(), width/2 - 25, 420);
            g.setStroke(new BasicStroke(10));
        g.fillPolygon(new int[]{width/2-85, width/2-65, width/2-65}, new int[] {height/2+105,height/2+95,height/2+115}, 3);
            g.fillPolygon(new int[]{width/2+110, width/2+90, width/2+90}, new int[] {height/2+102,height/2+92,height/2+112}, 3);



        if (showPressText) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Click or Enter", width/2 - 71, 480);
        }
    }

    public void update(double delta){
        blinkTime += delta;
        if (blinkTime >= 0.6) {
            showPressText = !showPressText;
            blinkTime = 0;
        }
        
        if(detectDecrease() && game.getCurrentLevel()!=0){
                game.setCurrentLevel(game.getCurrentLevel()-1);
        }


        if(detectIncrease() && game.getCurrentLevel()!=game.getLevel().size()-1){
                game.setCurrentLevel(game.getCurrentLevel()+1);
        }

        if(detecSettings()  || game.getKeyboard().isKeyPressed(KeyEvent.VK_C)){
            game.setGameState(GameState.ON_CONFIG);
        }

        if(detecPlay() || game.getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
            game.setGameState(GameState.PRE_LEVEL);
        }

        if(detectScore() || game.getKeyboard().isKeyPressed(KeyEvent.VK_S)){
            game.setGameState(GameState.ON_SCORE);
        }


    }

    public int chooseLevel(){
        int res=0;
        for(int i=0; i<game.getLevel().size(); i++){
            if(i==game.getCurrentLevel()){
                res=i+1;
            }
        }
         return res;
    }


    public boolean detectDecrease(){
        return isMouseOverClickArea(width/2-90,height/2+60 , 30, 30);
    }

    public boolean detectIncrease(){
        return isMouseOverClickArea(width/2+80,height/2+60 , 30, 30);
    }

    protected boolean detecPlay(){
        return isMouseOverClickArea(width / 2-50, height-230, 100, 60);
    }

    protected boolean detecSettings(){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }


    public boolean detectScore(){
        return isMouseOverClickArea(120, height-100, 150, 80);
    }


    protected boolean isMouseOverClickArea(int x, int y, int width, int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && unBounceClick() ;
    }

    public boolean unBounceClick(){
        boolean m = game.getMouse().isLeftButtonPressed();
        boolean justClicked = m && !prevKeyPressed;
        prevKeyPressed = m;
        return justClicked;
    }

}