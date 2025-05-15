package Proyecto.games.Pong_game.Model;



public class PongModel {
    
    private int player1Y;
    private int player2Y;
    private int ballX;
    private int ballY;
    private int ballSpeedX;
    private int ballSpeedY;
    private int paddleSpeed;
    private int player1Score;
    private int player2Score;

    public PongModel() {
        this.player1Y = 0;
        this.player2Y = 0;
        this.ballX = 0;
        this.ballY = 0;
        this.ballSpeedX = 0;
        this.ballSpeedY = 0;
        this.paddleSpeed = 0;
        this.player1Score = 0;
        this.player2Score = 0;

    }

    public void setPlayer1Y(int player1Y) {
        this.player1Y = player1Y;
    }
    public int getPlayer1Y() {
        return player1Y;
    }
    public void setPlayer2Y(int player2Y) {
        this.player2Y = player2Y;
    }
    public int getPlayer2Y() {
        return player2Y;
    }
    public void setBallX(int ballX) {
        this.ballX = ballX;
    }
    public int getBallX() {
        return ballX;
    }
    public void setBallY(int ballY) {
        this.ballY = ballY;
    }
    public int getBallY() {
        return ballY;
    }
    public void setBallSpeedX(int ballSpeedX) {
        this.ballSpeedX = ballSpeedX;
    }
    public int getBallSpeedX() {
        return ballSpeedX;
    }
    public void setBallSpeedY(int ballSpeedY) {
        this.ballSpeedY = ballSpeedY;
    }
    public int getBallSpeedY() {
        return ballSpeedY;
    }
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }
    public int getPaddleSpeed() {
        return paddleSpeed;
    }
    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }
    public int getPlayer1Score() {
        return player1Score;
    }
    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }
    public int getPlayer2Score() {
        return player2Score;
    }

}
