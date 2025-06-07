package Proyecto.games.Pong_game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

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
import Proyecto.games.Pong_game.Model.Track;
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
    PaddleModel paddleLeftModel,paddleRightModel;
    PaddleIAmodel paddleIAModel;
    PaddleIAController paddleLefIAtController;
    PaddleController paddleLeftController,paddleRightController;
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
    GameSettingsView settingsView;
    SettingsModel settingsModel;
    SettingsModel.Config config;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, gameOver = false,twoplayers,musicOFF;
    private Player winner;
    private Difficult difficult;
    private Track track;
    private int maxPoints=5;



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
        keyboard = this.getKeyboard();
        setDifficult(1);
        setTwoPlayers(true);
        settingsModel = new SettingsModel();
        settingsView = new GameSettingsView(getWidth(), getHeight(),this);
        settingController = new SettingController(settingsView,settingsModel , getMouse(),this);
        initSettings();
        //modelos
        scoreManagerModel = new ScoreManagerModel(maxPoints);
        if(twoplayers){
            paddleLeftModel = new PaddleModel(250);
            }
        else{
            paddleIAModel = new PaddleIAmodel(250);
        }
        paddleRightModel = new PaddleModel(250);
        ballModel = new BallModel(400,270,10);
        
        //vistas
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Pong_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        scoreManagerView = new ScoreManagerView(scoreManagerModel);
        if(twoplayers){
            paddleLeftView = new PaddleView(paddleLeftModel,8);
        }
        else{
            paddleLeftView = new PaddleView(paddleIAModel,8);
        }
        paddleRightView = new PaddleView(paddleRightModel,795);
        ballView = new BallView(ballModel);
        gameOverMenuView = new GameOverMenuView(getWidth(), getWidth());
        gameMenu = new GameMenuView(getWidth(), getHeight(),this);
        gamePauseView = new GamePauseView(getWidth(), getHeight());

        //controladores
        if(twoplayers){
            paddleLeftController = new PaddleController(paddleLeftModel,keyboard, KeyEvent.VK_W, KeyEvent.VK_S );
        }
        else{
            paddleLefIAtController = new PaddleIAController(paddleIAModel);
        }
        paddleRightController = new PaddleController(paddleRightModel, keyboard,KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        if(twoplayers){
            ballController = new BallController(ballModel, paddleLeftModel, paddleRightModel, scoreManagerModel);
        }
        else{
            ballController = new BallController(ballModel, paddleIAModel, paddleRightModel, scoreManagerModel);
        }

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

    public void initSettings(){
        config = SettingsModel.loadConfig();
        musicOFF = config.musicOff;
        track = config.track;
        difficult = config.difficult;
        maxPoints = config.maxPoints;
        twoplayers = config.twoPlayers;

    if(musicOFF){
        settingsView.setDraw("Off");
    }
    else{
        settingsView.setDraw("Track");
        playTrack(track);
    }

    switch (config.difficult) {
        case HARD -> settingsView.setDraw("Hard");
        case MEDIUM -> settingsView.setDraw("Medium");
        case EASY -> settingsView.setDraw("Easy");
    }

    // WinPoints
    switch (config.maxPoints) {
        case 3 -> settingsView.setDraw("Win3");
        case 5 -> settingsView.setDraw("Win5");
        case 7 -> settingsView.setDraw("Win7");
    }

    // TwoPlayers
    if (config.twoPlayers) {
        settingsView.setDraw("TwoPlayers");
    }
    }

    public void saveSettings(){
        SettingsModel.saveConfig(musicOFF, track, difficult, maxPoints, twoplayers);
    }
    public boolean getIsinsettings() {
        return this.isInSettings;
    }
    public void setIsinsettings() {
        this.isInSettings = !this.isInSettings;
    }
    public Track getTrack(){
        return this.track;
    }
    public void setMaxPoints(int option) {
        switch (option) {
            case 0 -> this.maxPoints = 3; 
            case 1 -> this.maxPoints = 5; 
            case 2 -> this.maxPoints = 7; 
        }

    }
    public void setMusicOFF() {
        this.musicOFF = !this.musicOFF;
    }
    public boolean getmusicOFF() {
        return this.musicOFF;
    }
    public void setTrack(int option) {
        if(!musicOFF){
        switch (option) {
            case 1->{ 
            this.track = Track.TRACK1;
            }
            case 2->{
            this.track = Track.TRACK2;
            }
            case 3->{
            this.track = Track.TRACK3;
            }
        }
    }
}
public void playTrack(Track option) {
        if(!musicOFF){
        switch (option) {
            case TRACK1->{ 
            SoundPlayer.playSound("app/src/main/java/Proyecto/games/Pong_game/resources/cancion-joaqui.wav");
            }
            case TRACK2->{
            SoundPlayer.playSound("app/src/main/java/Proyecto/games/Pong_game/resources/cancion-travis.wav");
            }
            case TRACK3->{
            SoundPlayer.playSound("app/src/main/java/Proyecto/games/Pong_game/resources/cancion-pastillas.wav");

            }
        }
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

    public void setTwoPlayers(boolean twoplayers) {
        this.twoplayers = twoplayers;
        
    }

    @Override
    public void gameUpdate(double delta) {

        if(isInSettings){
            settingController = new SettingController(settingsView, settingsModel, getMouse(), this);
        }
        if(isInMenu){
            gameMenu.update(delta);

            if(gameMenu.detectSettings(getKeyboard()) || gameMenu.detectSetting(getMouse())){ isInSettings = !isInSettings; }
            if((gameMenu.detectPlay(getMouse()) || gameMenu.detectPlay(getKeyboard()))){ isInMenu = false; }
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
                    if(twoplayers){
                        paddleLeftController.update();
                        paddleLeftModel.update(delta);
                    }
                    else{
                        updateIA(delta);
                        paddleIAModel.update(delta);
                    }

                    paddleRightModel.update(delta);
                    paddleRightController.update();
                    ballController.update();
                }
            }
        }
    }

    public void updateIA(double delta){
        switch (difficult) {
            case EASY -> {
                if (ballModel.getPosX() < 800 * 0.1) {
                    paddleLefIAtController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                }
            }
            case MEDIUM -> {
                if (ballModel.getPosX() < 800 * 0.2) {
                    paddleLefIAtController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                }
            }
            case HARD -> {
                paddleLefIAtController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
            }
        }
    }

    @Override
    public void gameDraw(Graphics2D g) {
        if(isInMenu){
            gameMenu.drawmenu(g);

            if(isInSettings){
                settingsView.drawmenu(g);
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
        // Cargar configuración
    
    }

    public void gameReset(){
        //Reinciamos estados
        gameOver = false;
        winner = null;

        // Reiniciamos modelos
        scoreManagerModel.reset();
        ballModel.reset();
        if(twoplayers){
            paddleLeftModel.reset();
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
            paddleLeftModel.pause();
        }
        else{
            paddleIAModel.pause();
        }
        paddleRightModel.pause();
        
    }
}
