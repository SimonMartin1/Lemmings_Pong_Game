package Proyecto.games.Lemmings_game;

import Proyecto.games.Lemmings_game.Controller.ButtonController;
import Proyecto.games.Lemmings_game.Controller.GameSettingsController;
import Proyecto.games.Lemmings_game.Controller.LevelController;
import Proyecto.games.Lemmings_game.Model.*;
import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.ScoreDatabase;
import Proyecto.games.Lemmings_game.View.*;
import Proyecto.games.Pong_game.Model.SettingsModel;
import Proyecto.utils.SoundPlayer;
import com.entropyinteractive.JGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;

public class Lemmings extends JGame {

    GameMenuView gameMenu;

    ButtonController buttonController;

    private int currentLevel = 0;
    private ScoreDatabase db;

    private final List<MapModel> mapModels = new ArrayList<>();
    private final List<MapView> mapViews = new ArrayList<>();
    private final List<LevelModel> levelModels = new ArrayList<>();
    private final List<LevelView> levelViews = new ArrayList<>();
    private final List<LevelController> levelControllers = new ArrayList<>();
    private GameSettingsView gameSettingsView;
    private GamePauseView gamePauseView;
    private GameScoreView gameScoreView;
    private GameWinView gameWinView;
    private GameSettingsController gameSettingsController;
    private SettingsModel.Settings Settings, backUpSettings;
    private static boolean fullScreen = false;
    private static boolean musicOff = true;
    private boolean isInMenu = true, isInSettings = false, gamePause = false, isInScore = false, gameWin = false;
    private int screenWidth = getWidth();
    private int screenHeight = getHeight();
    private int pointsSum;
    private Boolean prevPausePressed = null;



    private final List<MinimapModel> minimapModels = new ArrayList<>();

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {


        configReader();

        System.out.println(fullScreen);

        if (!fullScreen) {
            Lemmings game = new Lemmings("Lemmings", 800, 600);
            game.run(1.0 / 60.0); // 60 FPS
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int width = screenSize.width;
            int height = screenSize.height;

            Lemmings game = new Lemmings("Lemmings", width, height);
            game.run(1.0 / 60.0); // 60 FPS
        }


    }

    public static void configReader() {
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

    public void backUpSettings() {
        backUpSettings = new SettingsModel.Settings();
        backUpSettings.fullScreen = fullScreen;
        backUpSettings.musicOff = musicOff;
    }

    public SettingsModel.Settings getbackUp() {
        return backUpSettings;
    }

    public void saveSettings() {
        GameSettingsModel.saveSettings(musicOff, fullScreen);
    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }

    public void setMusicOFF(boolean option) {
        this.musicOff = option;
    }

    public boolean getIsinsettings() {
        return this.isInSettings;
    }

    public void setIsinsettings() {
        this.isInSettings = !this.isInSettings;
    }

    public boolean getIsinMenu() {
        return this.isInMenu;
    }

    public void setIsinMenu(boolean option) {
        this.isInMenu = option;
    }

    public void setFullScreen(boolean enable) {
        Frame frame = this.getFrame();
        if (enable) {
            frame.dispose();
            frame.setUndecorated(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.screenWidth = screenSize.width;
            this.screenHeight = screenSize.height;
        } else {
            frame.dispose();
            frame.setUndecorated(false);
            frame.setExtendedState(Frame.NORMAL);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            this.screenWidth = 800;
            this.screenHeight = 600;
        }
        // Actualiza el tamaño de las vistas y modelos
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


        try {
            MapModel firstLevelMapModel = new MapModel(1, 0, db, 1020, 300);
            MapModel secondLevelMapModel = new MapModel(2, 0, db, 1100, 340);
            MapModel thirdLevelMapModel = new MapModel(3, 0, db, 1050, 260);


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
                    600, 100
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
                    400, 30
            );

            createLevel(
                    3, 3, 1050, 260, 410, 200, 430, 0,
                    Map.of(
                            Ability.DIGGER, 0,
                            Ability.CLIMB, 5,
                            Ability.STOP, 2,
                            Ability.UMBRELLA, 5
                    ),
                    0.8,
                    5,
                    "Cap 3",
                    410, 200
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Agregamos el listener del mouse
        getFrame().addMouseListener(this.getMouse());

        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png");
        this.getFrame().setIconImage(icon.getImage());
        //SoundPlayer.playSound("app/src/main/resources/cantinadelpela.wav");
        buttonController = new ButtonController(this.getMouse(), screenWidth, screenHeight);
        gameMenu = new GameMenuView(getWidth(), getHeight(), this);
        gameSettingsView = new GameSettingsView(screenWidth, screenHeight, null);
    }

    public void playTrack(){
        if(!musicOff){
            SoundPlayer.playSound("app/src/main/resources/cantinadelpela.wav");
        }
    }

    public void initSettings(){
        Settings= SettingsModel.loadSettings();
        musicOff=Settings.musicOff;
        fullScreen=Settings.fullScreen;
    }


    @Override
    public void gameUpdate(double delta) {

        if (isInSettings) {
            gameSettingsController = new GameSettingsController(gameSettingsView, this);
        }

        if (isInMenu) {
            gameMenu.update(delta);

            if (gameMenu.detectPlay(getMouse()) || gameMenu.detectPlay(getKeyboard())) {
                isInMenu = false;
            }
            if (gameMenu.detectSetting(getMouse()) || getKeyboard().isKeyPressed(KeyEvent.VK_C)) {
                isInSettings = !isInSettings;
            }
        } else {
            buttonController.update();
            levelControllers.get(currentLevel).update(delta);

            // Chequeo si se completó el nivel

            if (levelModels.get(currentLevel).isLevelFinished()) {
                if (levelModels.get(currentLevel).isLevelWon()) {
                    this.nextLevel();
                }
            }

        }
    }


    @Override
    public void gameDraw(Graphics2D g) {


        if (isInMenu) {
            gameMenu.drawmenu(g);

            if (isInSettings) {
                gameSettingsView.drawmenu(g);
            }
            if (isInScore) {
                gameScoreView.draw(g);
            }

            //settingsView.drawmenu(g);
        }
        else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            levelControllers.get(currentLevel).draw(g);

            if (gamePause) {
                gamePauseView.draw(g);
            }

            if (gameWin) {
                gameWinView.draw(g);
            }

        }
    }

    private void setFullScreen() {
        JFrame frame = this.getFrame();
        frame.dispose(); // Necesario para cambiar el modo antes de que se muestre

        frame.setUndecorated(true); // Sin bordes ni barra de título
        frame.setResizable(false);  // No redimensionable

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame); // ¡Pantalla completa real!

        frame.setVisible(true);
    }

    private void nextLevel() {
        if (currentLevel < levelModels.size() - 1) {
            currentLevel++;
            System.out.println("¡Pasaste al nivel " + (currentLevel + 1) + "!");
        } else {
            System.out.println("¡Felicitaciones! Completaste todos los niveles.");
            // Podés reiniciar o mostrar un mensaje de fin de juego
            // gameMenuView.reset(); o lo que necesites
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
            LevelController levelController = new LevelController(levelModel, levelView, getKeyboard(), getMouse(), camX, camY, minimapModel, screenWidth, screenHeight);

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
