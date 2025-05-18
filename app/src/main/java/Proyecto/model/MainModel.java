package Proyecto.model;

import Proyecto.games.Lemmings_game.Lemmings;
import Proyecto.games.Pong_game.Pong;


public class MainModel {
private Object runtimegame=null;
    public MainModel(){
        
    }
    public Object runGame(int i){
        Object game=null;
        switch (i) {
            case 0:
                if(runtimegame==null){
                Lemmings lemmings = new Lemmings("Lemmings", 1200, 800);
                lemmings.run(1.0 / 60.0);
                runtimegame = lemmings;
                game= lemmings;
                }
                break;
            case 1:
                if(runtimegame==null){
                Pong pong = new Pong("Pong", 1200, 800);
                pong.run(1.0 / 60.0);
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

