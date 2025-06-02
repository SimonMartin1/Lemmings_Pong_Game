package Proyecto.games.Pong_game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

import Proyecto.games.Pong_game.Controller.BallController;
import Proyecto.games.Pong_game.Controller.PaddleController;
import Proyecto.games.Pong_game.Model.BallModel;
import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.Model.Player;
import Proyecto.games.Pong_game.Model.ScoreManagerModel;
import Proyecto.games.Pong_game.View.BallView;
import Proyecto.games.Pong_game.View.GameMenuView;
import Proyecto.games.Pong_game.View.GameOverMenuView;
import Proyecto.games.Pong_game.View.PaddleView;
import Proyecto.games.Pong_game.View.ScoreManagerView;



public class Pong extends JGame {
    PaddleView paddleLeftView, paddleRightView;
    PaddleModel paddleModel,paddleRightModel;
    PaddleController paddleLeftController,paddleRightController;
    BallView ballView;
    BallModel ballModel;
    BallController ballController;
    Keyboard keyboard;
    ScoreManagerModel scoreManagerModel;
    ScoreManagerView scoreManagerView;
    GameOverMenuView gameOverMenuView;
    GameMenuView gameMenu;
    private boolean animation=false,gameOver = false;
    private Player winner;
    private double blinkTime = 0;
    private boolean showPressText = true;
    private Graphics2D g;

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
        scoreManagerModel = new ScoreManagerModel(1);
        paddleModel = new PaddleModel(250);
        paddleRightModel = new PaddleModel(250);
        ballModel = new BallModel(400,270,5);

        //vistas
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Pong_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        scoreManagerView = new ScoreManagerView(scoreManagerModel);
        paddleLeftView = new PaddleView(paddleModel,8,230);
        paddleRightView = new PaddleView(paddleRightModel,795,230);
        ballView = new BallView(330,370,20,ballModel);
        gameOverMenuView = new GameOverMenuView(getWidth(), getWidth());
        gameMenu = new GameMenuView(getWidth(), getHeight());

        //controladores
        paddleLeftController = new PaddleController(paddleModel,keyboard, KeyEvent.VK_W, KeyEvent.VK_S );
        paddleRightController = new PaddleController(paddleRightModel, keyboard,KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ballController = new BallController(ballModel, paddleModel, paddleRightModel, scoreManagerModel);

        // Forzar foco
        getFrame().addKeyListener(keyboard);
        getFrame().setFocusable(true);
        getFrame().requestFocus();

    }

    @Override
    public void gameUpdate(double delta) {
        if (!animation) {
            
            if (getMouse().isLeftButtonPressed()) {
                int mx = getMouse().getX();
                int my = getMouse().getY();
                int bx = getWidth()/2 - 100, by = 300, bw = 200, bh = 60;
                if (mx >= bx && mx <= bx + bw && my >= by && my <= by + bh) {
                    animation = true;
                }
            }
            if (getKeyboard().isKeyPressed(10)) {
                animation = true;
            }
            
            blinkTime += delta;
            if (blinkTime >= 0.6) { 
                showPressText = !showPressText;
                blinkTime = 0;
            }
        }
        if(animation){
        if(scoreManagerModel.hasWinner()){
            gameOver = true;
            winner = scoreManagerModel.getWinner();

            if(gameOverMenuView.wantsRestart(keyboard)){
                gameReset();
            }
            
            if(gameOverMenuView.wantsBackMenu(keyboard)){
                animation = false;
                gameReset();
                gameMenu.drawmenu(g);
            }

        }
        else{
            paddleRightController.update(delta);
            paddleLeftController.update(delta);

            paddleModel.update(delta);
            paddleRightModel.update(delta);

            ballController.update();
        }
    }
    }

    @Override
    public void gameDraw(Graphics2D g) {
        this.g = g;
        gameMenu.drawmenu(g);
        if (!animation && showPressText) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Click or Enter", getWidth()/2 - 71, 450);
        }
        if(animation){        // Limpiar pantalla
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar elementos
        scoreManagerView.draw(g);
        paddleRightView.draw(g);
        paddleLeftView.draw(g);
        ballView.draw(g);

        if(gameOver){
            gameOverMenuView.draw(g, scoreManagerModel.getWinner());
        }
    }
    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }

    public void gameReset(){
        //Reinciamos estados
        gameOver = false;
        winner = null;

        // Reiniciamos modelos
        scoreManagerModel.reset();
        ballModel.reset();
        paddleModel.reset();
        paddleRightModel.reset();
    }
}
