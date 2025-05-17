package Proyecto.Games.Pong_game.Model;



public class PongModel {
    
}


abstract class Entity{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
}


final class ball extends Entity{
    private int speedX;
    private int speedY;
    private int radius;

}

final class paddle extends Entity{
    private short id_player;
    private int speedx;
    private int speedy;

}





