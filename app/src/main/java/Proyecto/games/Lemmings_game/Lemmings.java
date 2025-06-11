package Proyecto.games.Lemmings_game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Proyecto.games.Lemmings_game.Controller.ButtonController;
import Proyecto.games.Lemmings_game.Controller.GameSettingsController;
import Proyecto.games.Lemmings_game.Controller.LevelController;
import Proyecto.games.Lemmings_game.Model.GameSettingsModel;
import Proyecto.games.Lemmings_game.Model.LevelModel;
import Proyecto.games.Lemmings_game.Model.MapModel;
import Proyecto.games.Lemmings_game.Model.MinimapModel;
import Proyecto.games.Lemmings_game.Model.Stock;
import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.ScoreDatabase;
import Proyecto.games.Lemmings_game.View.ExitView;
import Proyecto.games.Lemmings_game.View.GameMenuView;
import Proyecto.games.Lemmings_game.View.GamePauseView;
import Proyecto.games.Lemmings_game.View.GameScoreView;
import Proyecto.games.Lemmings_game.View.GameSettingsView;
import Proyecto.games.Lemmings_game.View.GameWinView;
import Proyecto.games.Lemmings_game.View.LevelView;
import Proyecto.games.Lemmings_game.View.MapView;
import Proyecto.games.Lemmings_game.View.SpawnerView;
import Proyecto.games.Pong_game.Model.SettingsModel;
import Proyecto.utils.SoundPlayer;



public class Lemmings extends JGame {

    GameMenuView gameMenu;

    ButtonController buttonController;

    private int currentLevel = 0;
    private ScoreDatabase db;

    private final List<MapModel> mapModels  = new ArrayList<>();
    private final List<MapView> mapViews = new ArrayList<>();
    private final List<LevelModel> levelModels = new ArrayList<>();
    private final List<LevelView> levelViews = new ArrayList<>();
    private final List<LevelController> levelControllers = new ArrayList<>();
    private GameSettingsView gameSettingsView;
    private GamePauseView gamePauseView;
    private GameScoreView gameScoreView;
    private GameWinView gameWinView;
    private GameSettingsController gameSettingsController;
    private SettingsModel.Settings Settings,backUpSettings;
    private static boolean fullScreen = false;
    private static boolean musicOff = true;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, isInScore = false, gameWin=false;
    private final int screenWidth = getWidth();
    private final int screenHeight = getHeight();
    private int pointsSum;
    private Boolean prevPausePressed = null;
    private final List<MinimapModel> minimapModels = new ArrayList<>();

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    
    public static void main(String[] args) {


        configReader();

        System.out.println(fullScreen);

        if(!fullScreen){
            Lemmings game = new Lemmings("Lemmings", 800, 600);
            game.run(1.0 / 60.0); // 60 FPS
        }else{
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int width = screenSize.width;
            int height = screenSize.height;

            Lemmings game = new Lemmings("Lemmings", width, height);
            game.run(1.0 / 60.0); // 60 FPS
        }    
    }

    public void initSettings(){
        Settings= SettingsModel.loadSettings();
        musicOff=Settings.musicOff;
        fullScreen=Settings.fullScreen;
    }

    public static void configReader(){
        Properties config = new Properties();

        try {
            FileInputStream input = new FileInputStream("app/src/main/java/proyecto/games/Lemmings_game/utils/Lemmings_setting.txt");
            config.load(input);
            input.close();

            config.list(System.out);
            // Obtener el valor de fullScreen
            String fullScreenValue = config.getProperty("fullScreen");
            fullScreen = Boolean.parseBoolean(fullScreenValue.trim());

            String musicOffValue = config.getProperty("musicOff");
            musicOff = Boolean.parseBoolean(musicOffValue.trim());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void backUpSettings(){
        backUpSettings = new SettingsModel.Settings();
        backUpSettings.fullScreen=fullScreen;
        backUpSettings.musicOff=musicOff;
    }

    public SettingsModel.Settings getbackUp(){
        return backUpSettings;
    }

    public void saveSettings(){
        GameSettingsModel.saveSettings(musicOff,fullScreen);
    }

    public static boolean setFullScreen(boolean option){
        return fullScreen=option;
    }

    public void setMusicOFF(boolean option){
        this.musicOff=option;
    }


    public boolean getIsinsettings() {
        return this.isInSettings;
    }
    public void setIsinsettings() {
        this.isInSettings = !this.isInSettings;
    }

    public boolean getIsinScore() {
    return this.isInScore;
    }    
        public boolean getIsinMenu() {
        return this.isInMenu;
    }
    public void setIsinMenu(boolean option) {
        this.isInMenu = option;
    }

    public void playTrack(){
        if(!musicOff){
            SoundPlayer.playSound("app/src/main/resources/cantinadelpela.wav");
        }
    }

    @Override
    public void gameStartup() {

        getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                SoundPlayer.stopSound();
            }
        });

