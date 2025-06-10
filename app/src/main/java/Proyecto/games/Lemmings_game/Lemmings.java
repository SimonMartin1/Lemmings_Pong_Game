package Proyecto.games.Lemmings_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import Proyecto.games.Lemmings_game.View.ExitView;
import Proyecto.games.Lemmings_game.View.GameMenuView;
import Proyecto.games.Lemmings_game.View.LevelView;
import Proyecto.games.Lemmings_game.View.MapView;
import Proyecto.games.Lemmings_game.View.SpawnerView;
import Proyecto.games.Lemmings_game.View.GameSettingsView;


public class Lemmings extends JGame {

    GameMenuView gameMenu;

    ButtonController buttonController;

    private int currentLevel = 1;

    private final List<MapModel> mapModels  = new ArrayList<>();
    private final List<MapView> mapViews = new ArrayList<>();
    private final List<LevelModel> levelModels = new ArrayList<>();
    private final List<LevelView> levelViews = new ArrayList<>();
    private final List<LevelController> levelControllers = new ArrayList<>();
    private GameSettingsView settingsView;
    private final static boolean fullScreen = false;
    private boolean isInMenu = true, isInSettings=false, gamePause = false, gameOver = false,twoplayers,musicOFF;
    private final int screenWidth = getWidth();
    private final int screenHeight = getHeight();
    private final boolean scoreAlreadySaved = false;

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    
    public static void main(String[] args) {
        if(!fullScreen){
            Lemmings game = new Lemmings("Lemmings", 800, 600);
            game.run(1.0 / 60.0); // 60 FPS
        }else{
            Lemmings game = new Lemmings("Lemmings", 1280, 1000);
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
                Ability.STOP, 1,
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


        LevelModel firstLevelModel = new LevelModel(mapModels.get(0), stockLevelOne, 3, .8, 1, "Just digging", mapModels.get(0).getExit(), 690, 70);
        LevelModel secondLevelModel = new LevelModel(mapModels.get(1), stockLevelTwo, 3, .8, 2, "Cap 2",   mapModels.get(1).getExit(), 400, 30);
        LevelModel thirdLevelModel = new LevelModel(mapModels.get(2), stockLevelThree, 3, .8, 3, "Cap 3", mapModels.get(2).getExit(), 410, 200);
        levelModels.add(firstLevelModel);
        levelModels.add(secondLevelModel);
        levelModels.add(thirdLevelModel);

        LevelView firstLevelView = new LevelView( levelModels.get(currentLevel), mapViews.get(currentLevel), stockLevelOne, screenWidth, screenHeight);
        LevelView secondLevelView = new LevelView( levelModels.get(1), mapViews.get(1), stockLevelTwo, screenWidth, screenHeight);
        LevelView thirdLevelView = new LevelView( levelModels.get(2), mapViews.get(2), stockLevelThree, screenWidth, screenHeight);


        levelViews.add(firstLevelView);
        levelViews.add(secondLevelView);
        levelViews.add(thirdLevelView);

        //minimapmodel
        MinimapModel minimapModel = new MinimapModel(mapViews.get(currentLevel), levelViews.get(currentLevel), levelModels.get(currentLevel));

        levelControllers.add(new LevelController(levelModels.get(currentLevel), levelViews.get(currentLevel), getKeyboard(), getMouse(), 0, 0, minimapModel, screenWidth, screenHeight));
        levelControllers.add(new LevelController(levelModels.get(1), levelViews.get(1), getKeyboard(), getMouse(), 430, 0, minimapModel, screenWidth, screenHeight));
        levelControllers.add(new LevelController(levelModels.get(2), levelViews.get(2), getKeyboard(), getMouse(), 430, 0, minimapModel, screenWidth, screenHeight));

        //Agregamos el listener del mouse
        getFrame().addMouseListener(this.getMouse());


        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png");
        this.getFrame().setIconImage(icon.getImage());

        buttonController = new ButtonController(this.getMouse(), screenWidth, screenHeight);
        gameMenu = new GameMenuView(getWidth(), getHeight(), this);
    }

    @Override
    public void gameUpdate(double delta) {
        
        if(isInSettings){
            //settingController = new SettingController(settingsView, settingsModel, getMouse(), this);
        }

        if(isInMenu){
            gameMenu.update(delta);
            if(gameMenu.detectSetting(getMouse())){ 
                if(isInSettings){
                    /*saveSettings();+/* */
                } 
                isInSettings = !isInSettings;  
            }
            if((gameMenu.detectPlay(getMouse()) || gameMenu.detectPlay(getKeyboard()))){ 
                isInMenu = false; 
                levelControllers.get(0).setStarting(true);
                levelControllers.get(0).update(delta);
                //buttonController.update(); 
                
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
                levelControllers.get(0).draw(g);
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
    
    }


    