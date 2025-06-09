package Proyecto.games.Pong_game;
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
import Proyecto.games.Pong_game.View.BallSkins;
import Proyecto.games.Pong_game.View.BallView;
import Proyecto.games.Pong_game.View.GameMenuView;
import Proyecto.games.Pong_game.View.GameOverMenuView;
import Proyecto.games.Pong_game.View.GamePauseView;
import Proyecto.games.Pong_game.View.GameSettingsView;
import Proyecto.games.Pong_game.View.PaddleView;
import Proyecto.games.Pong_game.View.ScoreManagerView;
import Proyecto.games.Pong_game.utils.SoundPlayer;


public class Pong extends JGame {
    PaddleView paddleLeftView,paddleLeftIAView, paddleRightView;
    PaddleModel paddleLeftModel,paddleRightModel;
    PaddleIAmodel paddleIAModel;
    PaddleIAController paddleLeftIAController;
    PaddleController paddleLeftController,paddleRightController;
    BallView ballView;
    BallModel ballModel;
    BallController ballController,ballIAController;
    Keyboard keyboard;
    PitchView pitch;
    ScoreManagerModel scoreManagerModel;
    ScoreManagerView scoreManagerView;
    GameOverMenuView gameOverMenuView;
    GameMenuView gameMenu;
    GamePauseView gamePauseView;
    SettingController settingController;
    GameSettingsView settingsView;
    SettingsModel settingsModel;
    SettingsModel.Settings setting;
    public SettingsModel.Settings backupSettings;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, gameOver = false,twoplayers,musicOFF;
    private boolean backupDrawHard, backupDrawMedium, backupDrawEasy, backupDrawTwoPlayers, backupDrawWin5, backupDrawWin10, backupDrawWin15, backupDrawOff, backupDrawTrack,backupFullScreen,fullScreen;
    private Player winner;
    private Difficult difficult;
    private BallSkins ballSkin;
    private PitchSkins pitchSkin;
    private Track track;
    private int maxPoints=5;
    private int[] player1Keys,player2Keys;
    private int width, height;
    private final int oldwidth, oldheight;



    public Pong(String title, int width, int height) {
        super(title, width, height);
        this.width=getWidth();
        this.height=getHeight();
        this.oldwidth=getWidth();
        this.oldheight=getHeight();
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
        settingsView = new GameSettingsView(width, this.height,this);
        settingController = new SettingController(settingsView,settingsModel , getMouse(),this);
        player1Keys=new int[2];
        player2Keys=new int[2];
        initSettings();
        backUpSettings();
        //modelos
        scoreManagerModel = new ScoreManagerModel(maxPoints);

        paddleLeftModel = new PaddleModel(height/2);
        paddleIAModel = new PaddleIAmodel(height/2);
        paddleRightModel = new PaddleModel(height/2);
        ballModel = new BallModel(width/2,height/2,10);
        
        //vistas
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Pong_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        scoreManagerView = new ScoreManagerView(scoreManagerModel, this.width,this.height);

        paddleLeftView = new PaddleView(paddleLeftModel,15);
        paddleLeftIAView = new PaddleView(paddleIAModel,15);
        paddleRightView = new PaddleView(paddleRightModel,width-25);
        ballView = new BallView(ballModel);
        pitch = new PitchView();
        gameOverMenuView = new GameOverMenuView(this.width, this.height);
        gameMenu = new GameMenuView(width, this.height,this);
        gamePauseView = new GamePauseView(this.width, this.height);

        //controladores
        paddleLeftController = new PaddleController(paddleLeftModel,keyboard, player1Keys[0],player1Keys[1] );
        paddleLeftIAController = new PaddleIAController(paddleIAModel);

        paddleRightController = new PaddleController(paddleRightModel, keyboard,player2Keys[0], player2Keys[1]);
        
        ballController = new BallController(ballModel, paddleLeftModel, paddleRightModel, scoreManagerModel,width,height);
        
        ballIAController = new BallController(ballModel, paddleIAModel, paddleRightModel, scoreManagerModel, width,height);

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
        setting = SettingsModel.loadSettings();
        musicOFF = setting.musicOff;
        track = setting.track;
        difficult = setting.difficult;
        maxPoints = setting.maxPoints;
        twoplayers = setting.twoPlayers;
        ballSkin = setting.ballSkin;
        pitchSkin=setting.pitchSkin;
        fullScreen=setting.fullScreen;
        player1Keys=setting.Player1Keys;
        player2Keys=setting.Player2Keys;

    if(musicOFF){
        settingsView.setDraw("Off");
    }
    else{
        settingsView.setDraw("Track");
        playTrack(track);
    }
    if (setting.twoPlayers) {
        settingsView.setDraw("TwoPlayers");
    }
    else{
        switch (setting.difficult) {
            case HARD -> settingsView.setDraw("Hard");
            case MEDIUM -> settingsView.setDraw("Medium");
            case EASY -> settingsView.setDraw("Easy");
        }
    }

    // WinPoints
    switch (setting.maxPoints) {
        case 5 -> settingsView.setDraw("Win5");
        case 10 -> settingsView.setDraw("Win10");
        case 15 -> settingsView.setDraw("Win15");
    }

    }
    public SettingsModel.Settings getSettings() {
        return setting;
    }  
    
