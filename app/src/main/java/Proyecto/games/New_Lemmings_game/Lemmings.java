// Refactor de Lemmings.java
// Elimina los modelos, vistas y controladores de nivel, pero mantiene GameMenuView, GamePauseView, etc.

package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.Levels.LoadFromFiles;
import Proyecto.games.New_Lemmings_game.View.*;
import Proyecto.games.New_Lemmings_game.View.Menu;
import Proyecto.games.New_Lemmings_game.utils.ConfigLemmings;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.SoundManager;
import Proyecto.games.utils.SoundPlayer;
import Proyecto.games.New_Lemmings_game.utils.ScoreDatabase;
import com.entropyinteractive.JGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Lemmings extends JGame {
    private Menu menu;
    private Pause pause;
    private Settings settings;
    private Score score;
    private Win win;
    private final List<Level> levels = new ArrayList<>();
    private int currentLevel = 0;
    private Cursor cursor;
    private GameState gameState= GameState.ON_MENU;
    private int screenWidth = 800;
    private int screenHeight = 600;
    private int pointsSum=0;
    private ConfigLemmings configLemmings;
    private SoundManager soundManager;
    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings",800,600);
        game.run(1.0 / 60.0);
    }
    
    @Override
    public void gameStartup() {
        configLemmings = new ConfigLemmings();
        soundManager=new SoundManager(configLemmings.isMusicOff());

        if(!getConfig().isMusicOff()){
            soundManager.playMusic("app/src/main/resources/soundEffects/cantinadelpela.wav", true);
        }

        getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Lemmings.super.shutdown();
            }
        });

        ScoreDatabase.createTable();

        try {
            loadLevels();
            Stock stock = levels.get(currentLevel).getStock();
            cursor = new Cursor(stock, getMouse(), screenWidth, screenHeight, false);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        this.menu = new Menu(screenWidth, screenHeight, this);
        this.pause = new Pause(screenWidth, screenHeight,this);
        this.settings = new Settings(screenWidth, screenHeight, this);
        this.score = new Score(screenWidth, screenHeight, this);
        this.win = new Win(screenWidth, screenHeight,this);
    }

    @Override
    public void gameUpdate(double delta) {

        switch (gameState){

            case PRE_MENU -> {
                if(!soundManager.isMuted()){
                    soundManager.playMusic("app/src/main/resources/soundEffects/cantinadelpela.wav", true);
                }
                setGameState(GameState.ON_MENU);
            }

            case ON_MENU -> menu.update(delta);

            case ON_CONFIG -> settings.update(delta);

            case ON_SCORE -> score.update(delta);

            case PRE_LEVEL -> startGameLevel();

            case PLAYING -> {
                Level current = levels.get(currentLevel);
       
                cursor.setCurrentLemmings(current.getLemmings()); // Esto es clave
                cursor.setStock(levels.get(currentLevel).getStock());

                cursor.setCamX(current.getCamX()); // si tenés cámara que se mueve
                current.update(delta);
                cursor.update(); // <-- actualizás el cursor con el mouse
                levels.get(currentLevel).getMinimap().handleClick(getMouse().getX(),getMouse().getY());

                updatelevelScreenHandler();
            }

            case ON_PAUSE -> pause.update(delta);

            case LEVEL_END -> updatelevelScreenHandler();

            case ENDGAME -> win.update(delta);
        }


    }

    @Override
    public void gameDraw(Graphics2D g) {

        switch (gameState){
            case PRE_MENU,ON_MENU -> menu.draw(g);

            case ON_SCORE -> score.draw(g);

            case ON_CONFIG -> settings.draw(g);

            case PRE_LEVEL ->  levels.get(currentLevel).drawPreLevelScreen(g);

            case PLAYING -> {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                levels.get(currentLevel).drawLevel(g,800,600);
            }

            case ON_PAUSE -> pause.draw(g);

            case LEVEL_END -> levels.get(currentLevel).drawWonScreen(g);

            case ENDGAME -> win.draw(g);

        }
    }

    public void updatelevelScreenHandler(){

        switch (gameState){

            case PLAYING -> {
                if (levels.get(currentLevel).isLevelFinished()) gameState = GameState.LEVEL_END;
                else if (getKeyboard().isKeyPressed(KeyEvent.VK_P)) gameState = GameState.ON_PAUSE;
            }

            case LEVEL_END -> {

                if (getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)){
                    if(levels.get(currentLevel).isLevelWon()){
                        nextLevel();
                    } else{
                        startGameLevel();
                    }
                }
                else if(!levels.get(currentLevel).isLevelWon() && getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE)){
                    gameState = GameState.ON_MENU;
                    resetLevel(currentLevel);
                }

            }

        }
    }

    @Override
    public void gameShutdown() {
        soundManager.stopMusic();
    }

    // Lógica de niveles

    private void nextLevel() {
        if (currentLevel < levels.size() - 1) {
            currentLevel++;
            startGameLevel();
        }
        else{
            //Si termino el juego guardo el puntaje
            for (Level l : levels) pointsSum += l.getLevelScore();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ScoreDatabase.saveScore(timestamp, pointsSum);
            setGameState(GameState.ENDGAME);
        }
    }

    private void loadLevels() throws IOException {
        LoadFromFiles loadFromFiles = new LoadFromFiles();
        File folder = new File("app/src/main/java/Proyecto/games/New_Lemmings_game/Levels");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                Level level = loadFromFiles.loadLevelFromFile(file.getPath());
                level.setLemmingSkin(configLemmings.getLemmingSkin());
                levels.add(level);
            }
        } else {
            System.out.println("No se encontraron archivos en la carpeta de niveles.");
        }
    }

    public void startGameLevel(){
        resetLevel(currentLevel);
        gameState = GameState.PLAYING;
    }

    public void resetLevel(int index) {
        if (index >= 0 && index < levels.size()) {
            LoadFromFiles loadFromFiles = new LoadFromFiles();
            File folder = new File("app/src/main/java/Proyecto/games/New_Lemmings_game/Levels");
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

            if (files != null && files.length > index) {

                Level level;

                try{
                    level = loadFromFiles.loadLevelFromFile(files[index].getPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                level.setLemmingSkin(configLemmings.getLemmingSkin());
                levels.set(index, level); // Reemplaza el viejo level con uno nuevo
            } else {
                System.out.println("No se encontró el archivo del nivel para reiniciar.");
            }
        }
    }



    // Getters & Setter

    public ConfigLemmings getConfig(){return configLemmings;}

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }


    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public List<Level> getLevel(){
        return levels;
    }

    public int getScore(){return pointsSum;}

    public SoundManager getSoundManager() {
        return soundManager;
    }


}
