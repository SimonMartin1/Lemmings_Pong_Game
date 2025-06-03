package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.PaddleModel;
import com.entropyinteractive.Keyboard;


public class PaddleController {
    private final PaddleModel paddleModel;
    private final Keyboard keyboard;
    private final int upKey;
    private final int downKey;

    public PaddleController(PaddleModel paddleModel, Keyboard keyboard, int upKey, int downKey) {
        this.paddleModel = paddleModel;
        this.keyboard = keyboard;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void update(double delta) {
        // Actualizar estado basado en teclas presionadas
        paddleModel.setMoveUp(keyboard.isKeyPressed(upKey));
        paddleModel.setMoveDown(keyboard.isKeyPressed(downKey));

        // Actualizar posición
        paddleModel.update(delta);
    }

}
