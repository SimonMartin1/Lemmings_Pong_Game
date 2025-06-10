package Proyecto.games.Lemmings_game.Controller;

import Proyecto.games.Lemmings_game.Model.CursorModel;
import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.Model.LevelModel;
import Proyecto.games.Lemmings_game.Model.MapModel;
import Proyecto.games.Lemmings_game.Model.MinimapModel;
import Proyecto.games.Lemmings_game.View.*;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelController {
    private LevelModel levelModel;
    private LevelView levelView;
    private CursorModel cursorModel;
    private MinimapModel mapModel;
    private int panelWidth = 1366;
    private int panelHeight = 768;
    
    private MinimapModel minimapModel;
    private Mouse mouse;
    private Keyboard keyboard;

    private int camX;
    private int camY;

    private boolean isStarting = true;

    private java.util.List<LemmingView> lemmingViews = new java.util.ArrayList<LemmingView>();


    public LevelController(LevelModel lvlModel, LevelView lvlView,  Keyboard k, Mouse m, int camX, int camY, MinimapModel minimapModel){
        this.levelModel = lvlModel;
        this.levelView = lvlView;
        this.cursorModel = new CursorModel(lvlModel.getStock(), m);

        this.levelView.setCamX(camX);
        this.levelView.setCamY(camY);

        this.keyboard = k;
        this.mouse = m;
        //this.camX = levelView.getCamX();

        this.camX = camX;
        this.camY = camY;
        this.minimapModel = minimapModel;
        cursorModel.setCurrentLemmings(levelModel.getLemmings());
    }

    public void update(double delta){
        //OJITO ACA LRPM


        if(mouse.isLeftButtonPressed()){
            if(mouse.getX() <= 730 && mouse.getX() >= 480 && mouse.getY() >= 480 && mouse.getY() <= 580){
                minimapModel.handleClick(mouse.getX(), mouse.getY());
            }
        }

        levelModel.setCamX(this.camX);

        if (isStarting) {
            if (keyboard.isKeyPressed(KeyEvent.VK_I) || mouse.isLeftButtonPressed()) {
                isStarting = false;
            }

            return;
        }


        levelModel.update(delta);
        cursorModel.setCamX(levelView.getCamX());
        cursorModel.update();
        syncLemmingViews();
    }



    public void draw(Graphics2D g){

        if(isStarting){
            levelView.drawPreLevelScreen(g);
        }else{
            if(levelModel.isLevelFinished()){
                for (LemmingModel l : levelModel.getLemmings()){
                    System.out.println(l.isActive());
                }

                levelView.drawEndScreen(g);
            }
            else{
                levelView.drawLevel(g ,panelWidth , panelHeight);

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

}
