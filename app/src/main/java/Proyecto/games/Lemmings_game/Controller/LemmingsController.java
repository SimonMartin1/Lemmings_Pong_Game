package Proyecto.Games.Lemmings_game.Controller;
import Proyecto.Games.Lemmings_game.Model.LemmingsModel;
import Proyecto.Games.Lemmings_game.View.LemmingsView;

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
