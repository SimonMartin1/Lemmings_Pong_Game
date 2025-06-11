package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.BallModel;
import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.Model.ScoreManagerModel;

public class BallController {
    private final BallModel ballModel;
    private final PaddleModel paddleLeftModel;
    private final PaddleModel paddleRightModel;
    private final ScoreManagerModel scoreManagerModel;

    private static  int LEFT_PADDLE_X_LIMIT = 10;
    private static  int LEFT_GOAL_LIMIT;
    private static  int RIGHT_PADDLE_X_LIMIT = 775;
    private static  int RIGHT_GOAL_LIMIT;
    private static  int TOP_BOUNDARY = 30;
    private static  int BOTTOM_BOUNDARY = 570;
    private static  int PADDLE_HEIGHT = 120;
    private int width,height;
    private int offsetY = 50; 

    public BallController(BallModel ballModel, PaddleModel paddleLeftModel, PaddleModel paddleRightModel, ScoreManagerModel scoreManagerModel, int width, int height) {
        this.width=width;
        this.height=height;
        this.ballModel = ballModel;
        this.paddleLeftModel = paddleLeftModel;
        this.paddleRightModel = paddleRightModel;
        this.scoreManagerModel = scoreManagerModel;
        RIGHT_GOAL_LIMIT=width+100;
        LEFT_GOAL_LIMIT=-100;
    }

    public void updateSize(int width, int height){
        this.width=width;
        this.height=height;
        TOP_BOUNDARY=60;
        BOTTOM_BOUNDARY=height- 60;
        LEFT_PADDLE_X_LIMIT=60;
        RIGHT_PADDLE_X_LIMIT=width-90;
        RIGHT_GOAL_LIMIT=width-30;
        PADDLE_HEIGHT = height/3; 
    }
    public void update() {
        ballModel.move();

        handleLeftPaddleCollision();
        handleLeftGoal();
        handleWallCollision();
        handleRightPaddleCollision();
        handleRightGoal();
    }

    private void handleLeftPaddleCollision() {
        if(ballModel.getPosX() < 5){
            handleRightGoal();
        }
        
        else if (ballModel.getDirX() < 0 && ballModel.getPosX() - 15 <= LEFT_PADDLE_X_LIMIT) {
            double paddleY = paddleLeftModel.getY();

            if (isCollidingWithPaddle(ballModel.getPosY(), paddleY)) {
                ballModel.bounceOffPaddle(paddleY, PADDLE_HEIGHT);
                ballModel.increaseSpeed();
                ballModel.reproduceBounceBall();
            }
        }
    }

    private void handleRightPaddleCollision() {

        if(ballModel.getPosX() > width-5){
            handleLeftGoal();
        }

        else if (ballModel.getDirX() > 0 && ballModel.getPosX() + 15 >= RIGHT_PADDLE_X_LIMIT) {
            double paddleY = paddleRightModel.getY() ;
            System.out.println("Ball Y: " + ballModel.getPosY());
            System.out.println("Paddle Right Y: " + paddleY);
            if (isCollidingWithPaddle(ballModel.getPosY(), paddleY)) {
                ballModel.bounceOffPaddle(paddleY, PADDLE_HEIGHT);
                ballModel.increaseSpeed();
                ballModel.reproduceBounceBall();

            }
        }
    }

    private void handleLeftGoal() {
        if (ballModel.getPosX() <= LEFT_GOAL_LIMIT) {
            ballModel.reset();
            paddleLeftModel.reset();
            paddleRightModel.reset();
            scoreManagerModel.refreshRightPoints();
        }
    }

    private void handleWallCollision() {
        if (ballModel.getPosY() >= BOTTOM_BOUNDARY) {
            ballModel.reverseDirY();
            ballModel.reproduceBounceBall();
            ballModel.setPosY(BOTTOM_BOUNDARY - 1);
        } else if (ballModel.getPosY() <= TOP_BOUNDARY) {
            ballModel.reverseDirY();
            ballModel.reproduceBounceBall();
            ballModel.setPosY(TOP_BOUNDARY + 1);
        }
    }

    private void handleRightGoal() {
        if (ballModel.getPosX() >= RIGHT_GOAL_LIMIT) {
            ballModel.reset();
            paddleLeftModel.reset();
            paddleRightModel.reset();
            scoreManagerModel.refreshLeftPoints();
        }
    }

    private boolean isCollidingWithPaddle(double ballY, double paddleY) {
        double ballRadiusY = 15; 
        return ballY + ballRadiusY >= paddleY && ballY - ballRadiusY <= paddleY + PADDLE_HEIGHT;
    }
}