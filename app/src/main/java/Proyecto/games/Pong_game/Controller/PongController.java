package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.PongModel;
import Proyecto.games.Pong_game.View.PongView;

public class PongController {
    private PongModel model;
    private PongView view;

    public PongController(PongModel model, PongView view) {
        this.model = model;
        this.view = view;
    }

}



