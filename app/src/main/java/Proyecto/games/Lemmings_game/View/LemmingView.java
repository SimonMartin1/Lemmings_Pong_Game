package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.*;

import java.awt.Graphics;
import java.awt.Color;


public class LemmingView{
    private LemmingModel model;

    public LemmingView(LemmingModel model) {
        this.model = model;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(model.getX(), model.getY(), 10, 10);
    }


}
