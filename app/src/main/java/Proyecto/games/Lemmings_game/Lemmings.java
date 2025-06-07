package Proyecto.games.Lemmings_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Proyecto.games.Lemmings_game.Controller.ButtonController;
import Proyecto.games.Lemmings_game.Model.CursorModel;
import com.entropyinteractive.JGame;

import Proyecto.games.Lemmings_game.Model.MapModel;
import Proyecto.games.Lemmings_game.View.Buttons;
import Proyecto.games.Lemmings_game.View.MapView;
import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.View.LemmingView;
import Proyecto.games.Lemmings_game.View.MinimapView;
import Proyecto.games.Lemmings_game.View.GameMenuView;


public class Lemmings extends JGame {

    private MapModel firstLevelMapModel;
    private MapView firstLevelMapView;
    GameMenuView gameMenuView;
    private Graphics2D g;
    private boolean animation = false; 
    private double blinkTime = 0;
    private boolean showPressText = true;
    Buttons buttonDig;
    Buttons buttonBuild;
    Buttons buttonStop;
    Buttons buttonFly;
    ButtonController buttonController;
    MinimapView minimapView;
    CursorModel cursorModel;
    //lista de mapas y vistas
    private List<MapModel> levelsModel = new ArrayList<>();
    private List<MapView> levelsView = new ArrayList<>();
    private int nivelActual = 0;

    int cantLemmings = 4;
    private double spawnTimer = 0;
    private double spawnInterval = 2; // segundos entre lemmings
    private int lemmingsToSpawn = cantLemmings;
    private int spawnedLemmings = 0;


    private java.util.List<LemmingModel> lemmingModels = new java.util.ArrayList<LemmingModel>();
    private java.util.List<LemmingView> lemmingViews = new java.util.ArrayList<LemmingView>();

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }


    @Override
    public void gameStartup() {
        //modelos
        try {
            //primer nivel
            firstLevelMapModel = new MapModel(1,650,350,0);
            //segundo nivel
            //firstLevelMapModel = new MapModel(2,650,350,0); //arregalr el offset del mapa
            //tercer nivel
            //firstLevelMapModel = new MapModel(3,650,350,0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        getFrame().addMouseListener(this.getMouse());

        //creacion lemmings 
        


        //vistas
        buttonDig = new Buttons("Cavar", 10, 450, 100, 150);
        buttonStop = new Buttons("Parar",110,450,100,150);
        buttonBuild = new Buttons("Construir",210,450,100,150);
        buttonFly = new Buttons("Volar",310,450,100,150);
        minimapView = new MinimapView(480, 480, 250, 100);

        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        firstLevelMapView = new MapView(firstLevelMapModel);

        buttonController = new ButtonController(this.getMouse());

        cursorModel = new CursorModel(this.getMouse());
        cursorModel.setCurrentLemmings(lemmingModels);

        gameMenuView = new GameMenuView(getWidth(), getHeight());
    }

    @Override
    public void gameUpdate(double delta) {
        if (!animation) {
            
            if (getMouse().isLeftButtonPressed()) {
                int mx = getMouse().getX();
                int my = getMouse().getY();
                int bx = getWidth()/2 - 100, by = 300, bw = 200, bh = 60;
                if (mx >= bx && mx <= bx + bw && my >= by && my <= by + bh) {
                    animation = true;
                }
            }
            if (getKeyboard().isKeyPressed(10)) {
                animation = true;
            }
            
            blinkTime += delta;
            if (blinkTime >= 0.6) { 
                showPressText = !showPressText;
                blinkTime = 0;
            }
        }else{
            cursorModel.update();

            for (LemmingModel l : lemmingModels) {
                l.update(delta);
            }

            // Spawn con delay
            if (spawnedLemmings < lemmingsToSpawn) {
                spawnTimer += delta;
                if (spawnTimer >= spawnInterval) {
                    spawnTimer = 0;
                    spawnedLemmings++;

                    LemmingModel nuevo = new LemmingModel(1, 300, 100, 1, 1, firstLevelMapView, firstLevelMapModel);
                    LemmingView nuevoView = new LemmingView(nuevo);
                    lemmingModels.add(nuevo);
                    lemmingViews.add(nuevoView);

                }

            }


        }

    }

    @Override
    public void gameDraw(Graphics2D g) {
            this.g=g;
            gameMenuView.draw(g);

            if (!animation && showPressText) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Click or Enter", getWidth()/2 - 71, 420);
            }

            if (animation) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                //primer nivel
                firstLevelMapView.draw(g, 430, 0);

                //segundo nivel
                //firstLevelMapView.draw(g, 300, 0);

                //tercer nivel
                firstLevelMapView.draw(g, 300, 0);
                
                //lemmingView.draw(g);
                //lemmingViews.get(0).draw(g);
                
                buttonDig.draw(g);
                buttonStop.draw(g);
                buttonBuild.draw(g);
                buttonFly.draw(g);
                minimapView.drawMinimap(g);
            
                //lemmingViews.get(0).draw(g);
                
                for (LemmingView view : lemmingViews) {
                    view.draw(g);
                }
                
            }

    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }
    }