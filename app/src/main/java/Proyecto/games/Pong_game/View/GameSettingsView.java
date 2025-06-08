package Proyecto.games.Pong_game.View;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.entropyinteractive.Mouse;

import Proyecto.games.Pong_game.Pong;


public class GameSettingsView {
    private final int width;
    private final int height;
    public boolean drawHard, drawMedium, drawEasy, drawTwoPlayers, drawWin5, drawWin10, drawWin15, drawOff, drawTrack, nextTrack ,prevMousePressed,drawFullScreen,nextPitchSkin,nextBallskin;
    private final Pong game;

    public GameSettingsView(int width, int height, Pong game) {
        this.width = width;
        this.height = height;
        this.game = game;
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
        g.drawString(getPitchSkin(), width/2-120 , 305);
        g.drawString("Ball Skin", width/2-265 , 350);
        g.drawString(getBallSkin(), width/2-120 , 350);
        g.drawString("Full Screen", width/2-265 , 395);
        g.drawString("On", width/2-120 , 395);
        g.drawString("Off", width/2-40 , 395);
        g.drawString("Keys", width/2-265 , 440);
        g.drawString("Change Keys", width/2+20 , 440);
        g.drawString("Save", width-325 , 550);
        g.drawString("Cancel", width-245 , 550);
        g.drawString("Reset", width-145 , 550);
        g.fillRoundRect(width/2-50, 365, 80, 40, 20, 20);

    

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
            activeButtons(g, width/2-120 , 215,"On", width/2-135, 190, 60, 40,20,20);
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
        activeButtons(g,430, 125,"Off", 425, 105, 40, 30, 10, 10);
        }
        if(drawTrack){
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2-140, 100, 100, 40, 20, 20); 
        }
        if(nextTrack){
            g.drawString(getTrack(), width/2-120 , 125);
        }
        if (nextBallskin) {
            g.drawString(getBallSkin(), width/2-120 , 350);
        }
        if(drawFullScreen){
            activeButtons(g, width/2-120 , 395,"On", 425, 105, 40, 30, 10, 10);
        }
        if(!drawFullScreen){
            activeButtons(g, width/2-90 , 395,"On", 425, 105, 40, 30, 10, 10);
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
            case "nextTrack" -> {
                nextTrack=true;
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
            nextTrack = false;
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
    private boolean isMouseJustPressed(Mouse m) {
    boolean justPressed = m.isLeftButtonPressed() && !prevMousePressed;
    prevMousePressed = m.isLeftButtonPressed();
    return justPressed;
}

    // --- TRACK NAME ---
    public boolean isTrackNameClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-110, by = 40, bw = 60, bh = 60;
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- OFF ---
    public boolean isOffClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-5, by = 40, bw = 60, bh = 60; // "Off"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- HARD ---
    public boolean isHardClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-15, by = 130, bw = 80, bh = 45; // coincide con selectHard
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- MEDIUM ---
    public boolean isMediumClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2+85, by = 130, bw = 80, bh = 45; // coincide con selectMedium
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- EASY ---
    public boolean isEasyClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2+195, by = 130, bw = 80, bh = 45; // coincide con selectEasy
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- ON ---
    public boolean isOnClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-160, by = 175, bw = 60, bh = 40; // "On"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- WINPOINTS 7 ---
    public boolean isWinPoints15Clicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-140, by = 205, bw = 40, bh = 40; // "7"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- WINPOINTS 5 ---
    public boolean isWinPoints10Clicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-80, by = 205, bw = 40, bh = 40; // "5"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- WINPOINTS 3 ---
    public boolean isWinPoints5Clicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-40, by = 205, bw = 40, bh = 40; // "3"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- SAVE ---
    public boolean isSaveClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width-325, by = 500, bw = 60, bh = 30; // "Save"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- CANCEL ---
    public boolean isCancelClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 555, by = 500, bw = 70, bh = 30; // "Cancel"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

    // --- RESET ---
    public boolean isResetClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 655, by = 500, bw = 60, bh = 30; // "Reset"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }
        public boolean isFullScreenClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx =width/2-120 , by = 395, bw = 40, bh = 40; 
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }
        public boolean isFullScreenOffClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-45, by = 370, bw = 40, bh = 40; 
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

        public boolean isPitchskinClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-140, by = 280, bw = 85, bh = 35; 
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }

        public boolean isBallSkinClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-140, by = 325, bw = 85, bh = 35; 
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && isMouseJustPressed(m) && game.getIsinsettings();
    }
}
