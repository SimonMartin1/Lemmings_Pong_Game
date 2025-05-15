package Proyecto.games.Pong_game.View;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Panel;



public class PongView extends Frame {
    
    private CardLayout login_to_play = new CardLayout();
    private Panel login_Panel = new Panel(login_to_play);
    private Button startButton = new Button("Start");
    
    public PongView() {
        
    }

}

class login_Menu extends Panel {
    
}


class main_Menu extends Panel {
    
}
