package Proyecto.games.New_Lemmings_game.View;

import Proyecto.games.utils.Drawable;
import Proyecto.games.utils.Screen;

import java.awt.*;

public class LevelAdder extends Screen {

    public LevelAdder(int width, int height) {
        super(width, height);
    }


    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }

    @Override
    public void update(double delta) {}



}
