package Proyecto.games.Pong_game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;
//import Proyecto.games.Pong_game.utils.SoundPlayer;
import Proyecto.games.Pong_game.Controller.BallController;
import Proyecto.games.Pong_game.Controller.PaddleController;
import Proyecto.games.Pong_game.Controller.PaddleIAController;
import Proyecto.games.Pong_game.Controller.SettingController;
import Proyecto.games.Pong_game.Model.BallModel;
import Proyecto.games.Pong_game.Model.Difficult;
import Proyecto.games.Pong_game.Model.PaddleIAmodel;
import Proyecto.games.Pong_game.Model.PaddleModel;
import Proyecto.games.Pong_game.Model.Player;
import Proyecto.games.Pong_game.Model.ScoreManagerModel;
import Proyecto.games.Pong_game.Model.SettingsModel;
import Proyecto.games.Pong_game.View.BallView;
import Proyecto.games.Pong_game.View.GameMenuView;
import Proyecto.games.Pong_game.View.GameOverMenuView;
import Proyecto.games.Pong_game.View.GamePauseView;
import Proyecto.games.Pong_game.View.GameSettingsView;
import Proyecto.games.Pong_game.View.PaddleView;
import Proyecto.games.Pong_game.View.ScoreManagerView;


public class Pong extends JGame {
    PaddleView paddleLeftView, paddleRightView;
    PaddleModel paddleModel,paddleRightModel;
    PaddleIAmodel paddleIAModel;
    PaddleIAController paddleLefIAtController;
    PaddleController paddleLefController,paddleRightController;
    BallView ballView;
    BallModel ballModel;
    BallController ballController;
    Keyboard keyboard;
    ScoreManagerModel scoreManagerModel;
    ScoreManagerView scoreManagerView;
    GameOverMenuView gameOverMenuView;
    GameMenuView gameMenu;
    GamePauseView gamePauseView;
    SettingController settingController;
    GameSettingsView gameSettingsView;
    SettingsModel settingsModel;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, gameOver = false,twoplayers=true;
    private Player winner;
    private Difficult difficult;


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

        //SoundPlayer.playSound("app/src/main/java/Proyecto/games/Pong_game/resources/cancion-travis.wav");

        //incializacion del teclado
        keyboard = this.getKeyboard();
        setDifficult(2); // Por defecto, dificultad fácil
        //modelos
        scoreManagerModel = new ScoreManagerModel();
        if(twoplayers){
            paddleModel = new PaddleModel(250);
            }
        else{
            paddleIAModel = new PaddleIAmodel(250);
        }
        paddleRightModel = new PaddleModel(250);
        ballModel = new BallModel(400,270,10);
        settingsModel = new SettingsModel();
        //vistas
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Pong_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        scoreManagerView = new ScoreManagerView(scoreManagerModel);
        if(twoplayers){
            paddleLeftView = new PaddleView(paddleModel,8);
        }
        else{
            paddleLeftView = new PaddleView(paddleIAModel,8);
        }
        paddleRightView = new PaddleView(paddleRightModel,795);
        ballView = new BallView(ballModel);
        gameOverMenuView = new GameOverMenuView(getWidth(), getWidth());
        gameMenu = new GameMenuView(getWidth(), getHeight());
        gamePauseView = new GamePauseView(getWidth(), getHeight());
        gameSettingsView = new GameSettingsView(getWidth(), getHeight());

        //controladores
        if(twoplayers){
            paddleLefController = new PaddleController(paddleModel,keyboard, KeyEvent.VK_W, KeyEvent.VK_S );
        }
        else{
            paddleLefIAtController = new PaddleIAController(paddleIAModel);
        }
        paddleRightController = new PaddleController(paddleRightModel, keyboard,KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ballController = new BallController(ballModel, paddleIAModel, paddleRightModel, scoreManagerModel);
        
        settingController = new SettingController(gameSettingsView,settingsModel , getWidth(), getHeight(), getMouse());

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

                    if(!twoplayers){
                        updateIA(delta);
                        paddleIAModel.update(delta);
                    }
                    else{
                        paddleModel.update(delta);
                    }
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
        if(twoplayers){
            paddleModel.reset();
        }
        else{
            paddleIAModel.reset();
        }
        paddleRightModel.reset();
    }

    public void gamePause(){

        scoreManagerModel.pause();
        ballModel.pause();
                if(twoplayers){
            paddleModel.pause();
        }
        else{
            paddleIAModel.pause();
        }
        paddleRightModel.pause();
        
    }
    @Override public void readPropertiesFile(){}

    public void updateIA(double delta){
        switch (difficult){
                        case EASY :
                            if(ballModel.getPosX() < 800 * 0.1){
                                paddleLefIAtController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                            }
                            break;

                        case MEDIUM:
                            if(ballModel.getPosX() < 800 * 0.2){
                                paddleLefIAtController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                            }
                            break;

                        case HARD:
                            paddleLefIAtController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                            break;
                    }
    }
    public void setDifficult(int difficult) {
        switch (difficult) {
            case 0:
                this.difficult = Difficult.EASY;
                break;
            case 1:
                this.difficult = Difficult.MEDIUM;
                break;
            case 2:
                this.difficult = Difficult.HARD;
                break;
            default:
                this.difficult = Difficult.EASY;
        }
    }
}
