package Proyecto.Games.Pong_game.View;
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

class PongMenu_View extends Panel implements GamesMenu {
    private boolean twoPlayers;
    private boolean difficulty;
    
    public PongMenu_View() {
        this.twoPlayers = false;
        this.difficulty = false;
    }
    
    @Override
    public void Set_TwoPlayers() {
        this.twoPlayers = true;
    }
    
    @Override
    public boolean Get_TwoPlayers() {
        return this.twoPlayers;
    }
    
    @Override
    public boolean Set_Difficulty() {
        this.difficulty = true;
        return this.difficulty;
    }
    
    @Override
    public boolean Get_Difficulty() {
        return this.difficulty;
    }
    
    @Override
    public void OpenConfiguration() {
        // Implementar la lógica para abrir la configuración
    }
    
}


class MainScreen extends Panel {

}

interface GamesMenu {
    public void Set_TwoPlayers();
    public boolean Get_TwoPlayers();
    public boolean Set_Difficulty();
    public boolean Get_Difficulty();
    public void OpenConfiguration();
}