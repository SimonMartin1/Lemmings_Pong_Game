package Proyecto.Games.Lemmings_game.Model;


public class LemmingsModel {
    // Atributos del modelo
    private int lemmingCount;
    private int level;
    private int score;

    // Constructor
    public LemmingsModel() {
        this.lemmingCount = 0;
        this.level = 1;
        this.score = 0;
    }

    // Métodos para acceder y modificar los atributos
    public int getLemmingCount() {
        return lemmingCount;
    }

    public void setLemmingCount(int lemmingCount) {
        this.lemmingCount = lemmingCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
