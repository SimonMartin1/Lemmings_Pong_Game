package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.utils.Drawable;
import Proyecto.games.utils.Screen;

import java.awt.*;

public class Win extends Lemmings_Screens {

    // Botón "Volver al menú"
    

    public Win(int width, int height, Lemmings game) {
        super(width,height,game);
    }
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("You Win!", width / 2 - 90, height / 2 - 60);

    }

    @Override
    public void update(double delta) {

    }
}