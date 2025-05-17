package Proyecto.Model;
import Proyecto.Games.Lemmings_game.Lemmings;
import Proyecto.Games.Pong_game.Pong;


public class MainModel {
private Object runtimegame=null;
    public MainModel(){
        
    }
    public Object runGame(int i){
        Object game=null;
        switch (i) {
            case 0:
                Lemmings lemmings = new Lemmings("Lemmings", 800, 600);
                runtimegame = lemmings;
                game= lemmings;
                break;
            case 1:
                Pong pong = new Pong("Pong", 800, 600);
                runtimegame = pong;
                game= pong;
                break;
        }
        return game;
    }
}

