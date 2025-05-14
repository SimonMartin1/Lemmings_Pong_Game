package Proyecto;

import java.awt.Graphics2D;

public class Pong extends JGame {
    public Pong(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Pong game = new Pong("Mi Pong", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }

    @Override
    public void gameStartup() {
        // Inicializar pelotas, paletas, etc.
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
