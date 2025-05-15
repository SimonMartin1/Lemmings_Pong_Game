package Proyecto.Lemmings_game.Controller;
import Proyecto.Lemmings_game.Model.lemmingsModel;
import Proyecto.Lemmings_game.View.lemmingsView;

public class lemmingsController {
    // Atributos del controlador
    private lemmingsModel model;
    private lemmingsView view;

    // Constructor
    public lemmingsController(lemmingsModel model, lemmingsView view) {
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
