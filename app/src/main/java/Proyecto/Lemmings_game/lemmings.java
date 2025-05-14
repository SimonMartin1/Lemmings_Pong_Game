
package Proyecto.Lemmings_game;

import java.awt.Graphics2D;

import Proyecto.JGame;

public class lemmings extends JGame {
    public lemmings(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        lemmings game = new lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }

    @Override
    public void gameStartup() {
        // Inicializar el juego
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
}