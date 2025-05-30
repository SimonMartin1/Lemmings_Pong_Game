/*package Proyecto.games.Lemmings_game.view;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Proyecto.games.Common_files.JGame;

//taking in mind the game loop implementation, JGame creates a Frame and a Panel 
//that holds the game canvas, so we can use it to create the view of the game

public class LemmingsView{
    JPanel login_Panel;
    private JFrame frame;
    public LemmingsView(JGame game) {
        this.frame = game.getFrame();
        login_Panel = new config_screen();
        game.getFrame().setSize(game.getWidth(), game.getHeight());
        game.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getFrame().setLocationRelativeTo(null);
        game.getFrame().add(login_Panel);
        

    }
    public JFrame getGameFrame(){
        return frame;
    }

}
*/