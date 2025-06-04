package Proyecto.games.Pong_game.View;
import Proyecto.games.Pong_game.Pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.entropyinteractive.Mouse;


public class GameSettingsView {
    private final int width;
    private final int height;
    public boolean drawHard=false,drawMedium=false,drawEasy=true, drawTwoPlayers=false,drawWin3 = false, drawWin5 = false, drawWin7 = true, drawOff = false;
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

    public void drawmenu(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2 -140, 145, 400, 40, 20, 20); 
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
        g.drawString("7", width/2-120 , 260);
        g.drawString("5", width/2-60 , 260); 
        g.drawString("3", width/2 , 260);
        g.drawString("Save", width-325 , 550);
        g.drawString("Cancel", width-245 , 550);
        g.drawString("Reset", width-145 , 550);

        if(drawHard){
            g.setColor(Color.WHITE);
            g.fillRoundRect(width/2-15, 145, 70, 40, 20, 20);
            g.setColor(new Color(0, 0, 0, 255));
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Hard", width/2 , 170);
        }
        if(drawMedium){
            g.setColor(Color.WHITE);
            g.fillRoundRect(width/2+85, 145, 80, 40, 20, 20);
            g.setColor(new Color(0, 0, 0, 255));
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Medium", width/2+90 , 170);
        }
        if(drawEasy){
            g.setColor(Color.WHITE);
            g.fillRoundRect(width/2+190, 145, 70, 40, 20, 20);
            g.setColor(new Color(0, 0, 0, 255));
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Easy", width/2+200 , 170);
        }
        if(drawTwoPlayers){
            g.setColor(Color.WHITE);
            g.fillRoundRect(width/2-135, 190, 60, 40, 20, 20);
            g.setColor(new Color(0, 0, 0, 255));
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("On", width/2-120 , 215);
        }
        if(drawWin7){
        g.setColor(Color.WHITE);
        g.fillRoundRect(320, 240, 30, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("7", width/2-120, 260);
        }
        if(drawWin5){
        g.setColor(Color.WHITE);
        g.fillRoundRect(380, 240, 30, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("5", width/2-60, 260);
        }
        if(drawWin3){
        g.setColor(Color.WHITE);
        g.fillRoundRect(440, 240, 30, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("3", width/2, 260);
        }
        if(drawOff){
        g.setColor(Color.WHITE);
        g.fillRoundRect(430, 110, 40, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Off", 430, 125);
        }
    }

    public void setDraw(String name) {
        switch(name){
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
            
            case "Win3" -> {
            drawWin3 = true;
            drawWin5 = false;
            drawWin7 = false;
            }

            case "Win5" -> {
            drawWin3 = false;
            drawWin5 = true;
            drawWin7 = false;
            }
            case "Win7" -> {
            drawWin3 = false;
            drawWin5 = false;
            drawWin7 = true;
            }
            case "Off" -> {
            drawOff = true;
            }
        }
    }

    // --- TRACK NAME ---
    public boolean isTrackNameClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 320, by = 110, bw = 90, bh = 30; // "Track 1"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- OFF ---
    public boolean isOffClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 430, by = 110, bw = 40, bh = 30; // "Off"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- HARD ---
    public boolean isHardClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-15, by = 130, bw = 80, bh = 80; // coincide con selectHard
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- MEDIUM ---
    public boolean isMediumClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2+85, by = 130, bw = 80, bh = 80; // coincide con selectMedium
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- EASY ---
    public boolean isEasyClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2+210, by = 85, bw = 80, bh = 80; // coincide con selectEasy
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- ON ---
    public boolean isOnClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = width/2-160, by = 175, bw = 80, bh = 70; // "On"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- WINPOINTS 7 ---
    public boolean isWinPoints7Clicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 320, by = 240, bw = 30, bh = 30; // "7"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- WINPOINTS 5 ---
    public boolean isWinPoints5Clicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 380, by = 240, bw = 30, bh = 30; // "5"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- WINPOINTS 3 ---
    public boolean isWinPoints3Clicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 440, by = 240, bw = 30, bh = 30; // "3"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- SAVE ---
    public boolean isSaveClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 475, by = 530, bw = 60, bh = 30; // "Save"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- CANCEL ---
    public boolean isCancelClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 555, by = 530, bw = 70, bh = 30; // "Cancel"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }

    // --- RESET ---
    public boolean isResetClicked(Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        int bx = 655, by = 530, bw = 60, bh = 30; // "Reset"
        return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
    }
}
