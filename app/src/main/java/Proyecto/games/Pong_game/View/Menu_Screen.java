package Proyecto.games.Pong_game.view;

import javax.swing.JPanel;

import Proyecto.games.Common_files.GamesMenu;

public class Menu_Screen extends JPanel implements GamesMenu {
    private boolean twoPlayers;
    private boolean difficulty;
    
    public Menu_Screen() {
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
