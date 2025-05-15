package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.PongModel;
import Proyecto.games.Pong_game.View.PongView;

public class PongController {
    private PongModel model;
    private PongView view;
    private int player1Score;
    private int player2Score;
    private int player1Y;
    private int player2Y;
    private int ballX;
    private int ballY;
    private int ballSpeedX;
    private int ballSpeedY;
    private int paddleSpeed;

    public PongController(PongModel model, PongView view) {
        this.model = model;
        this.view = view;
        this.player1Score = 0;
        this.player2Score = 0;
        this.player1Y = model.getPlayer1Y();
        this.player2Y = model.getPlayer2Y();
        this.ballX = model.getBallX();
        this.ballY = model.getBallY();
        this.ballSpeedX = model.getBallSpeedX();
        this.ballSpeedY = model.getBallSpeedY();
        this.paddleSpeed = model.getPaddleSpeed();
    }

}

