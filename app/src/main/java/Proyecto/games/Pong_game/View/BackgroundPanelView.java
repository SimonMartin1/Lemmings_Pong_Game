/*package Proyecto.games.Pong_game.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BackgroundPanelView extends JPanel {
    private Image backgroundImage;

    public BackgroundPanelView() throws IOException {
        this.backgroundImage = ImageIO.read(getClass().getResource("/cancha.png"));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
}
*/