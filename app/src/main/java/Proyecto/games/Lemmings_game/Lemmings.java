package Proyecto.games.Lemmings_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Proyecto.games.Lemmings_game.Controller.ButtonController;
import Proyecto.games.Lemmings_game.Controller.LevelController;
import Proyecto.games.Lemmings_game.Model.*;
import Proyecto.games.Lemmings_game.View.*;
import com.entropyinteractive.JGame;


public class Lemmings extends JGame {

    GameMenuView gameMenuView;
    private Graphics2D g;

    ButtonController buttonController;

    private int nivelActual = 0;

    private List<MapModel> mapModels  = new ArrayList<>();
    private List<MapView> mapViews = new ArrayList<>();
    private List<LevelModel> levelModels = new ArrayList<>();
    private List<LevelView> levelViews = new ArrayList<>();
    private List<LevelController> levelControllers = new ArrayList<>();

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }


    @Override
    public void gameStartup() {

        try{
            MapModel firstLevelMapModel = new MapModel(1,630,350,0);
            MapModel secondLevelMapModel = new MapModel(2,650,350,0);
            MapModel thirdLevelMapModel = new MapModel(3,650,350,0);

            MapView firstLevelMapView = new MapView(firstLevelMapModel, new SpawnerView(690, 70), new ExitView(1020, 300), 430, 0);
            MapView secondLevelMapView = new MapView(secondLevelMapModel, new SpawnerView(230, 150), new ExitView(230, 150), 430, 0);
            MapView thirdLevelMapView = new MapView(thirdLevelMapModel, new SpawnerView(230, 150), new ExitView(230, 150), 430, 0);

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

        LevelModel firstLevelModel = new LevelModel(mapModels.get(0), new Stock(), 3, .8, 1, "Just digging");
        LevelModel secondLevelModel = new LevelModel(mapModels.get(1), new Stock(), 3, .8, 2, "Cap 2");
        LevelModel thirdLevelModel = new LevelModel(mapModels.get(2), new Stock(), 5, .8, 3, "Cap 3");

        levelModels.add(firstLevelModel);
        levelModels.add(secondLevelModel);
        levelModels.add(thirdLevelModel);

        LevelView firstLevelView = new LevelView( levelModels.get(0), mapViews.get(0));
        LevelView secondLevelView = new LevelView( levelModels.get(1), mapViews.get(1));
        LevelView thirdLevelView = new LevelView( levelModels.get(2), mapViews.get(2));

        levelViews.add(firstLevelView);
        levelViews.add(secondLevelView);
        levelViews.add(thirdLevelView);

        //minimapmodel
        MinimapModel minimapModel = new MinimapModel(mapViews.get(0), levelViews.get(0), levelModels.get(0));

        levelControllers.add(new LevelController(levelModels.get(0), levelViews.get(0), getKeyboard(), getMouse(), 430, 0, minimapModel));
        levelControllers.add(new LevelController(levelModels.get(1), levelViews.get(1), getKeyboard(), getMouse(), 430, 0, minimapModel));
        levelControllers.add(new LevelController(levelModels.get(2), levelViews.get(2), getKeyboard(), getMouse(), 430, 0, minimapModel));




        //Agregamos el listener del mouse
        getFrame().addMouseListener(this.getMouse());


        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png");
        this.getFrame().setIconImage(icon.getImage());

        buttonController = new ButtonController(this.getMouse());
        gameMenuView = new GameMenuView(getWidth(), getHeight());
    }

    @Override
    public void gameUpdate(double delta) {
        if (!gameMenuView.isStarting(getMouse()) && !gameMenuView.isStarting(getKeyboard())) {
            gameMenuView.update(delta);
        }else{
            //aca lvl se updatea
            buttonController.update();
            levelControllers.get(2).update(delta);
        }

    }

    @Override
    public void gameDraw(Graphics2D g) {
            this.g=g;

            if(!gameMenuView.isStarting(getMouse()) || !gameMenuView.isStarting(getKeyboard())){
                gameMenuView.draw(g);
            }
            else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());

                //Aca segundo lvl
                levelControllers.get(2).draw(g);

            }

    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }
    }