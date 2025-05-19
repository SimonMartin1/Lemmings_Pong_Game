package Proyecto.model;

import Proyecto.games.Lemmings_game.Lemmings;
import Proyecto.games.Pong_game.Pong;

import Proyecto.games.Common_files.JGame;
import Proyecto.games.Lemmings_game.controller.LemmingsController;
import Proyecto.games.Lemmings_game.model.LemmingsModel;
import Proyecto.games.Lemmings_game.view.LemmingsView;
import java.lang.Thread;

public class MainModel {
private Object runtimegame;
    public MainModel(){
        runtimegame=null;
    }
    public void runGame(int i){
        switch (i) {
            case 0:
                if(runtimegame==null){
                Lemmings game = new Lemmings("Lemmings", 1200, 800);
new Thread(() -> game.run(1.0/60.0)).start();
                // Thread hilo = new ExecuteLemmings(game);
                // hilo.start();
                new Thread(() -> game.run(1.0/60.0)).start(); // 60 FPS
                runtimegame = game;
                }
                break;
            case 1:
                if(runtimegame==null){
                Pong game2 = new Pong("Pong", 1200, 800);
                game2.run(1.0 / 60.0);
                runtimegame = game2;
                }
                break;
        }
    }

    public Object getRuntimegame() {
        return runtimegame;
    }
}

class ExecuteLemmings extends Thread{
    private Lemmings game;
    ExecuteLemmings(Lemmings game){
        this.game=game;
    }
    @Override public void run(){
        game.run(1.0/60.0);
    }
}
