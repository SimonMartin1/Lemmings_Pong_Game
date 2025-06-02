package Proyecto.games.Lemmings_game.Model;

public class LemmingModel {
    int x;
    int y;
    int vx;
    int speed;

    public LemmingModel(int x, int y, int vx, int speed) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(double delta) {
       y = (int) (y + speed * delta);
    }

}
