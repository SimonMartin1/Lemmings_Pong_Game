package Proyecto.games.Lemmings_game.view;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Proyecto.games.Common_files.JGame;

//taking in mind the game loop implementation, JGame creates a Frame and a Panel 
//that holds the game canvas, so we can use it to create the view of the game

public class LemmingsView {
    final private JFrame Game_Frame;
    private JPanel login_Panel;
    public LemmingsView(JGame game) {
        this.Game_Frame=game.getFrame();
        this.login_Panel=game.getCanvas();
        login_Panel = new config_screen();
        Game_Frame.setSize(game.getWidth(), game.getHeight());
        Game_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Game_Frame.setLocationRelativeTo(null);
        Game_Frame.add(login_Panel);
        Game_Frame.setVisible(true);

    }

    public JFrame getGameFrame(){
        return Game_Frame;
    }
}
