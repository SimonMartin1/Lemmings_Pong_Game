package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.BallModel;
import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.Model.ScoreManagerModel;

public class BallController {
    private final BallModel ballModel;
    private final PaddleModel paddleLeftModel;
    private final PaddleModel paddleRightModel;
    private final ScoreManagerModel scoreManagerModel;

    private static final int LEFT_PADDLE_X_LIMIT = 10;
    private static final int LEFT_GOAL_LIMIT = -100;
    private static final int RIGHT_PADDLE_X_LIMIT = 775;
    private static final int RIGHT_GOAL_LIMIT = 900;
    private static final int TOP_BOUNDARY = 30;
    private static final int BOTTOM_BOUNDARY = 570;
    private static final int PADDLE_HEIGHT = 120;

    public BallController(BallModel ballModel, PaddleModel paddleLeftModel, PaddleModel paddleRightModel, ScoreManagerModel scoreManagerModel) {
        this.ballModel = ballModel;
        this.paddleLeftModel = paddleLeftModel;
        this.paddleRightModel = paddleRightModel;
        this.scoreManagerModel = scoreManagerModel;
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

        if(ballModel.getPosX() > 795){
            handleLeftGoal();
        }
        else if (ballModel.getDirX() > 0 && ballModel.getPosX() + 15 >= RIGHT_PADDLE_X_LIMIT) {
            double paddleY = paddleRightModel.getY();

            if (isCollidingWithPaddle(ballModel.getPosY(), paddleY)) {
                ballModel.bounceOffPaddle(paddleY, PADDLE_HEIGHT);
                ballModel.increaseSpeed();
                ballModel.reproduceBounceBall();
            }
        }
    }

    private void handleLeftGoal() {
        if (ballModel.getPosX() <= LEFT_GOAL_LIMIT + 40) {
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
        if (ballModel.getPosX() >= RIGHT_GOAL_LIMIT - 40) {
            ballModel.reset();
            paddleLeftModel.reset();
            paddleRightModel.reset();
            scoreManagerModel.refreshLeftPoints();
        }
    }

    private boolean isCollidingWithPaddle(double ballY, double paddleY) {
        double ballRadiusY = 15; // Radio vertical de la pelota
        return ballY + ballRadiusY >= paddleY && ballY - ballRadiusY <= paddleY + PADDLE_HEIGHT;
    }
}