package Proyecto.games.New_Pong_game;

import java.awt.*;

public class Paddle implements Drawable{
    private int MOVE_AMOUNT = 700;

    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_HEIGHT = 150;
    private final Color PADDLE_COLOR = Color.WHITE;


    private boolean isMoveDown = false;
    private boolean isMoveUp = false;
    private int y, x;
    private boolean paused = false;
    final private int initialY;

    public Paddle(int initialY, int x) {
        this.y = initialY;
        this.initialY = initialY;
        this.x = x;

        // TODO: LEER LAS PROPERTIES
    }

    // TODO: VER ESTA FUNCIÓN QUE ESTA RARA
    public void updateSize(int height, int movement){
        this.MOVE_AMOUNT=movement;
    }

    public void setMoveDown(boolean isMoveDown){
        this.isMoveDown = isMoveDown;
    }

    public void setMoveUp(boolean isMoveUp){
        this.isMoveUp = isMoveUp;
    }

    public void update(double delta){
        if(isMoveUp){
            if(y>=40){
                y-= MOVE_AMOUNT*delta;
            }else{
                y = 35;
            }
        }
        if (isMoveDown) {
            if(y<=450){
                y+= MOVE_AMOUNT*delta;
            }else{
                y = 451;
            }
        }
    }

    public int getY() {
        return y;
    }

    public void reset(){
        this.y = initialY;
        this.isMoveDown = false;
    }

    public void pause() {
        this.paused = !this.paused;
    }

    public void draw(Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(PADDLE_COLOR);
        g2d.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
