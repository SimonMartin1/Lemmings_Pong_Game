package Proyecto.games.Pong_game.Controller;


import Proyecto.games.Pong_game.Model.PaddleModel;

public class PaddleIAController {
    private final PaddleModel paddleIAmodel;
    private double PADDLE_HEIGHT = 40;
    private final double FOLLOW_MARGIN = 15;

    public PaddleIAController(PaddleModel paddleModel){
        this.paddleIAmodel = paddleModel;
    }

    public void update(double delta, double ballX, double ballY, double dirX, double dirY){
        double paddleCenterY = paddleIAmodel.getY() + PADDLE_HEIGHT / 2.0;

        paddleIAmodel.setMoveUp(false);
        paddleIAmodel.setMoveDown(false);

        if (dirX < 0) {
            if (ballY < paddleCenterY - FOLLOW_MARGIN) {
                paddleIAmodel.setMoveUp(true);
            } else if (ballY > paddleCenterY + FOLLOW_MARGIN) {
                paddleIAmodel.setMoveDown(true);
            }
        }

        paddleIAmodel.update(delta);
    }

    public void updateSize(int height){
        this.PADDLE_HEIGHT=height;
    }
}
