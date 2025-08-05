package Proyecto.games.New_Pong_game;

import Proyecto.games.New_Pong_game.utils.Difficult;

public class PaddleIAController {
    private final Paddle paddleIAmodel;
    private double PADDLE_HEIGHT = 40;
    private final double FOLLOW_MARGIN = 15;
    private double percentajeKnowing;

    public PaddleIAController(Paddle paddle, Difficult difficult){
        this.paddleIAmodel = paddle;

        switch (difficult){
            case EASY -> percentajeKnowing = .1;
            case MEDIUM -> percentajeKnowing = .2;
            case HARD -> percentajeKnowing = 1;
        }
    }

    public void update(double delta, double ballX, double ballY, double dirX){
        if (ballX < 800 * percentajeKnowing) {
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


    }

    public void updateSize(int height){
        this.PADDLE_HEIGHT=height;
    }
}