        ScoreDatabase.createTable();

        playTrack();
        initSettings();
        backUpSettings();

        if (fullScreen) {
            setFullScreen(); 
        }

        try{

            createLevel(
                    1, 1, 1020, 300, 690, 70, 0, 0,
                    Map.of(
                            Ability.DIGGER, 5,
                            Ability.CLIMB, 0,
                            Ability.STOP, 3,
                            Ability.UMBRELLA, 0
                    ),
                    0.25,
                    3,
                    "Just digging",
                    600, 0
            );

            createLevel(
                    2, 2, 1100, 340, 400, 30, 0, 0,
                    Map.of(
                            Ability.DIGGER, 0,
                            Ability.CLIMB, 0,
                            Ability.STOP, 0,
                            Ability.UMBRELLA, 3
                    ),
                    1.0,
                    3,
                    "Cap 2",
                    400, 0
            );

            createLevel(
                    3, 3, 1050, 260, 410, 200, 430, 0,
                    Map.of(
                            Ability.DIGGER, 0,
                            Ability.CLIMB, 10,
                            Ability.STOP, 2,
                            Ability.UMBRELLA, 10
                    ),
                    0.8,
                    5,
                    "Cap 3",
                    410, 0
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Agregamos el listener del mouse
        getFrame().addMouseListener(this.getMouse());
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png");
        this.getFrame().setIconImage(icon.getImage());
        buttonController = new ButtonController(this.getMouse(), screenWidth, screenHeight);
        gameMenu = new GameMenuView(getWidth(), getHeight(),this);
        gamePauseView= new GamePauseView(screenWidth, screenHeight);
        gameSettingsView= new GameSettingsView(screenWidth, screenHeight,this);
        gameScoreView= new GameScoreView(screenWidth, screenHeight);
        gameWinView = new GameWinView(screenWidth, screenHeight);
    }

public boolean mouseTracker(int x, int y, int width,int height, Mouse m){
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && m.isLeftButtonPressed() ;
    }

    public boolean detectPlay(Mouse m) {
        return mouseTracker(screenWidth/2 - 100, 300,200,60, m) && !isInSettings;
    }

    public boolean detectPlay(Keyboard k){
        return k.isKeyPressed(10) && !isInSettings && !isInScore;
    }

    public boolean detectSetting(Mouse m) {
        return  mouseTracker(screenWidth - 250,screenHeight-110,150,80, m) && !isInSettings && !isInScore;
    }
        public boolean detectScore(Mouse m) {
        return  mouseTracker(250,screenHeight-110,150,80, m) && !isInSettings && !isInScore;
    }

    @Override
    public void gameUpdate(double delta) {

        if(isInSettings){
            gameSettingsController= new GameSettingsController(gameSettingsView, this);
        }

        if(isInMenu){
            gameMenu.update(delta);

            if (detectPlay(getMouse()) || detectPlay(getKeyboard())) {
            isInMenu=false;
            }
            if(detectSetting(getMouse()) || getKeyboard().isKeyPressed(KeyEvent.VK_C)){
                isInSettings=!isInSettings;
            }

            if(detectScore(getMouse()) || getKeyboard().isKeyPressed(KeyEvent.VK_S)){
                isInScore=!isInScore;
            }
        }
        else{

            if(getKeyboard().isKeyPressed(KeyEvent.VK_P)){
                gamePause=true;
                if(wantsBackMenu(getKeyboard())){
                    isInMenu=!isInMenu;
                }
            }

            if(gameWin && getKeyboard().isKeyPressed(KeyEvent.VK_P)){
                isInMenu=true;
            }
            
            if(gamePause && pauseGame()){
                gamePause=false;
            }

            if(!gamePause){
                buttonController.update();
                levelControllers.get(currentLevel).update(delta);
            

                if(levelModels.get(currentLevel).isLevelFinished()){
                    if (levelModels.get(currentLevel).isLevelWon()) {
                        nextLevel();
                    }else{
                        repeatLevel();
                    }
                }
            }
        }
    }


    public boolean wantsBackMenu(Keyboard keyboard) {
        return keyboard.isKeyPressed(KeyEvent.VK_ENTER);
    }

    public boolean pauseGame() {
        boolean currentPressed = getKeyboard().isKeyPressed(KeyEvent.VK_P);

        if (prevPausePressed == null) {
            prevPausePressed = currentPressed;
            return false;
        }

        boolean justPressed = currentPressed && !prevPausePressed;
        prevPausePressed = currentPressed;
        return justPressed;
    }


    @Override
    public void gameDraw(Graphics2D g) {


        if(isInMenu){
            gameMenu.drawmenu(g);
            
            if(isInSettings){
                gameSettingsView.drawmenu(g);
            }
            if(isInScore){
                gameScoreView.draw(g);
            }
        }
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            levelControllers.get(currentLevel).draw(g);

            if(gamePause){
                gamePauseView.draw(g);
            }

            if(gameWin){
                gameWinView.draw(g);
            }
        }

    }

