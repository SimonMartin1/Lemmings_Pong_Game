package Proyecto.model;

import Proyecto.games.Lemmings_game.Lemmings;
import Proyecto.games.Pong_game.Pong;
import Proyecto.controller.MainController;


public class MainModel {
private Object runtimegame=null;
    public MainModel(){
        
    }
    public Object runGame(int i){
        Object game=null;
        switch (i) {
            case 0:
                if(runtimegame==null){
                Lemmings lemmings = new Lemmings("Lemmings", 800, 600);
                runtimegame = lemmings;
                game= lemmings;
                }
                break;
            case 1:
                if(runtimegame==null){
                Pong pong = new Pong("Pong", 800, 600);
                runtimegame = pong;
                game= pong;
                }
                break;
        }
        return game;
    }

    public Object getRuntimegame() {
        return runtimegame;
    }
}

