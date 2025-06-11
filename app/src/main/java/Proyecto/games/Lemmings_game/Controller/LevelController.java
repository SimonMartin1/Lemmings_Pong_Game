package Proyecto.games.Lemmings_game.Controller;

import Proyecto.games.Lemmings_game.Model.CursorModel;
import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.Model.LevelModel;
import Proyecto.games.Lemmings_game.Model.MinimapModel;
import Proyecto.games.Lemmings_game.View.LemmingView;
import Proyecto.games.Lemmings_game.View.LevelView;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelController {
    private final LevelModel levelModel;
    private final LevelView levelView;
    private CursorModel cursorModel;
    private MinimapModel mapModel;
    //private int panelWidth = 1366;
    //private int panelHeight = 768;
    private final int screenWidth;
    private final int screenHeight;

    private final MinimapModel minimapModel;
    private final Mouse mouse;
    private final Keyboard keyboard;
    private int camX;
    private final int camY = 0;
    
    private final List<LemmingView> lemmingViews = new ArrayList<LemmingView>();

    private boolean isStarting;


    public LevelController(LevelModel lvlModel, LevelView lvlView, Keyboard k, Mouse m, int camX, int camY, MinimapModel minimapModel, int screenWidth, int screenHeight) {
        this.levelModel = lvlModel;
        this.levelView = lvlView;
        this.cursorModel = new CursorModel(lvlModel.getStock(), m, screenWidth, screenHeight);

        this.levelView.setCamX(camX);
        //this.levelView.setCamY(camY);

        this.keyboard = k;
        this.mouse = m;
        //this.camX = levelView.getCamX();

        this.camX = camX;
        //this.camY = camY;
        this.minimapModel = minimapModel;
        cursorModel.setCurrentLemmings(levelModel.getLemmings());
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void setStarting(boolean value) {
        this.isStarting = value;
    }

    public void update(double delta) {

        if (mouse.isLeftButtonPressed()) {
            if (mouse.getX() <= 770 && mouse.getX() >= 520 && mouse.getY() >= 480 && mouse.getY() <= 580) {
                minimapModel.handleClick(mouse.getX(), mouse.getY());
                System.out.println("MASTANTUONOOOO");
            }
        }

        levelModel.setCamX(this.camX);


        if (keyboard.isKeyPressed(KeyEvent.VK_I) || mouse.isLeftButtonPressed()) {
            isStarting = false;
        }


    if(levelModel.isLevelFinished())

    {
        if (keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
            reset();
        }
    }

        levelModel.update(delta);
        cursorModel.setCamX(levelView.getCamX());
        cursorModel.update();

    syncLemmingViews();


    }

    public void draw(Graphics2D g){

        if(isStarting){
            levelView.drawPreLevelScreen(g);
        }
        else{
            if(levelModel.isLevelFinished()){
                for (LemmingModel l : levelModel.getLemmings()){
                    System.out.println(l.isActive());
                }

                levelView.drawEndScreen(g);
            }
            else{
                levelView.drawLevel(g ,screenWidth , screenHeight);

                for (LemmingView view : lemmingViews) {
                    this.camX = levelView.getCamX();
                    //System.out.println("camX en controller: " + camX );

                    view.draw(g, camX, camY);
                }
            }
        }


    }


    public boolean wantsToStart(Keyboard k){
        return k.isKeyPressed(KeyEvent.VK_I);
    }

    private void syncLemmingViews() {
        java.util.List<LemmingModel> lemmingModels = levelModel.getLemmings();

        lemmingViews.removeIf(view -> !lemmingModels.contains(view.getModel()));

        while (lemmingViews.size() < lemmingModels.size()) {
            LemmingModel newModel = lemmingModels.get(lemmingViews.size());
            lemmingViews.add(new LemmingView(newModel));
        }
    }


    public void reset(){

        levelModel.reset();
        levelView.reset();

        this.cursorModel = new CursorModel(levelModel.getStock(), mouse, screenWidth, screenHeight);
        cursorModel.setCurrentLemmings(levelModel.getLemmings());
    }
}
