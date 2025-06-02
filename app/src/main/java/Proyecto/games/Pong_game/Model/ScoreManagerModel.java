package Proyecto.games.Pong_game.Model;

public class ScoreManagerModel {
    int pointsLeft = 0;
    int pointsRight =0;
    int maxPoints = 5;
    Player winner;

    public ScoreManagerModel(int maxPoints){
        this.maxPoints = maxPoints;
    }

    public void refreshLeftPoints(){
        pointsLeft++;

        if(pointsLeft == maxPoints) winner = Player.LEFT;
    }

    public void refreshRightPoints(){
        pointsRight++;

        if(pointsRight == maxPoints) winner = Player.RIGHT;
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

    public void reset(){
        this.pointsLeft = 0;
        this.pointsRight = 0;
        this.winner = null;
    }

    public boolean hasWinner(){ return winner != null; }

    public Player getWinner(){ return winner; }
}
