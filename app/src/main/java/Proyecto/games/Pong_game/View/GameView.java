package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Controller.PaddleController;
import Proyecto.games.Pong_game.Model.PaddleModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class GameView extends JFrame {


    public GameView() throws IOException {
        super("PongGame!");
        this.setSize(815, 635);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrar ventana


        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.setPreferredSize(new Dimension(800,600));

        BackgroundPanelView bPanel = new BackgroundPanelView();

        bPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(bPanel, JLayeredPane.DEFAULT_LAYER);

        BallView ballPanel = new BallView();
        ballPanel.setBounds(341, 240, 800, 600);
        layeredPane.add(ballPanel, JLayeredPane.PALETTE_LAYER);


        PaddleModel paddleLeftModel = new PaddleModel(200);
        PaddleView paddleLeftView = new PaddleView(paddleLeftModel, 0);
        paddleLeftView.setBounds(30,0,10,4000);
        layeredPane.add(paddleLeftView,JLayeredPane.PALETTE_LAYER);
        PaddleController paddleLeftController = new PaddleController(paddleLeftModel, paddleLeftView);
        this.addKeyListener(paddleLeftController);
        this.setFocusable(true);
        this.setResizable(false);


        //PaddleView paddle2 = new PaddleView();
        //paddle2.setBounds(700,200,800,600);
        //layeredPane.add(paddle2,JLayeredPane.PALETTE_LAYER);

        this.setContentPane(layeredPane);

        //this.setVisible(true);
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
