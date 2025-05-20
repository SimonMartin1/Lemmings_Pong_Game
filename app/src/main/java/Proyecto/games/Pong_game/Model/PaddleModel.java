package Proyecto.games.Pong_game.Model;

import java.util.Vector;

public class PaddleModel extends Entity{
    public PaddleModel(int initialY) {
        this.y = initialY;
    }
    public void moveUp() {
        y+=10;
    }
    public void moveDown() {
        y-=10;
    }
    public int getY() {
        return y;
    }
}
