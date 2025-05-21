/*package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.View.PaddleView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle2Controller implements KeyListener {
    PaddleModel paddleModel;
    PaddleView paddleView;

    public Paddle2Controller(PaddleModel paddleModel, PaddleView paddleView){
        this.paddleView = paddleView;
        this.paddleModel = paddleModel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //no lo necesito de momento
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Usa getKeyCode() para flechas y teclas especiales
        if (keyCode == KeyEvent.VK_UP) { // Compara con getKeyCode()
            paddleModel.moveUp();
            paddleView.setBounds(760, paddleModel.getY(), 20, 90); // Ajusta X para la paleta derecha
        } else if (keyCode == KeyEvent.VK_DOWN) {
            paddleModel.moveDown();
            paddleView.setBounds(760, paddleModel.getY(), 20, 90);
        }
        paddleView.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //no lo necesito por ahora
    }
}
*/