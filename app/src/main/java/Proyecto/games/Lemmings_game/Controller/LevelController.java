package Proyecto.games.Lemmings_game.Controller;

import Proyecto.games.Lemmings_game.Model.CursorModel;
import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.Model.LevelModel;
import Proyecto.games.Lemmings_game.View.*;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelController {
    private LevelModel levelModel;
    private LevelView levelView;
    private CursorModel cursorModel;

    private Mouse mouse;
    private Keyboard keyboard;

    private int camX;
    private int camY;

    private boolean isStarting = true;

    private java.util.List<LemmingView> lemmingViews = new java.util.ArrayList<LemmingView>();


    public LevelController(LevelModel lvlModel, LevelView lvlView,  Keyboard k, Mouse m, int camX, int camY){
        this.levelModel = lvlModel;
        this.levelView = lvlView;
        this.cursorModel = new CursorModel(m);

        this.levelView.setCamX(camX);
        this.levelView.setCamY(camY);

        this.keyboard = k;
        this.mouse = m;

        this.camX = camX;
        this.camY = camY;

        cursorModel.setCurrentLemmings(levelModel.getLemmings());
    }

    public void update(double delta){

        levelModel.setCamX(this.camX);

        if (isStarting) {
            if (keyboard.isKeyPressed(KeyEvent.VK_ENTER) || mouse.isLeftButtonPressed()) {
                isStarting = false;
            }

            return;
        }

        levelModel.update(delta);

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
                levelView.drawLevel(g);

                for (LemmingView view : lemmingViews) {
                    view.draw(g);
                }
            }
        }


    }

    private void syncLemmingViews() {
        java.util.List<LemmingModel> lemmingModels = levelModel.getLemmings();

        while (lemmingViews.size() < lemmingModels.size()) {
            LemmingModel newModel = lemmingModels.get(lemmingViews.size());
            lemmingViews.add(new LemmingView(newModel));
        }
    }

}