    @Override
    public void gameShutdown() {
    }

    private void setFullScreen() {
        JFrame frame = this.getFrame();
        frame.dispose(); // Necesario para cambiar el modo antes de que se muestre
    
        frame.setUndecorated(true); // Sin bordes ni barra de título
        frame.setResizable(false);  // No redimensionable
    
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame); // ¡Pantalla completa real!
    }


    private void nextLevel() {
        if (currentLevel < levelModels.size() - 1) {
            currentLevel++;
            System.out.println("¡Pasaste al nivel " + (currentLevel + 1) + "!");
        } else {
            System.out.println("¡Felicitaciones! Completaste todos los niveles.");
            for(LevelModel l : levelModels){
                pointsSum += l.getPointsLevel();
            }
            gameWin=true;
            LocalDateTime dateHour = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateHourFormatter = dateHour.format(formatter);
            ScoreDatabase.saveScore(dateHourFormatter, pointsSum);
        }
    }

    private void repeatLevel(){
        System.out.println("Se repetira el nivel");
    }

    private void createLevel(
            int levelNumber, int mapId, int exitX, int exitY,
            int spawnerX, int spawnerY, int offsetX, int offsetY,
            Map<Ability, Integer> stockAbilities,
            double percentageToWin,
            int lemmingsToGenerate,
            String levelName,
            int camX, int camY
    ) {
        try {
            MapModel mapModel = new MapModel(mapId, offsetY, db, exitX, exitY);
            SpawnerView spawnerView = new SpawnerView(spawnerX, spawnerY);
            ExitView exitView = new ExitView(exitX, exitY);
            MapView mapView = new MapView(mapModel, spawnerView, exitView, camX, camY, screenWidth, screenHeight);

            Stock stock = new Stock(new HashMap<>(stockAbilities));
            LevelModel levelModel = new LevelModel(mapModel, stock, lemmingsToGenerate, percentageToWin, levelNumber, levelName, mapModel.getExit(), spawnerX, spawnerY);
            LevelView levelView = new LevelView(levelModel, mapView, screenWidth, screenHeight);
            MinimapModel minimapModel = new MinimapModel(mapView, levelView, levelModel);
            LevelController levelController = new LevelController(levelModel, levelView, getKeyboard(), getMouse(), camX, camY, minimapModel, screenWidth, screenHeight, fullScreen);

            mapModels.add(mapModel);
            mapViews.add(mapView);
            levelModels.add(levelModel);
            levelViews.add(levelView);
            minimapModels.add(minimapModel);
            levelControllers.add(levelController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    