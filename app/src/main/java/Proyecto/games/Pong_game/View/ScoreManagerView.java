package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.ScoreManagerModel;

import java.awt.*;

public class ScoreManagerView {
    private final ScoreManagerModel scoreManagerModel;
    private int width;


    public ScoreManagerView(ScoreManagerModel scoreManagerModel,int width, int height){
        this.scoreManagerModel = scoreManagerModel;
        this.width=width;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(Integer.toString(scoreManagerModel.getPointsLeft()),width/2-60,60);
        g.drawString(Integer.toString(scoreManagerModel.getPointsRight()),width/2+60,60);
        g.setColor(Color.ORANGE);
        g.drawString(Integer.toString(scoreManagerModel.getMaxPoints()),width/2,60);
    }

        public void updateSize(int width){
        this.width=width;
    }
}
