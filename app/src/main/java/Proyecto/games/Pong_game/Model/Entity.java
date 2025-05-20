package Proyecto.games.Pong_game.Model;

abstract class Entity {
    protected int dx;
    protected int dy;
    protected int x;
    protected int y;

    protected int getPositionX() {
        return this.x;
    }

    protected int getPositionY() {
        return this.y;
    }

}