package Proyecto.games.Pong_game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import javax.swing.ImageIcon;

import Proyecto.games.Pong_game.Controller.PaddleIAController;
import Proyecto.games.Pong_game.Model.*;
import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

import Proyecto.games.Pong_game.Controller.BallController;
import Proyecto.games.Pong_game.Controller.PaddleController;
import Proyecto.games.Pong_game.View.BallView;
import Proyecto.games.Pong_game.View.GameMenuView;
import Proyecto.games.Pong_game.View.GameOverMenuView;
import Proyecto.games.Pong_game.View.GamePauseView;
import Proyecto.games.Pong_game.View.GameSettingsView;
import Proyecto.games.Pong_game.View.PaddleView;
import Proyecto.games.Pong_game.View.ScoreManagerView;
import Proyecto.games.Pong_game.utils.SoundPlayer;



public class Pong extends JGame {
    PaddleView paddleLeftView, paddleRightView;
    PaddleModel paddleModel,paddleRightModel;
    PaddleIAController paddleLeftController;
    PaddleController paddleRightController;
    BallView ballView;
    BallModel ballModel;
    BallController ballController;
    Keyboard keyboard;
    ScoreManagerModel scoreManagerModel;
    ScoreManagerView scoreManagerView;
    GameOverMenuView gameOverMenuView;
    GameMenuView gameMenu;
    GamePauseView gamePauseView;
    GameSettingsView gameSettingsView;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, gameOver = false;
    private Player winner;
    private Difficult difficult = Difficult.HARD;


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

        SoundPlayer.playSound("app/src/main/java/Proyecto/games/Pong_game/resources/cancion-joaqui.wav");

        //incializacion del teclado
        keyboard = this.getKeyboard();

        //modelos
        scoreManagerModel = new ScoreManagerModel(2);
        //paddleModel = new PaddleModel(250);
        paddleModel = new PaddleIAmodel(250, difficult);
        paddleRightModel = new PaddleModel(250);
        ballModel = new BallModel(400,270,10);

        //vistas
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Pong_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        scoreManagerView = new ScoreManagerView(scoreManagerModel);
        paddleLeftView = new PaddleView(paddleModel,8);
        paddleRightView = new PaddleView(paddleRightModel,795);
        ballView = new BallView(ballModel);
        gameOverMenuView = new GameOverMenuView(getWidth(), getWidth());
        gameMenu = new GameMenuView(getWidth(), getHeight());
        gamePauseView = new GamePauseView(getWidth(), getHeight());
        gameSettingsView = new GameSettingsView(getWidth(), getHeight());

        //controladores
        //paddleLeftController = new PaddleController(paddleModel,keyboard, KeyEvent.VK_W, KeyEvent.VK_S );
        paddleLeftController = new PaddleIAController(paddleModel);
        paddleRightController = new PaddleController(paddleRightModel, keyboard,KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ballController = new BallController(ballModel, paddleModel, paddleRightModel, scoreManagerModel);

        // Forzar foco
        getFrame().addKeyListener(keyboard);
        getFrame().setFocusable(true);
        getFrame().requestFocus();
        getFrame().addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            gameShutdown();
            System.exit(0); // Opcional: cierra la aplicación completamente
        }
    });

    }

    @Override
    public void gameUpdate(double delta) {

        if(isInMenu){
            gameMenu.update(delta);

            if(gameMenu.detectSettings(getKeyboard())){ isInSettings = !isInSettings; }
            if(gameMenu.detectPlay(getMouse()) || gameMenu.detectPlay(getKeyboard())){ isInMenu = false; }
        }
        else{
            
            if(gamePauseView.pauseGame(keyboard)){  gamePause = !gamePause; }

            if(gamePause){

                if(gamePauseView.wantsBackMenu(keyboard)){
                    gameReset();
                    isInMenu = true;
                    gamePause = false;
                }

            }
            else{

                if(scoreManagerModel.hasWinner()){
                    gameOver = true;
                    winner = scoreManagerModel.getWinner();

                    if(gameOverMenuView.wantsRestart(keyboard)){ gameReset(); }

                    if(gameOverMenuView.wantsBackMenu(keyboard)){
                        gameReset();
                        isInMenu = true;
                    }
                }
                else{
                    // Updates
                    paddleRightController.update(delta);

                    switch (difficult){
                        case EASY :
                            if(ballModel.getPosX() < 800 * 0.1){
                                paddleLeftController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                            }
                            break;

                        case MEDIUM:
                            if(ballModel.getPosX() < 800 * 0.2){
                                paddleLeftController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                            }
                            break;

                        case HARD:
                            paddleLeftController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                            break;
                    }

                    paddleModel.update(delta);
                    paddleRightModel.update(delta);

                    ballController.update();
                }
            }
        }
    }

    @Override
    public void gameDraw(Graphics2D g) {
        
        if(isInMenu){
            gameMenu.drawmenu(g);

            if(isInSettings){
                gameSettingsView.drawmenu(g);
            }
        }
        else{
            
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            scoreManagerView.draw(g);
            paddleRightView.draw(g);
            paddleLeftView.draw(g);
            ballView.draw(g);

            if (gamePause) {
                gamePauseView.draw(g);
            }

            if (gameOver) {
                gameOverMenuView.draw(g, winner);
            }
        }

    }

    @Override
    public void gameShutdown() {
        if(isInMenu && !gameOver){
            gameReset();
        }
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

    public void gamePause(){

        scoreManagerModel.pause();
        ballModel.pause();
        paddleModel.pause();
        paddleRightModel.pause();
        
    }
}
