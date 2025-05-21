package Proyecto.games.Pong_game.Controller;

import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.View.PaddleView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaddleController implements KeyListener {
    PaddleModel paddleModel;
    PaddleView paddleView;

    public PaddleController(PaddleModel paddleModel, PaddleView paddleView){
        this.paddleView = paddleView;
        this.paddleModel = paddleModel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    //no lo necesito de momento
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            paddleModel.moveUp();
        } else if (e.getKeyChar() == 's') {
            paddleModel.moveDown();
        }
        paddleView.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    //no lo necesito por ahora
    }
}
