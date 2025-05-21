package Proyecto.games.Pong_game.Model;
import java.util.Random;

public class BallModel {
    double posX;
    double posY;
    double dirX;
    double dirY;
    double speed;
    Random rand = new Random();
    PaddleModel paddleLeftModel;
    PaddleModel paddleRightModel;
    ScoreManagerModel scoreManagerModel;

    public BallModel(double startX, double startY, double speed, PaddleModel paddleLeftModel, PaddleModel paddleRightModel,ScoreManagerModel scoreManagerModel){
        this.posX = startX;
        this.posY = startY;
        this.speed = speed; // la magnitud del vector
        double angle;
        this.paddleLeftModel = paddleLeftModel;
        this.paddleRightModel = paddleRightModel;
        this.scoreManagerModel = scoreManagerModel;
        if (rand.nextBoolean()) {
            angle = Math.toRadians(-45 + rand.nextDouble() * 90); // de -45 a 45
        } else {
            angle = Math.toRadians(135 + rand.nextDouble() * 90); // de 135 a 225
        }

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

        if (posX <= 50) {
            double paddleY = paddleLeftModel.getY();
            double paddleHeight = 120;
            if (posY >= paddleY && posY <= paddleY + paddleHeight) {
                dirX *= -1; // rebota
            }
        }
        if (posX <= -100) {
            scoreManagerModel.refreshRightPoints();
            reset();
        }

        if (posY>=570 || posY<=30){
            dirY *= -1;
        }

        if (posX >= 745 ) {
            double paddleY = paddleRightModel.getY();
            double paddleHeight = 120;
            if (posY >= paddleY && posY <= paddleY + paddleHeight) {
                dirX *= -1; // rebota
            }
        }

        if (posX >= 900) {
            scoreManagerModel.refreshLeftPoints();
            reset();
        }
    }
    public void reset(){
        posX = 370;
        posY = 330;
    }
}
