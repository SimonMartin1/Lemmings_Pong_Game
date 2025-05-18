package Proyecto.games.Lemmings_game;
import java.awt.Graphics2D;

import Proyecto.games.Common_files.JGame;
import Proyecto.games.Lemmings_game.controller.LemmingsController;
import Proyecto.games.Lemmings_game.model.LemmingsModel;
import Proyecto.games.Lemmings_game.view.LemmingsView;

public class Lemmings extends JGame {
    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }

    @Override
    public void gameStartup() {
        // Inicializar el juego
        LemmingsModel model = new LemmingsModel();
        LemmingsView view = new LemmingsView();
        LemmingsController controller = new LemmingsController(model, view);
    }

    @Override
    public void gameUpdate(double delta) {
        // Actualizar la lógica del juego
    }
@Override
    public void gameDraw(Graphics2D g) {
        // Dibujar elementos del juego
        g.fillRect(0, 0, width, height);
        //Dibujar elementos del juego
    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }

    @Override
    public void ReadPropertiesFile() {
        // Leer propiedades del juego desde un archivo
        // Implementar la lógica para leer el archivo de propiedades
    }
}