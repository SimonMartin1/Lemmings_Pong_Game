package Proyecto.games.Pong_game.Controller;

import com.entropyinteractive.Keyboard;

import Proyecto.games.Pong_game.Model.PaddleModel;


public class PaddleController {
    private final PaddleModel paddleModel;
    private final Keyboard keyboard;
    private int upKey;
    private int downKey;

    public PaddleController(PaddleModel paddleModel, Keyboard keyboard, int upKey, int downKey) {
        this.paddleModel = paddleModel;
        this.keyboard = keyboard;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update() {
        // Actualizar estado basado en teclas presionadas
        paddleModel.setMoveUp(keyboard.isKeyPressed(upKey));
        paddleModel.setMoveDown(keyboard.isKeyPressed(downKey));
    }
    public void setPaddleKeys(int upKey, int downKey){
        this.upKey=upKey;
        this.downKey=downKey;
    }


}
