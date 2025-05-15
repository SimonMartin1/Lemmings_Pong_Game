package Proyecto.games.Pong_game;
import java.awt.Graphics2D;

import Proyecto.games.JGame;
import Proyecto.games.Pong_game.Controller.pongController;
import Proyecto.games.Pong_game.Model.pongModel;
import Proyecto.games.Pong_game.View.pongView;

public class pong extends JGame {
    public pong(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        pong game = new pong("Mi Pong", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }

    @Override
    public void gameStartup() {
        // Inicializar pelotas, paletas, etc.
        pongModel model = new pongModel();
        pongView view = new pongView();
        pongController controller = new pongController(model, view);
    }

    @Override
    public void gameUpdate(double delta) {
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
}
