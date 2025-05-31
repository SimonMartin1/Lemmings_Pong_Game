package Proyecto.games.Pong_game.Model;
import java.util.Random;

public class BallModel {

    private static final int LEFT_PADDLE_X_LIMIT = 50;
    private static final int LEFT_GOAL_LIMIT = -100;
    private static final int RIGHT_PADDLE_X_LIMIT = 745;
    private static final int RIGHT_GOAL_LIMIT = 900;
    private static final int TOP_BOUNDARY = 30;
    private static final int BOTTOM_BOUNDARY = 570;
    private static final int PADDLE_HEIGHT = 120;
    private static final double RESET_POS_X = 370;
    private static final double RESET_POS_Y = 330;

    double posX;
    double posY;
    double dirX;
    double dirY;
    double speed;
    Random rand = new Random();
    PaddleModel paddleLeftModel;
    PaddleModel paddleRightModel;
    ScoreManagerModel scoreManagerModel;

    double generateRandomAngle(){
        if (rand.nextBoolean()) {
            return Math.toRadians(-45 + rand.nextDouble() * 90); // de -45 a 45
        } else {
            return Math.toRadians(135 + rand.nextDouble() * 90); // de 135 a 225
        }
    }

    public BallModel(double startX, double startY, double speed, PaddleModel paddleLeftModel, PaddleModel paddleRightModel,ScoreManagerModel scoreManagerModel){
        this.posX = startX;
        this.posY = startY;
        this.speed = speed; // la magnitud del vector
        double angle;
        this.paddleLeftModel = paddleLeftModel;
        this.paddleRightModel = paddleRightModel;
        this.scoreManagerModel = scoreManagerModel;

        angle = generateRandomAngle();

        this.dirX = Math.cos(angle);
        this.dirY = Math.sin(angle); // en realidad lo multiplico por el vector unitario
    }
    public double getPosY(){
        return posY;
    }
    public double getPosX(){
        return posX;
    }

    public void update(){
        posX += dirX * speed;
        posY += dirY * speed;


        if (posX <= LEFT_PADDLE_X_LIMIT ) {
            double paddleY = paddleLeftModel.getY();

            if (posY >= paddleY && posY <= paddleY + PADDLE_HEIGHT) {
                dirX *= -1;
            }
        }


        if (posX <= LEFT_GOAL_LIMIT) {
            reset();
            scoreManagerModel.refreshRightPoints();
        }

        if (posY >= BOTTOM_BOUNDARY || posY <= TOP_BOUNDARY){
            dirY *= -1;
        }

        if (posX >= RIGHT_PADDLE_X_LIMIT) {
            double paddleY = paddleRightModel.getY();
            if (posY >= paddleY && posY <= paddleY + PADDLE_HEIGHT) {
                dirX *= -1;
            }
        }

        if (posX >= RIGHT_GOAL_LIMIT) {
            reset();
            scoreManagerModel.refreshLeftPoints();
        }

    }

    public void reset(){
        double angle = generateRandomAngle();

        this.dirX = Math.cos(angle);
        this.dirY = Math.sin(angle);
        this.posX = RESET_POS_X;
        this.posY = RESET_POS_Y;


    }
}
