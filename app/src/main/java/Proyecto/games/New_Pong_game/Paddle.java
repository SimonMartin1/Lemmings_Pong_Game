package Proyecto.games.New_Pong_game;

import Proyecto.games.utils.Drawable;

import java.awt.*;

public class Paddle implements Drawable {
    private int MOVE_AMOUNT = 700;

    private final double PADDLE_WIDTH;
    private final double PADDLE_HEIGHT;
    private final Color PADDLE_COLOR = Color.WHITE;


    private boolean isMoveDown = false;
    private boolean isMoveUp = false;
    private int y, x;
    private boolean paused = false;
    final private int initialY;
    private final int width;
    private final int height;

    public Paddle(int width, int height, int initialY, int x) {
        this.y = initialY;
        this.initialY = initialY;
        this.x = x;

        this.width = width;
        this.height = height;

        this.PADDLE_HEIGHT = height * .25;
        this.PADDLE_WIDTH = width * 0.01;

    }

    public void setMoveDown(boolean isMoveDown){
        this.isMoveDown = isMoveDown;
    }

    public void setMoveUp(boolean isMoveUp){
        this.isMoveUp = isMoveUp;
    }

    public void update(double delta){
        if(isMoveUp){
            if(y>=(int) (height*.07)){
                y-= MOVE_AMOUNT*delta;
            }else{
                y = (int) (height*.07) + 5;
            }
        }
        if (isMoveDown) {
            if(y<=(int) (height*.75)){
                y+= MOVE_AMOUNT*delta;
            }else{
                y = (int) (height*.75) + 1;
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
        g2d.fillRect(x, y, (int) PADDLE_WIDTH, (int) PADDLE_HEIGHT);
    }
}
