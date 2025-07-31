package Proyecto.games.New_Pong_game;

import Proyecto.games.New_Pong_game.utils.Player;

import java.awt.*;

public class ScoreManager implements Drawable{
    private int screenWidth;
    private int pointsLeft = 0;
    private int pointsRight =0;
    private int maxPoints = 5;
    private boolean paused = false;
    private Player winner;

    public ScoreManager(int screenWidth, int maxPoints) {
        this.screenWidth = screenWidth;
        this.maxPoints = maxPoints;
    }

    public void refreshLeftPoints(){
        if(!paused){
            pointsLeft++;

            if(pointsLeft == maxPoints) winner = Player.LEFT;
        }
    }

    public void refreshRightPoints(){
        if(!paused){
            pointsRight++;

            if(pointsRight == maxPoints) winner = Player.RIGHT;
        }
    }

    public int getPointsLeft() {
        return pointsLeft;
    }
    public int getPointsRight(){
        return pointsRight;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void reset(){
        this.pointsLeft = 0;
        this.pointsRight = 0;
        this.winner = null;
    }
    public void pause() {
        this.paused = !this.paused;
    }

    public boolean hasWinner(){ return winner != null; }

    public Player getWinner(){ return winner; }

    public void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(Integer.toString(pointsLeft),screenWidth/2-60,60);
        g.drawString(Integer.toString(pointsRight),screenWidth/2+60,60);
        g.setColor(Color.ORANGE);
        g.drawString(Integer.toString(maxPoints),screenWidth/2,60);
    }

    public void updateSize(int screenWidth){
        this.screenWidth=screenWidth;
    }
}
