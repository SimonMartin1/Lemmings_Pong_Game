package Proyecto.games.Pong_game;
import java.awt.*;
import java.awt.event.KeyEvent;

//import Proyecto.games.Common_files.JGame;
import Proyecto.games.Pong_game.Controller.PaddleController;
import Proyecto.games.Pong_game.Model.BallModel;
import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.Model.ScoreManagerModel;
import Proyecto.games.Pong_game.View.BallView;
import Proyecto.games.Pong_game.View.PaddleView;
import Proyecto.games.Pong_game.View.ScoreManagerView;
import com.entropyinteractive.*;



public class Pong extends JGame {
    PaddleView paddleLeftView, paddleRightView;
    PaddleModel paddleModel,paddleRightModel;
    PaddleController paddleLeftController,paddleRightController;
    BallView ballView;
    BallModel ballModel;
    Keyboard keyboard;
    ScoreManagerModel scoreManagerModel;
    ScoreManagerView scoreManagerView;

    public Pong(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Pong game = new Pong("Mi Pong", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
        System.exit(0);
    }

    @Override
    public void gameStartup() {
        //incializacion del teclado
        keyboard = this.getKeyboard();

        //modelos
        scoreManagerModel = new ScoreManagerModel(5);
        paddleModel = new PaddleModel(250);
        paddleRightModel = new PaddleModel(250);
        ballModel = new BallModel(400,270,10,paddleModel,paddleRightModel,scoreManagerModel);

        //vistas
        scoreManagerView = new ScoreManagerView(scoreManagerModel);
        paddleLeftView = new PaddleView(paddleModel,8,230);
        paddleRightView = new PaddleView(paddleRightModel,770,230);
        ballView = new BallView(330,370,20,ballModel);

        //controladores
        paddleLeftController = new PaddleController(paddleModel,keyboard, KeyEvent.VK_W, KeyEvent.VK_S );
        paddleRightController = new PaddleController(paddleRightModel, keyboard,KeyEvent.VK_UP, KeyEvent.VK_DOWN);

        // Forzar foco
        getFrame().addKeyListener(keyboard);
        getFrame().setFocusable(true);
        getFrame().requestFocus();

    }

    @Override
    public void gameUpdate(double delta) {
        paddleRightController.update(delta);
        paddleLeftController.update(delta);
        paddleModel.update(delta);
        paddleRightModel.update(delta);
        ballModel.update();
    }

    @Override
    public void gameDraw(Graphics2D g) {
        // Limpiar pantalla
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar elementos
        scoreManagerView.draw(g);
        paddleRightView.draw(g);
        paddleLeftView.draw(g);
        ballView.draw(g);
    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }
}
