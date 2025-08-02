package Proyecto.games.New_Pong_game;

import Proyecto.games.New_Pong_game.utils.*;
import Proyecto.games.New_Pong_game.views.Menu;
import Proyecto.games.New_Pong_game.views.Over;
import Proyecto.games.New_Pong_game.views.Pause;
import Proyecto.games.New_Pong_game.views.Settings;
import com.entropyinteractive.JGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Pong extends JGame{

    private int width, height;
    GameState gameState = GameState.PRE_MENU;
    ConfigPong config,config_BackUp;

    SoundManager soundManager;
    ScoreManager scoreManager;

    // Vistas
    Menu menu;
    Settings settings;
    Pause pause;
    Pitch pitch;
    Over over;

    // Elementos
    Ball ball;

    PaddleController paddleLeftController;
    PaddleController paddleRightController;
    PaddleIAController paddleIAController;

    Paddle paddleLeft;
    Paddle paddleRight;


    public Pong(String title, int width, int height) {
        super(title, width, height);

        this.width = getWidth();
        this.height = getHeight();

    }

    public static void main(String[] args) {

        Pong game = new Pong("Mi Pong", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
        System.exit(0);

    }


    @Override
    public void gameStartup() {
        config = new ConfigPong();
        soundManager = new SoundManager(config.isMusicOff());
    }

    @Override
    public void gameUpdate(double delta) {
        switch (gameState){

            case PRE_MENU -> {

                if(config.isFullscreen()){
                    setFullscreenMode();

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                    this.width = screenSize.width;
                    this.height = screenSize.height;
                }
                else{
                    exitFullscreenAndSetWindowedMode();
                    this.width = 800;
                    this.height = 600;
                }

                this.menu = new Menu(width,height);
                this.settings = new Settings(width,height,this);
                this.over = new Over(width, height);

                this.gameState = GameState.ON_MENU;
            }


            case ON_MENU -> {
                menu.update(delta, this);

                if(getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
                    startGame();
                    setGameState(GameState.PLAYING);
                }
                //guardo una copia de la config para el cancel
                config_BackUp=config;
            }

            case ON_CONFIG -> settings.update(delta, this);

            case ON_PAUSE -> pause.wantsBackMenu();


            case PLAYING -> {

                if (scoreManager.hasWinner()){
                    gameState = GameState.FINISH;

                    over.setTwoPlayers(!config.isVersusIA());
                    over.setWinner(scoreManager.getWinner());
                    return;
                }

                pause.pauseGame();

                ball.update();

                if(!config.isVersusIA()){
                    paddleLeftController.update(delta);
                }
                else{
                    paddleIAController.update(delta, ball.getPosX(), ball.getPosY(), ball.getDirX());
                }

                paddleRightController.update(delta);
            }

            case FINISH -> {
                if(getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
                    gameState = GameState.PLAYING;
                    startGame();
                }

                if(getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
                    gameState = GameState.ON_MENU;
                }
            }
        }

    }

    @Override
    public void gameDraw(Graphics2D g) {

        switch (gameState){
            case PRE_MENU, ON_MENU -> menu.draw(g);
            case ON_CONFIG -> settings.draw(g);

            case ON_PAUSE -> {
                pitch.draw(g);
                ball.draw(g);
                paddleLeft.draw(g);
                paddleRight.draw(g);
                scoreManager.draw(g);
                pause.draw(g);
            }

            case PLAYING -> {
                pitch.draw(g);
                ball.draw(g);
                paddleLeft.draw(g);
                paddleRight.draw(g);
                scoreManager.draw(g);
            }

            case FINISH -> over.draw(g);
        }

    }

    @Override
    public void gameShutdown() {

    }


    public void startGame(){

        this.pitch = new Pitch(width,height, config.getPitchSkin());

        this.scoreManager = new ScoreManager(width, config.getMaxPoints());
        this.pause = new Pause(this);

        this.paddleRight = new Paddle(width, height, height/2  - 75, (int)(width - width*0.03));
        this.paddleRightController = new PaddleController(paddleRight, getKeyboard(), config.getPlayerTwoUp(), config.getPlayerTwoDown());

        if(config.isVersusIA()){
            this.paddleLeft = new Paddle(width, height, height/2 - 75, (int) (width*0.03));
            this.paddleIAController = new PaddleIAController(paddleLeft, config.getDifficult());
        }
        else{
            this.paddleLeft =  new Paddle(width, height, height/2 - 75, (int) (width*0.03));
            this.paddleLeftController = new PaddleController(paddleLeft, getKeyboard(), config.getPlayerOneUp(), config.getPlayerOneDown());
        }

        this.ball = new Ball(width, height, width/2, height/2, 10, paddleLeft, paddleRight, scoreManager,config.getBallSkin(), soundManager);
    }

    // Getters

    public ConfigPong getConfig(){
        return config;
    }

    public void cancelConfig(){
        config=config_BackUp;
    }

    public void resetConfig(){config=new ConfigPong();}

    public boolean isFullscreen(){
        return config.isFullscreen();
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    private void setFullscreenMode() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            JFrame frame = getFrame(); // Ya lo tenés gracias a JGame
            frame.dispose();
            frame.setUndecorated(true);
            frame.setResizable(false);
            gd.setFullScreenWindow(frame);
            frame.setVisible(true);

            try {
                Field canvasField = JGame.class.getDeclaredField("canvas");
                canvasField.setAccessible(true);
                JPanel canvas = (JPanel) canvasField.get(this); // ⚠️ "this" porque estás en Pong que hereda de JGame

                canvas.setFocusable(true);
                canvas.requestFocusInWindow();

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Pantalla completa no soportada.");
        }
    }

    private void exitFullscreenAndSetWindowedMode() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        JFrame frame = getFrame(); // Método de JGame

        // Salir del modo pantalla completa
        gd.setFullScreenWindow(null);

        // Restaurar el JFrame
        frame.dispose();
        frame.setUndecorated(false);
        frame.setResizable(true);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centrar en pantalla
        frame.setVisible(true);

        try {
            Field canvasField = JGame.class.getDeclaredField("canvas");
            canvasField.setAccessible(true);
            JPanel canvas = (JPanel) canvasField.get(this);

            canvas.setFocusable(true);
            canvas.requestFocusInWindow();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
