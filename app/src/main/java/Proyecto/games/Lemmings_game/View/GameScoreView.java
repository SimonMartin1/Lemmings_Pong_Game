package Proyecto.games.Lemmings_game.View;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.entropyinteractive.Mouse;

import Proyecto.games.Lemmings_game.Lemmings;

public class GameScoreView {
    private final int width;
    private final int height;
    private final Lemmings game;
    public GameScoreView(int width, int height, Lemmings game) {
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.drawString("Game Score", width/2-50 , 70);
    }
}
