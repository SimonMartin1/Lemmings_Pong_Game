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
    private boolean draw=false;
    private final Pong game;

    public GameSettingsView(int width, int height, Pong game) {
        this.width = width;
        this.height = height;
        this.game = game;
    }

    // public String getTrack(){
    //     String res="";
    //     if(!game.getmusicOFF()){
    //     switch (game.getTrack()) {
    //         case TRACK1 -> res = "Track 1";
    //         case TRACK2 -> res = "Track 2";
    //         case TRACK3 -> res = "Track 3";
    //     }
    // }
    //     return res;
    // }

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
        g.drawString(" ", width/2-120 , 125);
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

        
    }

    public void setDraw() {
        this.draw = !this.draw;
    }

    public void selectHard(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRoundRect(width/2, 145, 100, 40, 20, 20);
        g.setColor(new Color(255, 255, 255, 255));
        g.setFont(new Font("Arial", Font.BOLD, 18));
    }


    public boolean isTrackNameClicked(Mouse m) {
    boolean click=false;
        int mx = m.getX();
    int my = m.getY();
    int bx = width/2 - 120, by = 125-20, bw = 90, bh = 30;
    if(mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed()){
        click=true;
    }    
    return click;
}

public boolean isOffClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2 + 20, by = 125-20, bw = 40, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isHardClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2, by = 170-20, bw = 60, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isMediumClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2+90, by = 170-20, bw = 80, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isEasyClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2+200, by = 170-20, bw = 70, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isOnClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2-120, by = 215-20, bw = 40, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isSaveClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width-325, by = 550-20, bw = 60, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isCancelClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width-245, by = 550-20, bw = 70, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isResetClicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width-145, by = 550-20, bw = 60, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}
public boolean isWinPoints7Clicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2 - 120, by = 260 - 20, bw = 30, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isWinPoints5Clicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2 - 60, by = 260 - 20, bw = 30, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}

public boolean isWinPoints3Clicked(Mouse m) {
    int mx = m.getX();
    int my = m.getY();
    int bx = width/2, by = 260 - 20, bw = 30, bh = 30;
    return mx >= bx && mx <= bx + bw && my >= by && my <= by + bh && m.isLeftButtonPressed();
}
}