    public void backUpSettings() {

        backupSettings = new SettingsModel.Settings();
        backupSettings.musicOff = musicOFF;
        backupSettings.track = track;
        backupSettings.difficult = difficult;
        backupSettings.maxPoints = maxPoints;
        backupSettings.twoPlayers = twoplayers;
        backupSettings.ballSkin= ballSkin;
        backupSettings.pitchSkin=pitchSkin;

        backupDrawHard = settingsView.drawHard;
        backupDrawMedium = settingsView.drawMedium;
        backupDrawEasy = settingsView.drawEasy;
        backupDrawTwoPlayers = settingsView.drawTwoPlayers;
        backupDrawWin5 = settingsView.drawWin5;
        backupDrawWin10 = settingsView.drawWin10;
        backupDrawWin15 = settingsView.drawWin15;
        backupDrawOff = settingsView.drawOff;
        backupDrawTrack = settingsView.drawTrack;
        backupFullScreen= settingsView.drawFullScreen;

    }
    public boolean getBackUpSettings(int option){
        boolean backup = false;
        switch(option){
            case 0 -> backup = backupDrawHard; // Hard
            case 1 -> backup = backupDrawMedium; // Medium
            case 2 -> backup = backupDrawEasy; // Easy
            case 3 -> backup = backupDrawTwoPlayers; // Two Players
            case 4 -> backup = backupDrawWin5; // Win 5
            case 5 -> backup = backupDrawWin10; // Win 10
            case 6 -> backup = backupDrawWin15; // Win 15
            case 7 -> backup = backupDrawOff; // Off
            case 8 -> backup = backupDrawTrack; // Track
            case 9 -> backup = backupFullScreen; // Off FullScreen
        }
        return backup;
    }

