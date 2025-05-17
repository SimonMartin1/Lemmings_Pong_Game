package Proyecto.Games.Pong_game.Controller;

import Proyecto.Games.Pong_game.Model.PongModel;
import Proyecto.Games.Pong_game.View.PongView;

public class PongController {
    private PongModel model;
    private PongView view;

    public PongController(PongModel model, PongView view) {
        this.model = model;
        this.view = view;
    }

}



