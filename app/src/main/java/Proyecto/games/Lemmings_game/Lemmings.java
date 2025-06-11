package Proyecto.games.Lemmings_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.entropyinteractive.JGame;

import Proyecto.games.Lemmings_game.Controller.ButtonController;
import Proyecto.games.Lemmings_game.Controller.LevelController;
import Proyecto.games.Lemmings_game.Model.LevelModel;
import Proyecto.games.Lemmings_game.Model.MapModel;
import Proyecto.games.Lemmings_game.Model.MinimapModel;
import Proyecto.games.Lemmings_game.Model.Stock;
import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.ScoreDatabase;
import Proyecto.games.Lemmings_game.View.ExitView;
import Proyecto.games.Lemmings_game.View.GameMenuView;
import Proyecto.games.Lemmings_game.View.GameSettingsView;
import Proyecto.games.Lemmings_game.View.LevelView;
import Proyecto.games.Lemmings_game.View.MapView;
import Proyecto.games.Lemmings_game.View.SpawnerView;


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
    private GameSettingsView settingsView;
    private final static boolean fullScreen = true;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, gameOver = false,twoplayers,musicOFF;
    private int screenWidth = getWidth();
    private int screenHeight = getHeight();
    private final boolean scoreAlreadySaved = false;
    private final List<MinimapModel> minimapModels = new ArrayList<>();

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    
    public static void main(String[] args) {
        if(!fullScreen){
            Lemmings game = new Lemmings("Lemmings", 800, 600);
            game.run(1.0 / 60.0); // 60 FPS
        }else{
            Lemmings game = new Lemmings("Lemmings", 1366, 768);
            game.run(1.0 / 60.0); // 60 FPS
        }

        
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
            frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            this.screenWidth = screenSize.width;
            this.screenHeight = screenSize.height;
        } else {
            frame.dispose();
            frame.setUndecorated(false);
            frame.setExtendedState(java.awt.Frame.NORMAL);
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
        if (fullScreen) {
            setFullScreen(); 
        }


        try{
            MapModel firstLevelMapModel = new MapModel(1,0, 690, 70);
            MapModel secondLevelMapModel = new MapModel(2,0, 1100, 340);
            MapModel thirdLevelMapModel = new MapModel(3,0, 1050, 260);    


            MapView firstLevelMapView = new MapView(firstLevelMapModel, new SpawnerView(690, 70), new ExitView(1020, 300), 0, 0, screenWidth, screenHeight);
            MapView secondLevelMapView = new MapView(secondLevelMapModel, new SpawnerView(400, 30), new ExitView(1100, 340), 0, 0, screenWidth, screenHeight);
            MapView thirdLevelMapView = new MapView(thirdLevelMapModel, new SpawnerView(410, 200), new ExitView(1050, 260), 430, 0, screenWidth, screenHeight);

            //Agrego los modelos
            mapModels.add(firstLevelMapModel);
            mapModels.add(secondLevelMapModel);
            mapModels.add(thirdLevelMapModel);

            //Agrego las vistas
            mapViews.add(firstLevelMapView);
            mapViews.add(secondLevelMapView);
            mapViews.add(thirdLevelMapView);

        }catch (Exception e){
            e.printStackTrace();
        }

        //MapModel map, Stock stock, int lemmingsToGenerate, int percentajeToWin, int level, String lvlName

        Stock stockLevelOne = new Stock(new HashMap<Ability, Integer>(Map.of(
                Ability.DIGGER, 5,
                Ability.CLIMB, 0,
                Ability.STOP, 3,
                Ability.UMBRELLA, 0
        )));

        Stock stockLevelTwo = new Stock(new HashMap<Ability, Integer>(Map.of(
                Ability.DIGGER, 0,
                Ability.CLIMB, 0,
                Ability.STOP, 5,
                Ability.UMBRELLA, 0
        )));

        Stock stockLevelThree = new Stock(new HashMap<Ability, Integer>(Map.of(
                Ability.DIGGER, 0,
                Ability.CLIMB, 5,
                Ability.STOP, 2,
                Ability.UMBRELLA, 3
        )));



        LevelModel firstLevelModel = new LevelModel(mapModels.get(0), stockLevelOne, 3, .8, 1, "Just digging", mapModels.get(0).getExit(), 600, 100);

        LevelModel secondLevelModel = new LevelModel(mapModels.get(1), stockLevelTwo, 3, .8, 2, "Cap 2",   mapModels.get(1).getExit(), 400, 30);
        LevelModel thirdLevelModel = new LevelModel(mapModels.get(2), stockLevelThree, 3, .8, 3, "Cap 3", mapModels.get(2).getExit(), 410, 200);
        levelModels.add(firstLevelModel);
        levelModels.add(secondLevelModel);
        levelModels.add(thirdLevelModel);

        LevelView firstLevelView = new LevelView( levelModels.get(0), mapViews.get(0), stockLevelOne, screenWidth, screenHeight);
        LevelView secondLevelView = new LevelView( levelModels.get(1), mapViews.get(1), stockLevelTwo, screenWidth, screenHeight);
        LevelView thirdLevelView = new LevelView( levelModels.get(2), mapViews.get(2), stockLevelThree, screenWidth, screenHeight);


        levelViews.add(firstLevelView);
        levelViews.add(secondLevelView);
        levelViews.add(thirdLevelView);

        //minimapmodel
        MinimapModel minimapModelOne = new MinimapModel(mapViews.get(0), levelViews.get(0), levelModels.get(0));
        MinimapModel minimapModelTwo = new MinimapModel(mapViews.get(1), levelViews.get(1), levelModels.get(1));
        MinimapModel minimapModelThree = new MinimapModel(mapViews.get(2), levelViews.get(2), levelModels.get(2));


        minimapModels.add(minimapModelOne);
        minimapModels.add(minimapModelTwo);
        minimapModels.add(minimapModelThree);

        levelControllers.add(new LevelController(levelModels.get(0), levelViews.get(0), getKeyboard(), getMouse(), 0, 0, minimapModels.get(0), screenWidth, screenHeight));
        levelControllers.add(new LevelController(levelModels.get(1), levelViews.get(1), getKeyboard(), getMouse(), 430, 0,  minimapModels.get(1), screenWidth, screenHeight));
        levelControllers.add(new LevelController(levelModels.get(2), levelViews.get(2), getKeyboard(), getMouse(), 430, 0,  minimapModels.get(2), screenWidth, screenHeight));

        //Agregamos el listener del mouse
        getFrame().addMouseListener(this.getMouse());


        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png");
        this.getFrame().setIconImage(icon.getImage());

        buttonController = new ButtonController(this.getMouse(), screenWidth, screenHeight);
        gameMenu = new GameMenuView(getWidth(), getHeight(),this);
        settingsView= new GameSettingsView(screenWidth, screenHeight, null);
    }

    @Override
    public void gameUpdate(double delta) {
        
        if(isInMenu){
            gameMenu.update(delta);
          
            if (gameMenu.detectPlay(getMouse()) || gameMenu.detectPlay(getKeyboard())) {
            isInMenu=false;
            }
            if(gameMenu.detectSetting(getMouse()) || getKeyboard().isKeyPressed(KeyEvent.VK_C)){
                isInSettings=!isInSettings;
            }
        }
        else{
            buttonController.update();
            levelControllers.get(currentLevel).update(delta);
            
            if (levelModels.get(currentLevel).isLevelFinished()) {
                nextLevel();
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
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            levelControllers.get(currentLevel).draw(g);
            mapModels.get(0).getExit().drawTest(g);
        }

    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
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
    
    }


    