package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.LemmingState;

import java.awt.Rectangle;

public class ExitModel {
    private final int x;
    private final int y;
    public int savedLemmings = 0;
    private final int width = 32;
    private final int height = 32;
    private MapModel mapModel;

    public ExitModel(int x, int y, MapModel mapModel) {
        this.x = x;
        this.y = y;
        this.mapModel = mapModel;
    }

    public Rectangle getBounds() {
        return new Rectangle(x- mapModel.getCameraX(), y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkLemming(LemmingModel lemming){
        if(getBounds().intersects(lemming.getX() - mapModel.getCameraX(), lemming.getY(),16,16)){
            //System.out.println("llege a la salida");
        }
        return getBounds().intersects(lemming.getX() - mapModel.getCameraX(), lemming.getY(),16,16);
    }

    public void sumLemming(LemmingModel lemming){
        if (checkLemming(lemming) && !lemming.isSaved()) {

            //TODO: SUMAR 10 PUNTOS AL JUGADOR POR LEMMING.

            savedLemmings++;
            lemming.setSaved(true);
            lemming.setStateLemming(LemmingState.EXITED);
            System.out.println("lemming salido: " + savedLemmings);
        }
    }

    public int getSavedLemmings() {
        return savedLemmings;
    }

}
