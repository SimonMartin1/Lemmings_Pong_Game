package Proyecto.model;

import Proyecto.games.Lemmings_game.Lemmings;
import Proyecto.games.Pong_game.Pong;

import Proyecto.games.Common_files.JGame;
import Proyecto.games.Lemmings_game.controller.LemmingsController;
import Proyecto.games.Lemmings_game.model.LemmingsModel;
import Proyecto.games.Lemmings_game.view.LemmingsView;
import java.lang.Thread;

import javax.swing.SwingUtilities;

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
                SwingUtilities.invokeLater(() -> {
                    LemmingsView view = new LemmingsView(game);
                    LemmingsModel model = new LemmingsModel();
                    LemmingsController controller = new LemmingsController(model, view, game);
                    controller.initController();
                });
                new Thread(() -> game.run(1.0/60.0)).start();
                runtimegame = game;
            }
            break;
        // ... otros juegos ...
    }
}
    public Object getRuntimegame() {
        return runtimegame;
    }
}

