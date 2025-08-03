package Proyecto.games.New_Lemmings_game.View;


import java.awt.*;
import java.awt.event.KeyEvent;

import Proyecto.games.New_Lemmings_game.Lemmings;
import Proyecto.games.New_Lemmings_game.utils.Lemmings_Screens;
import Proyecto.games.New_Lemmings_game.utils.ScoreDatabase;
import Proyecto.games.utils.Drawable;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.Screen;
import com.entropyinteractive.Mouse;

public class Score extends Lemmings_Screens {
    private Lemmings game;
    private boolean prevMousePressed;
    public Score(int width, int height, Lemmings game) {
        super(width,height,game);
        this.game = game;
    }
    @Override
    public void draw(Graphics2D g){
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 28));
    g.drawString("Game Score - Ranking", width/2-140 , 70);
    g.drawString("Back", width-250 , height-60);

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

    @Override
    public void update(double delta) {
        if(detectScore() || game.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
            game.setGameState(GameState.ON_MENU);
        }
    }

    protected boolean isMouseOverClickArea(int x, int y, int width, int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && game.getMouse().isLeftButtonPressed();
    }

    public boolean detectScore(){
        return isMouseOverClickArea(width - 250, height - 110, 150, 80);
    }
}
