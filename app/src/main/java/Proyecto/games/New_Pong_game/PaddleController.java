package Proyecto.games.New_Pong_game;

import com.entropyinteractive.Keyboard;

public class PaddleController {
    private int upKey;
    private int downKey;
    private Paddle paddle;
    private Keyboard keyboard;

    public PaddleController(Paddle paddle, Keyboard keyboard, int upKey, int downKey) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update(double delta) {
        // Actualizar estado basado en teclas presionadas
        paddle.setMoveUp(keyboard.isKeyPressed(upKey));
        paddle.setMoveDown(keyboard.isKeyPressed(downKey));

        paddle.update(delta);
    }

    public void setPaddleKeys(int upKey, int downKey){
        this.upKey=upKey;
        this.downKey=downKey;
    }
}
