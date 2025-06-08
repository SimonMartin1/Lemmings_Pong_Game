package Proyecto.games.Lemmings_game.Model;

import java.awt.Rectangle;

public class ExitModel {
    private final int x;
    private final int y;
    public int savedLemmings = 0;
    private final int width = 32; 
    private final int height = 32;

    public ExitModel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean checkLemming(LemmingModel lemming){
        if(getBounds().intersects(lemming.getX(),lemming.getY(),16,16)){
            //System.out.println("llege a la salida");
        }
        return getBounds().intersects(lemming.getX(),lemming.getY(),16,16);
    }

    public void sumLemming(LemmingModel lemming){
        if (checkLemming(lemming) && !lemming.isSaved()) {
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
