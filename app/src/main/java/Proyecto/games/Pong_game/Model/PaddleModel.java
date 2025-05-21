package Proyecto.games.Pong_game.Model;

public class PaddleModel {
    private final int PADDLE_HEIGHT = 120;
    private final int WINDOW_HEIGHT = 600;
    private final int MOVE_AMOUNT = 700;
    private final int PADDING = 10;
    private boolean isMoveDown = false;
    private boolean isMoveUp = false;
    private int y;

    public PaddleModel(int initialY) {
        this.y = initialY;
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
}
