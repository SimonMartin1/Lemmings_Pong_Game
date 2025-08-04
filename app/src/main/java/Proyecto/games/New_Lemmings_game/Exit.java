package Proyecto.games.New_Lemmings_game;

import java.awt.*;
import java.io.IOException;

public class Exit extends Gate {
    private int camX;
    //private int x;
    //private int y;
    public int savedLemmings = 0;
    private final int width = 100;
    private final int height = 100;
    //private int width;
    //private int height; 
    
    public Exit(int x, int y, int camX) {

        super(x,y,33, 25, 1, "/lemmingsAnimation/lemming_exit.png", camX);

        try {
            this.loadAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, int camX, int camY) {
        int drawX = getX() - camX;
        int drawY = getY() - camY;
        if (frames != null && frames[currentFrameIndex] != null) {
            updateAnimation();
            g.drawImage(frames[currentFrameIndex], drawX, drawY,100 ,80,null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x- camX, y, width, height);
    }

    public boolean checkLemming(Lemming_Entity lemmingEntity){
        if(getBounds().intersects(lemmingEntity.getX() - camX, lemmingEntity.getY(),16,16)){
            System.out.println("llege a la salida");
        }
        return getBounds().intersects(lemmingEntity.getX() - camX, lemmingEntity.getY(),16,16);
    }

    public void sumLemming(Lemming_Entity lemmingEntity){
        if (checkLemming(lemmingEntity) && !lemmingEntity.isSaved()) {

            //TODO: SUMAR 10 PUNTOS AL JUGADOR POR LEMMING.

            savedLemmings++;
            lemmingEntity.setSaved(true);
            //Aca tengo que modificar para que se actualice el estado del lemming actual
            //lemming.setStateLemming(LemmingState.EXITED);
            System.out.println("lemming salido: " + savedLemmings);
        }
    }

    public int getSavedLemmings() {
        return savedLemmings;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
