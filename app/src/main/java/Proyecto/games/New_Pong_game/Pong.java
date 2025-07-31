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

public class Pong extends JGame {

    private int width, height;
    private final Properties propertiesGameConfig;
    GameState gameState = GameState.ON_MENU;
    ConfigPong config;

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


    public Pong(String title, int width, int height) throws IOException {
        super(title, width, height);

        this.width = getWidth();
        this.height = getHeight();

        //Inicializamos properties
        propertiesGameConfig = new Properties();
        propertiesGameConfig.load(new FileInputStream("app/src/main/java/Proyecto/games/New_Pong_game/config.properties"));
    }

    public static void main(String[] args) {
        try{

            Pong game = new Pong("Mi Pong", 800, 600);
            game.run(1.0 / 60.0); // 60 FPS
            System.exit(0);

        }catch (IOException err){
            System.out.println("No se pudo leer el archivo config");
        }
    }


    @Override
    public void gameStartup() {

        // Leemos las configs
        boolean musicOff = Boolean.parseBoolean(propertiesGameConfig.getProperty("musicOff", "false"));
        boolean isFullscreen = Boolean.parseBoolean(propertiesGameConfig.getProperty("isFullscreen", "false"));
        boolean isVersusIA = Boolean.parseBoolean(propertiesGameConfig.getProperty("isVersusIA", "true"));
        int maxPoints = Integer.parseInt(propertiesGameConfig.getProperty("maxPoints", "10"));
        Difficult difficult = Difficult.values()[Integer.parseInt(propertiesGameConfig.getProperty("difficult", "EASY"))];
        int player1UpKey = KeyEvent.getExtendedKeyCodeForChar(propertiesGameConfig.getProperty("player1.up", "W").charAt(0));
        int player1DownKey = KeyEvent.getExtendedKeyCodeForChar(propertiesGameConfig.getProperty("player1.down", "S").charAt(0));
        int player2UpKey = KeyEvent.getExtendedKeyCodeForChar(propertiesGameConfig.getProperty("player2.up", "UP").charAt(0));
        int player2DownKey = KeyEvent.getExtendedKeyCodeForChar(propertiesGameConfig.getProperty("player2.down", "DOWN").charAt(0));
        SkinBall skinBall = SkinBall.values()[Integer.parseInt(propertiesGameConfig.getProperty("skin.ball", "CRAZY"))];
        SkinPitch skinPitch = SkinPitch.values()[Integer.parseInt(propertiesGameConfig.getProperty("skin.pitch", "BASKET"))];


        config = new ConfigPong(difficult,maxPoints,isVersusIA,player2DownKey,player2UpKey ,player1DownKey, player1UpKey,isFullscreen,musicOff, skinPitch, skinBall);
        soundManager = new SoundManager(musicOff);


        if(config.isFullscreen()){
            setFullscreenMode();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            this.width = screenSize.width;
            this.height = screenSize.height;
        }

        this.menu = new Menu(width,height);
        this.settings = new Settings(width,height,this);
        this.over = new Over(width, height);
    }

    @Override
    public void gameUpdate(double delta) {
        switch (gameState){
            case ON_MENU -> {
                menu.update(delta, this);

                if(getKeyboard().isKeyPressed(KeyEvent.VK_SPACE)){
                    startGame();
                    setGameState(GameState.PLAYING);
                }
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
            case ON_MENU -> menu.draw(g);
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

        this.pitch = new Pitch(width,height, config.getSkinPitch());

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

        this.ball = new Ball(width, height, width/2, height/2, 10, paddleLeft, paddleRight, scoreManager,config.getSkinBall(), soundManager);
    }

    // Getters

    public ConfigPong getConfig(){
        return config;
    }

    public boolean isFullscreen(){
        return config.isFullscreen();
    }

    public boolean isOnSettings(){
        return gameState == GameState.ON_CONFIG;
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
}
