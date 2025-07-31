package Proyecto.games.New_Pong_game;

import Proyecto.games.New_Pong_game.utils.SkinBall;
import Proyecto.games.New_Pong_game.utils.SoundManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ball implements Drawable {

    private BufferedImage spriteSheet;
    private final int frameWidth = 32;
    private final int frameHeight = 32;

    private double posX;
    private double posY;
    private double dirX;
    private double dirY;
    private double speed;
    private boolean paused = false;
    private final double initialSpeed;
    private int screenWidth;

    private double RESET_POS_X;
    private double RESET_POS_Y;
    private final SkinBall skinBall;
    private final SoundManager soundManager;
    private final Paddle leftPaddle;
    private final Paddle rightPaddle;
    private final ScoreManager scoreManager;

    private static  int LEFT_PADDLE_X_LIMIT = 10;
    private static  int LEFT_GOAL_LIMIT;
    private static  int RIGHT_PADDLE_X_LIMIT;
    private static  int RIGHT_GOAL_LIMIT;
    private static  int TOP_BOUNDARY;
    private static  int BOTTOM_BOUNDARY;
    private static  int PADDLE_HEIGHT;

    Random rand = new Random();


    public Ball(int screenWidth, int screenHeight, double startX, double startY, double speed, Paddle leftPaddle, Paddle rightPaddle, ScoreManager scoreManager, SkinBall skinBall, SoundManager soundManager) {
        this.posX = startX;
        this.posY = startY;
        this.speed = speed;
        this.initialSpeed = speed;

        this.skinBall = skinBall;
        this.soundManager = soundManager;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.scoreManager = scoreManager;
        this.screenWidth = screenWidth;
        RIGHT_PADDLE_X_LIMIT = (int)(screenWidth - (screenWidth * 0.03));
        TOP_BOUNDARY = (int)(screenWidth * 0.04);
        BOTTOM_BOUNDARY = screenHeight - TOP_BOUNDARY;
        PADDLE_HEIGHT = (int)(screenHeight * .25);
        RESET_POS_X = (int)(screenHeight * .45);
        RESET_POS_Y = (int)(screenHeight * .40);


        double angle = generateRandomAngle();

        this.dirX = Math.cos(angle);
        this.dirY = Math.sin(angle);


        RIGHT_GOAL_LIMIT=screenWidth+100;
        LEFT_GOAL_LIMIT=-100;
    }



    public void reset() {
        double angle = generateRandomAngle();
        this.dirX = Math.cos(angle);
        this.dirY = Math.sin(angle);
        this.posX = RESET_POS_X;
        this.posY = RESET_POS_Y;
        this.speed = this.initialSpeed;
    }

    private void reproduceBounceBall() {
        //soundManager.playSoundEffect("app/src/main/java/Proyecto/games/Pong_game/resources/bounce.wav");
    }

    private void bounceOffPaddle(double paddleY, double paddleHeight) {
        double relativeIntersectY = (this.posY - (paddleY + paddleHeight / 2.0)) / (paddleHeight / 2.0);
        this.dirY = relativeIntersectY * 0.7;
        this.reverseDirX();
    }

    public void pause() {
        this.paused = !this.paused;
    }

    @Override
    public void draw(Graphics2D g) {

        switch (skinBall) {
            case DEFAULT -> g.setColor(Color.ORANGE);
            case CRAZY -> g.setColor(new Color(randomNumber(), randomNumber(), randomNumber()));
        }

        g.fillOval((int) (posX - frameWidth / 2), (int) (posY - frameHeight / 2), frameWidth, frameHeight);
    }

    private static int randomNumber() {
        return 50 + (int) (Math.random() * (256 - 50));
    }


    // !IMPORTANT - Funciones de trayectoria de la ball

    public void update() {
        move();

        handleLeftPaddleCollision();
        handleLeftGoal();
        handleWallCollision();
        handleRightPaddleCollision();
        handleRightGoal();
    }

    private void handleLeftPaddleCollision() {
        if(getPosX() < 5){
            handleRightGoal();
        }
        else if (getDirX() < 0 && getPosX() - 50 <= LEFT_PADDLE_X_LIMIT) {
            double paddleY = leftPaddle.getY();

            if (isCollidingWithPaddle(getPosY(), paddleY)) {
                bounceOffPaddle(paddleY, PADDLE_HEIGHT);
                increaseSpeed();
                reproduceBounceBall();
            }
        }
    }

    private void handleRightPaddleCollision() {


        if(getPosX() > screenWidth - 5){
            handleLeftGoal();
        }
        else if (getDirX() > 0 && getPosX() + 15 >= RIGHT_PADDLE_X_LIMIT) {
            double paddleY = rightPaddle.getY() ;

            if (isCollidingWithPaddle(getPosY(), paddleY)) {
                bounceOffPaddle(paddleY, PADDLE_HEIGHT);
                increaseSpeed();
                reproduceBounceBall();

            }
        }
    }

    private void handleLeftGoal() {
        if (getPosX() <= LEFT_GOAL_LIMIT) {
            reset();
            leftPaddle.reset();
            rightPaddle.reset();
            scoreManager.refreshRightPoints();
        }
    }

    private void handleWallCollision() {
        if (getPosY() >= BOTTOM_BOUNDARY) {
            reverseDirY();
            reproduceBounceBall();
            setPosY(BOTTOM_BOUNDARY - 1);
        } else if (getPosY() <= TOP_BOUNDARY) {
            reverseDirY();
            reproduceBounceBall();
            setPosY(TOP_BOUNDARY + 1);
        }
    }

    private void handleRightGoal() {
        if (getPosX() >= RIGHT_GOAL_LIMIT) {
            reset();
            leftPaddle.reset();
            rightPaddle.reset();
            scoreManager.refreshLeftPoints();
        }
    }

    private boolean isCollidingWithPaddle(double ballY, double paddleY) {
        double ballRadiusY = 15;

        return ballY + ballRadiusY >= paddleY && ballY - ballRadiusY <= paddleY + PADDLE_HEIGHT;
    }

    public void move() {
        if (!paused) {
            this.posX += this.dirX * this.speed;
            this.posY += this.dirY * this.speed;
        }
    }

    private void reverseDirX() {
        this.dirX *= -1;
    }
    private void reverseDirY() {
        this.dirY *= -1;
    }
    private void increaseSpeed() {
        this.speed += .5;
    }

    double generateRandomAngle(){
        if (rand.nextBoolean()) {
            return Math.toRadians(-45 + rand.nextDouble() * 90); // de -45 a 45
        } else {
            return Math.toRadians(135 + rand.nextDouble() * 90); // de 135 a 225
        }
    }

    /*public void updateSize(int width, int height){
        RESET_POS_X=width/2;
        RESET_POS_Y=height/2;
    }*/



    // !IMPORTANT - Getters & Setters

    public double getPosY(){
        return posY;
    }
    public double getPosX(){
        return posX;
    }
    public double getDirX(){ return dirX; }
    public double getDirY(){ return dirY; }


    public void setPosY(double newPosY) {
        this.posY = newPosY;
    }
}
