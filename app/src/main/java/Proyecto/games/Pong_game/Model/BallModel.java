package Proyecto.games.Pong_game.Model;
import Proyecto.games.Pong_game.utils.SoundPlayer;

import java.util.Random;

public class BallModel {

    private static double RESET_POS_X = 370;
    private static double RESET_POS_Y = 330;

    private double posX;
    private double posY;
    private double dirX;
    private double dirY;
    private double speed;
    private boolean paused = false;
    private final double initialSpeed;

    Random rand = new Random();

    double generateRandomAngle(){
        if (rand.nextBoolean()) {
            return Math.toRadians(-45 + rand.nextDouble() * 90); // de -45 a 45
        } else {
            return Math.toRadians(135 + rand.nextDouble() * 90); // de 135 a 225
        }
    }
            public void updateSize(int width, int height){
        RESET_POS_X=width;
        RESET_POS_Y=height;
    }

    public BallModel(double startX, double startY, double speed) {
        this.posX = startX;
        this.posY = startY;
        this.speed = speed;
        this.initialSpeed = speed;

        double angle = generateRandomAngle();

        this.dirX = Math.cos(angle);
        this.dirY = Math.sin(angle);
    }

    public double getPosY(){
        return posY;
    }

    public double getPosX(){
        return posX;
    }

    public double getDirX(){ return dirX; }

    public double getDirY(){ return dirY; }

    public void setPosY(double newPosY){
        this.posY = newPosY;
    }

    public void move() {
        if(!paused){
        this.posX += this.dirX * this.speed;
        this.posY += this.dirY * this.speed;
        }
    }

    public void reverseDirX() {
        this.dirX *= -1;
    }

    public void reverseDirY() {
        this.dirY *= -1;
    }

    public void increaseSpeed() {
        this.speed += .5;
    }

    public void reset() {
        double angle = generateRandomAngle();
        this.dirX = Math.cos(angle);
        this.dirY = Math.sin(angle);
        this.posX = RESET_POS_X;
        this.posY = RESET_POS_Y;
        this.speed = this.initialSpeed;
    }

    public void reproduceBounceBall(){
        SoundPlayer.playSound("app/src/main/java/Proyecto/games/Pong_game/resources/bounce.wav");
    }

    public void bounceOffPaddle(double paddleY, double paddleHeight) {
        double relativeIntersectY = (this.posY - (paddleY + paddleHeight / 2.0)) / (paddleHeight / 2.0);
        this.dirY = relativeIntersectY * 0.7;
        this.reverseDirX();
    }
        public void pause() {
        this.paused = !this.paused;
    }
}