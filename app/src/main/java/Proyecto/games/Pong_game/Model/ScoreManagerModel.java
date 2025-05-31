package Proyecto.games.Pong_game.Model;

public class ScoreManagerModel {
    int pointsLeft = 0;
    int pointsRight =0;
    int maxPoints = 5;

    public ScoreManagerModel(int maxPoints){
        this.maxPoints = maxPoints;
    }

    public void refreshLeftPoints(){
        pointsLeft++;
    }

    public void refreshRightPoints(){
        pointsRight++;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }
    public int getPointsRight(){
        return pointsRight;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
