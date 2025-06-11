package Proyecto.games.Lemmings_game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import Proyecto.games.Lemmings_game.Utils.ScoreDatabase;


public class GameScoreView {
    private final int width;
    private final int height;
    private ScoreDatabase db;
    public GameScoreView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g){
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 28));
    g.drawString("Game Score - Ranking", width/2-140 , 70);

    java.util.List<String[]> ranking = ScoreDatabase.getRanking();
    g.setFont(new Font("Arial", Font.PLAIN, 22));
    int y = 120;
    int pos = 1;
    for (String[] entry : ranking) {
        String line = pos + ". " + " Fecha: "+entry[0] + " - "+"Puntaje: " + entry[1];
        g.drawString(line, width/2-215, y);
        y += 35;
        pos++;
    }
    if (ranking.isEmpty()) {
        g.drawString("No scores yet.", width/2-80, y);
    }
}
}
