package Proyecto.games.Pong_game.View;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.entropyinteractive.Mouse;

import Proyecto.games.Pong_game.Pong;


public class GameSettingsView {
    private int width;
    private int height;
    public boolean drawHard, drawMedium, drawEasy, drawTwoPlayers, drawWin5, drawWin10, drawWin15, drawOff, drawTrack,prevMousePressed,drawFullScreen,setkeys=false,getKey;
    private final Pong game;


    public GameSettingsView(int width, int height, Pong game) {
        this.width = width;
        this.height = height;
        this.game = game;
    }
        public String getKeys(int option){
            String res;
        
        switch (option) {
            default -> res =KeyEvent.getKeyText(game.getPlayersKeys(1)[0]);
            case 1 -> res =KeyEvent.getKeyText(game.getPlayersKeys(1)[1]);
            case 2 -> res =KeyEvent.getKeyText(game.getPlayersKeys(1)[0]);
            case 3 -> res =KeyEvent.getKeyText(game.getPlayersKeys(2)[1]);
            case 4 -> res =KeyEvent.getKeyText(game.getPlayersKeys(2)[0]);
        }
        return res;
    }

    public String getTrack(){
        String res="";
        
        switch (game.getTrack()) {
            case TRACK1 -> res = "Track 1";
            case TRACK2 -> res = "Track 2";
            case TRACK3 -> res = "Track 3";
        }
        return res;
    }
    public String getPitchSkin(){
        String res="";
        
        switch (game.getPitchSkin()) {
            case BLACK -> res = "Black";
            case BLUE -> res = "Blue";
            case BASKET -> res = "Basket";
        }
        return res;
    }
        public String getBallSkin(){
        String res="";
        
        switch (game.getBallSkin()) {
            case NORMAL -> res = "Normal";
            case CRAZY -> res = "Crazy";
            case TENNIS -> res = "Tenis";
            case FOOTBALL -> res = "Football";
            case BASKET -> res = "Basket";
        }
        return res;
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
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2 -140, 145, 400, 40, 20, 20); 
        g.drawRoundRect(width/2-145, 325, 95, 35, 20, 20); 
        g.drawRoundRect(width/2-145, 280, 95, 35, 20, 20); 
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width/2-50 , 70);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Music", width/2-250 , 125);
        g.drawString(getTrack(), width/2-120 , 125);
        g.drawString("Off", width/2+20 , 125);
        g.drawString("1 Player", width/2-265 , 170);
        g.setColor(Color.WHITE);
        g.drawString("Difficulty", width/2-120 , 170);
        g.drawString("Hard", width/2 , 170);
        g.drawString("Medium", width/2+90 , 170);
        g.drawString("Easy", width/2+200 , 170);
        g.drawString("2 Players", width/2-265 , 215);
        g.drawString("On", width/2-120 , 215);
        g.drawString("WinPoints", width/2-265 , 260);
        g.drawString("15", width/2-120 , 260);
        g.drawString("10", width/2-60 , 260); 
        g.drawString("5", width/2 , 260);
        g.drawString("Pitch Skin", width/2-265 , 305);
        g.drawString(getPitchSkin(), width/2-125 , 305);
        g.drawString("Ball Skin", width/2-265 , 350);
        g.drawString(getBallSkin(), width/2-125 , 350);
        g.drawString("Full Screen", width/2-265 , 395);
        g.drawString("On", width/2-120 , 395);
        g.drawString("Off", width/2-40 , 395);
        g.drawString("Keys", width/2-265 , 440);
        g.drawString("Player1 Up: "+getKeys(1)+"  Down: "+getKeys(2), width/2-200 , 440);
        g.drawString("Change Keys", width/2+100 , 440);
        g.drawString("Save", width-325 , height-65);
        g.drawString("Cancel", width-245 , height-65);
        g.drawString("Reset", width-145 , height-65);
        //g.fillRect(width/2+100,420,120,30);
        //g.drawLine(0,height/2,width,height/2);
        //g.drawLine(width/2,0,width/2,height);
        //width/2+105 , by = height/2+65, bw = 125, bh = 35;
        
    

        if(drawHard){
            activeButtons(g, width/2, 170,"Hard", width/2-15, 145,70,40,20,20);
        }
        if(drawMedium){
            activeButtons(g, width/2+90 , 170,"Medium", width/2+85, 145, 80, 40,20,20);
        }
        if(drawEasy){
            activeButtons(g, width/2+200 , 170,"Easy" ,width/2+190, 145, 70, 40,20,20);
        }
        if(drawTwoPlayers){
            activeButtons(g, width/2-120 , 215,"On", width/2-125, 195, 40, 30,20,20);
            g.setColor(Color.WHITE);
            g.drawString("Player2 Up: "+getKeys(3)+"  Down: "+getKeys(4), width/2-200 , 485);
        }
        if(drawWin5){
        activeButtons(g, width/2, 260,"5", width/2-10, 240, 30, 30,10,10);
        }
        if(drawWin15){
        activeButtons(g,width/2-120, 260,"15",width/2-125, 240, 30, 30, 10, 10);
        }
        if(drawWin10){
        activeButtons(g,width/2-60, 260 ,"10",width/2-65, 240, 30, 30, 10, 10);
        }
        if(drawOff){
        activeButtons(g,width/2+25, 125,"Off", width/2+20, 105, 40, 30, 10, 10);
        }
        if(drawTrack){
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2-140, 100, 100, 40, 20, 20);
        }
        if(drawFullScreen){
            activeButtons(g, width/2-120 , 395,"On", width/2-125, 375, 40, 30, 10, 10);
        }else{
            activeButtons(g, width/2-40 , 395,"Off", width/2-45, 375, 40, 30, 10, 10);
        }
        if(setkeys){
            g.setColor(Color.BLACK);
            g.fillRect(width/2+100 ,400, 125,50);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);
            g.fillRoundRect(width/2-70, 125, 150, 40, 20, 20); 
            g.fillRoundRect(width/2-80, 185, 65, 40, 20, 20);
            g.fillRoundRect(width/2+20, 185, 65, 40, 20, 20); 
            g.fillRoundRect(width/2-70, 250, 150, 40, 20, 20);
            g.fillRoundRect(width/2+20, 310, 65, 40, 20, 20); 
            g.fillRoundRect(width/2-80, 310, 65, 40, 20, 20);
            g.fillRoundRect(width/2+75, 405, 150, 40, 20, 20); 
            g.setColor(Color.BLACK);
            g.drawString("Cancel", width/2+120 , 430);
            g.drawString("Player 1", width/2-30, 150);
            g.drawString("Player 2", width/2-30 , 275);
            g.drawString("Up", width/2+40 , 210);
            g.drawString("Down", width/2-75 , 210);
            g.drawString("Up", width/2+40 , 335);
            g.drawString("Down", width/2-75 , 335);
        }
        if(getKey){
            g.setColor(Color.BLACK);
            g.fillRect(0 ,0, width,height);
            g.setColor(Color.WHITE);
            g.drawString("Select the new key", width/2-70, 150);
        }
        
    }

    public boolean getDrawTrack(){
        return this.drawTrack;
    }

    public void setDraw(String name) {
        switch(name){

            case "Track" -> {
                drawTrack=true;
                drawOff = false;
                
            }
            case "Hard" -> {
                drawHard = true;
                drawMedium = false;
                drawEasy = false;
                drawTwoPlayers = false;
            }
            case "Medium" -> {
                drawMedium = true;
                drawHard = false;
                drawEasy = false;
                drawTwoPlayers = false;
            }
            case "Easy" -> {
                drawEasy = true;
                drawHard = false;
                drawMedium = false;
                drawTwoPlayers = false;
            }
            case "TwoPlayers" -> {
                drawTwoPlayers = true;
                drawHard = false;
                drawMedium = false;
                drawEasy = false;
            }
            
            case "Win5" -> {
            drawWin5 = true;
            drawWin10 = false;
            drawWin15 = false;
            }

            case "Win10" -> {
            drawWin5 = false;
            drawWin10 = true;
            drawWin15 = false;
            }
            case "Win15" -> {
            drawWin5 = false;
            drawWin10 = false;
            drawWin15 = true;
            }
            case "Off" -> {
            drawOff = true;
            drawTrack = false;
            }
            case "Cancel" -> {
                
            }
            case "Reset" -> {
                drawHard = false;
                drawMedium = false;
                drawEasy = true;
                drawTwoPlayers = false;
                drawWin5 = true;
                drawWin10 = false;
                drawWin15 = false;
                drawOff = false;
                drawTrack = true;
                drawFullScreen=false;
            }
            case "fullscreen" ->{drawFullScreen=true;}
            case "fullscreenOff" ->{drawFullScreen=false;}
        }
    }
        public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
    }

    private boolean isMouseJustPressed(Mouse m) {
    boolean justPressed = m.isLeftButtonPressed() && !prevMousePressed;
    prevMousePressed = m.isLeftButtonPressed();
    return  justPressed && game.getIsinsettings();
}

    public boolean mouseTracker(int x, int y, int width,int height, Mouse m){
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && isMouseJustPressed(m) && !setkeys;
    }

    public boolean mouseTrackerSetKeys(int x, int y, int width,int height, Mouse m){
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && isMouseJustPressed(m) && setkeys;
    }

    // --- TRACK NAME ---
    public boolean isTrackNameClicked(Mouse m) {
        return mouseTracker(width/2-120, 85, 105, 25, m);
    }

    // --- OFF ---
    public boolean isOffClicked(Mouse m) {
        return mouseTracker(width/2-5, 40, 60, 60, m);
    }

    // --- HARD ---
    public boolean isHardClicked(Mouse m) {
        return mouseTracker(width/2-15, 130, 80, 45, m);
    }

    // --- MEDIUM ---
    public boolean isMediumClicked(Mouse m) {
        return mouseTracker(width/2+85, 130, 80, 45, m);
    }

    // --- EASY ---
    public boolean isEasyClicked(Mouse m) {
        return mouseTracker(width/2+195, 130, 80, 45, m);
    }

    // --- ON ---
    public boolean isOnClicked(Mouse m) {
        return mouseTracker(width/2-160, 175, 60, 40, m);
    }

    // --- WINPOINTS 7 ---
    public boolean isWinPoints15Clicked(Mouse m) {
        return mouseTracker(width/2-140, 220, 40, 20, m);
    }

    // --- WINPOINTS 5 ---
    public boolean isWinPoints10Clicked(Mouse m) {
        return mouseTracker(width/2-80, 220, 40, 40, m);
    }

    // --- WINPOINTS 3 ---
    public boolean isWinPoints5Clicked(Mouse m) {
        return mouseTracker(width/2-40, 220, 40, 40, m);
    }

    // --- SAVE ---
    public boolean isSaveClicked(Mouse m) {
    return mouseTracker(width-325, 500, 30, 30, m);
    }

    // --- CANCEL ---
    public boolean isCancelClicked(Mouse m) {
    return mouseTracker(width-245, 500, 60, 30, m);
    }

    // --- RESET ---
    public boolean isResetClicked(Mouse m) {
    return mouseTracker(width-145, 500, 60, 30, m);
    }
        public boolean isFullScreenClicked(Mouse m) {
        return mouseTracker(width/2-120, height/2+50, 30, 30, m);
    }
        public boolean isFullScreenOffClicked(Mouse m) {
        return mouseTracker(width/2-60, 375, 60, 30, m);
    }

        public boolean isPitchSkinClicked(Mouse m) {
        return  mouseTracker(width/2-140, 260, 45, 35, m);
    }

        public boolean isBallSkinClicked(Mouse m) {
        return mouseTracker(width/2-140, 290, 85, 35, m);
    }
        public boolean isChangeKeysClicked(Mouse m) {
        return mouseTracker(width/2+100,390,70,30, m);
    }
    
    public boolean isCancelSetKeysClicked(Mouse m) {
        return mouseTrackerSetKeys(width/2+100, 381, 70,45,m);
    }
    public boolean isPlayer1UpClicked(Mouse m) {
        return mouseTrackerSetKeys(width/2+20, 170, 65,40,m);
    }
    public boolean isPlayer1DownClicked(Mouse m) {
        return mouseTrackerSetKeys(width/2-80, 170, 65,40,m);
    }
    public boolean isPlayer2UpClicked(Mouse m) {
        return mouseTrackerSetKeys(width/2+20, 292, 65,40,m);
    }
    public boolean isPlayer2DownClicked(Mouse m) {
        return mouseTrackerSetKeys(width/2-80, 292, 65,40,m);
    }
}
