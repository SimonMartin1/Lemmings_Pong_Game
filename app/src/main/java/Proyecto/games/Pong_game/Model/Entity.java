package Proyecto.games.Pong_game.model;

abstract class Entity{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    public int getPositionX() {
        return this.x;
    }
    public int getPositionY() {
        return this.y;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract void update(); // para moverse, chequear estado, etc.

}

