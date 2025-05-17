package Proyecto.games.Pong_game.controller;

import Proyecto.games.Pong_game.model.PongModel;
import Proyecto.games.Pong_game.view.PongView;

public class PongController {
    private PongModel model;
    private PongView view;

    public PongController(PongModel model, PongView view) {
        this.model = model;
        this.view = view;
    }

}



