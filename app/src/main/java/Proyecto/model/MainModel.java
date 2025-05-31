package Proyecto.model;

import Proyecto.games.Lemmings_game.Lemmings;
import Proyecto.games.Pong_game.Pong;
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
        Lemmings game = new Lemmings("Lemmings", 800, 600);
            if(runtimegame==null){
                SwingUtilities.invokeLater(() -> {
                    new Thread(() -> game.run(1.0/60.0)).start();
                    runtimegame = game;
                });
                
            }
            game.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
            game.stop(); 
            runtimegame = null; 
        }
});
            break;
    case 1:
        Pong game2 = new Pong("Lemmings", 800, 600);
            if(runtimegame==null){
                SwingUtilities.invokeLater(() -> {
                    new Thread(() -> game2.run(1.0/60.0)).start();
                    runtimegame = game2;
                });
                
            }
            game2.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
            game2.stop(); 
            runtimegame = null; 
        }
});
            break;
    }
}
    public Object getRuntimegame() {
        return runtimegame;
    }
}

