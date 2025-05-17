package Proyecto.games.Lemmings_game.controller;
import Proyecto.games.Lemmings_game.model.LemmingsModel;
import Proyecto.games.Lemmings_game.view.LemmingsView;

public class LemmingsController {
    // Atributos del controlador
    private LemmingsModel model;
    private LemmingsView view;

    // Constructor
    public LemmingsController(LemmingsModel model, LemmingsView view) {
        this.model = model;
        this.view = view;
    }

    // Métodos para manejar la lógica del juego
    public void startGame() {
        // Lógica para iniciar el juego
    }

    public void updateGame() {
        // Lógica para actualizar el juego
    }

    public void endGame() {
        // Lógica para finalizar el juego
    }
}
