package Proyecto.games.Pong_game.View;

import Proyecto.games.Pong_game.Model.ScoreManagerModel;

import java.awt.*;

public class ScoreManagerView {
    private final ScoreManagerModel scoreManagerModel;
    private final int posXLeftScore = 300;
    private final int posYLeftScore = 60;
    private final int posXRightScore = 500;
    private final int posYRightScore = 60;


    public ScoreManagerView(ScoreManagerModel scoreManagerModel){
        this.scoreManagerModel = scoreManagerModel;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(Integer.toString(scoreManagerModel.getPointsLeft()),posXLeftScore,posYLeftScore);
        g.drawString(Integer.toString(scoreManagerModel.getPointsRight()),posXRightScore,posYRightScore);
        g.setColor(Color.ORANGE);
        g.drawString(Integer.toString(scoreManagerModel.getMaxPoints()),400,posYRightScore);
    }
}
