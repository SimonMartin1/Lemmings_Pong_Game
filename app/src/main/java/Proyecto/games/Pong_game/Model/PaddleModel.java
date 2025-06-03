package Proyecto.games.Pong_game.Model;

public class PaddleModel {
    private final int MOVE_AMOUNT = 700;
    private boolean isMoveDown = false;
    private boolean isMoveUp = false;
    private int y;
    private boolean paused = false;
    private int initialY;

    public PaddleModel(int initialY) {
        this.y = initialY;
        this.initialY = initialY;
    }

    public void setMoveDown(boolean isMoveDown){
        this.isMoveDown = isMoveDown;
    }

    public void setMoveUp(boolean isMoveUp){
        this.isMoveUp = isMoveUp;
    }


    public void update(double delta){
    if(!paused){
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
    }

    public int getY() {
        return y;
    }

    public void reset(){
        this.y = initialY;
        this.isMoveUp = false;
        this.isMoveDown = false;
    }

    public void pause() {
        this.paused = !this.paused;
    }
}