    public void saveSettings(){
        SettingsModel.saveSettings(musicOFF, track, difficult, maxPoints, twoplayers, ballSkin, pitchSkin,fullScreen,player1Keys,player2Keys);
    }
    public void resetSettings(){
        musicOFF = false;
        track = Track.TRACK3;
        difficult = Difficult.EASY;
        maxPoints = 5;
        twoplayers = false;
        player1Keys[0]=87;
        player1Keys[1]=83;
        player2Keys[0]=38;
        player2Keys[1]=40;

    }
    public void setFullScreen(boolean enable) {
    java.awt.Frame frame = this.getFrame();
    if (enable) {
        frame.dispose();
        frame.setUndecorated(true);
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.width = screenSize.width;
        this.height = screenSize.height;
    } else {
        frame.dispose();
        frame.setUndecorated(false);
        frame.setExtendedState(java.awt.Frame.NORMAL);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.width = 800;
        this.height = 600;
    }
    // Actualiza vistas y modelos
    settingsView.updateSize(width, height);
    gameMenu.updateSize(width, height);
    gamePauseView.updateSize(width, height);
    gameOverMenuView.updateSize(width, height);
    scoreManagerView.updateSize(width);
    paddleLeftView.updateSize(60,30,300);
    paddleLeftIAView.updateSize(60,30,300);
    paddleRightView.updateSize(width-90,30,300);
    paddleLeftIAController.updateSize(height);
    ballModel.updateSize(width, height);
    ballController.updateSize(width, height);
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
    public void setTrack(int option) {
        if(!musicOFF){
            switch (option) {
                case 1-> this.track = Track.TRACK1;
                case 2->this.track = Track.TRACK2;
                case 3->this.track = Track.TRACK3;
            }
        }
    }
        public PitchSkins getPitchSkin(){
            return this.pitchSkin;
        }
        public void setPitchSkin(int option) {
        switch (option) {
            case 1-> this.pitchSkin = PitchSkins.BLACK;
            case 2-> this.pitchSkin = PitchSkins.BLUE; 
            case 3-> this.pitchSkin = PitchSkins.BASKET; 
        }
        ballView.setBallType(ballSkin);
    }
    public BallSkins getBallSkin(){
        return ballSkin;
    }
    public void setBallSkin(int option) {
        switch (option) {
            case 1-> this.ballSkin = BallSkins.NORMAL;
            case 2-> this.ballSkin = BallSkins.CRAZY; 
            case 3-> this.ballSkin = BallSkins.TENNIS; 
            case 4-> this.ballSkin = BallSkins.FOOTBALL; 
            case 5-> this.ballSkin = BallSkins.BASKET; 
        }
        ballView.setBallType(ballSkin);
    }
    public void setMaxPoints(int option) {
        switch (option) {
            case 0 -> this.maxPoints = 5; 
            case 1 -> this.maxPoints = 10; 
            case 2 -> this.maxPoints = 15; 
        }

    }
    public void setMusicOFF(boolean option) {
        this.musicOFF = option;
    }
    public boolean getmusicOFF() {
        return this.musicOFF;
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
public void stopTrack() {
        SoundPlayer.stopSound();
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
    public int setPlayersKeys(){
        int res=KeyEvent.KEY_FIRST;
        return res;
    }
    public int[] getPlayersKeys(int option){
        int [] res;
        switch(option){
            default -> res=player1Keys;
            case 1 -> res=player1Keys;
            case 2 -> res=player2Keys;
        }
        return res;
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

            if(gameMenu.detectSettings(getKeyboard()) || gameMenu.detectSetting(getMouse())){ if(isInSettings){saveSettings();} isInSettings = !isInSettings;  }
            if((gameMenu.detectPlay(getMouse()) || gameMenu.detectPlay(getKeyboard()))){ isInMenu = false; }
        }
        else{
            
            if(gamePauseView.pauseGame(keyboard) && !gameOver){  gamePause = !gamePause; }

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
                        isInSettings = false;
                    }
                }
                else{
                    // Updates
                    if(twoplayers){
                        paddleLeftController.update();
                        paddleLeftModel.update(delta);
                        ballController.update();
                    }
                    else{
                        updateIA(delta);
                        paddleIAModel.update(delta);
                        ballIAController.update();
                    }

                    paddleRightModel.update(delta);
                    paddleRightController.update();
                }
            }
        }
    }

    public void updateIA(double delta){
        switch (difficult) {
            case EASY -> {
                if (ballModel.getPosX() < 800 * 0.1) {
                    paddleLeftIAController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                }
            }
            case MEDIUM -> {
                if (ballModel.getPosX() < 800 * 0.2) {
                    paddleLeftIAController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
                }
            }
            case HARD -> {
                paddleLeftIAController.update(delta, ballModel.getPosX(), ballModel.getPosY(), ballModel.getDirX(), ballModel.getDirY());
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
            
            pitch.draw(g, this.width,this.height,pitchSkin);

            scoreManagerView.draw(g);
            paddleRightView.draw(g);
            if(twoplayers){
                paddleLeftView.draw(g);
            }
            else{
                paddleLeftIAView.draw(g);
            }
            ballView.draw(g);

            if (gamePause) {
                gamePauseView.draw(g);
            }

            if (gameOver) {
                gameOverMenuView.draw(g, winner, twoplayers);
            }
        }

    }

    @Override
    public void gameShutdown() {
        if(!isInMenu && !isInSettings && !gameOver){
            gameReset();
        }
        saveSettings();
        System.exit(0);
    
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
