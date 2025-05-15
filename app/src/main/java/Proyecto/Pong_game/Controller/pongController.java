package Proyecto.Pong_game.Controller;

import Proyecto.Pong_game.Model.pongModel;
import Proyecto.Pong_game.View.PongView;

public class pongController {
    private pongModel model;
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

    public pongController(pongModel model, PongView view) {
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
