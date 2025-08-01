package Proyecto.games.New_Pong_game.utils;

public class ConfigPong {
    // Dinamicas
    private boolean musicOff;
    private boolean isFullscreen;

    private int playerOneUp;
    private int playerOneDown;

    private int playerTwoUp;
    private int playerTwoDown;


    // Estaticas
    private boolean isVersusIA;
    private int maxPoints;
    private Difficult difficult;
    private BallSkin ballSkin;
    private PitchSkin pitchSkin;
    private Track track;

    // Lo demás

    public ConfigPong(Difficult difficult, int maxPoints, boolean isVersusIA, int playerTwoDown, int playerTwoUp, int playerOneDown, int playerOneUp, boolean isFullscreen, boolean musicOff, Track track, PitchSkin pitchSkin, BallSkin ballSkin) {
        this.difficult = difficult;
        this.maxPoints = maxPoints;
        this.isVersusIA = isVersusIA;
        this.playerTwoDown = playerTwoDown;
        this.playerTwoUp = playerTwoUp;
        this.playerOneDown = playerOneDown;
        this.playerOneUp = playerOneUp;
        this.isFullscreen = isFullscreen;
        this.musicOff = musicOff;
        this.track = track;
        this.pitchSkin = pitchSkin;
        this.ballSkin = ballSkin;
    }

    public boolean isMusicOff() {
        return musicOff;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public void setMusicOff(boolean musicOff) {
        this.musicOff = musicOff;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        isFullscreen = fullscreen;
    }

    public int getPlayerOneUp() {
        return playerOneUp;
    }

    public void setPlayerOneUp(int playerOneUp) {
        this.playerOneUp = playerOneUp;
    }

    public int getPlayerOneDown() {
        return playerOneDown;
    }

    public void setPlayerOneDown(int playerOneDown) {
        this.playerOneDown = playerOneDown;
    }

    public int getPlayerTwoUp() {
        return playerTwoUp;
    }

    public void setPlayerTwoUp(int playerTwoUp) {
        this.playerTwoUp = playerTwoUp;
    }

    public int getPlayerTwoDown() {
        return playerTwoDown;
    }

    public void setPlayerTwoDown(int playerTwoDown) {
        this.playerTwoDown = playerTwoDown;
    }

    public boolean isVersusIA() {
        return isVersusIA;
    }

    public void setVersusIA(boolean versusIA) {
        isVersusIA = versusIA;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public void setDifficult(Difficult difficult) {
        this.difficult = difficult;
    }

    public BallSkin getBallSkin() {
        return ballSkin;
    }

    public void setBallSkin(BallSkin ballSkin) {
        this.ballSkin = ballSkin;
    }

    public PitchSkin getPitchSkin() {
        return pitchSkin;
    }

    public void setPitchSkin(PitchSkin pitchSkin) {
        this.pitchSkin = pitchSkin;
    }
}
