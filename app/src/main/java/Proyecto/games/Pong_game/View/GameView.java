package Proyecto.games.Pong_game.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class GameView extends JFrame {

    public GameView() throws IOException {

        //config main frame
        super("PongGame!");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrar ventana

        //create a panel to put image backgrouhd

        BackgroundPanelView bPanel = new BackgroundPanelView();
        this.add(bPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                GameView view = new GameView();
                view.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
