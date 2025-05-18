package Proyecto.games.Pong_game.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PongView extends JFrame {
    
    private CardLayout login_to_play = new CardLayout();
    private JPanel login_Panel = new JPanel(login_to_play);
    
    public PongView() {
        this.setTitle("Pong Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(login_to_play);
        this.setResizable(true);
        this.setVisible(true);
    }

}



