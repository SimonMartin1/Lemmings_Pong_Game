// Refactor de Lemmings.java
// Elimina los modelos, vistas y controladores de nivel, pero mantiene GameMenuView, GamePauseView, etc.

package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.Levels.LoadFromFiles;
import Proyecto.games.New_Lemmings_game.View.*;
import Proyecto.games.New_Lemmings_game.View.Menu;
import Proyecto.games.New_Lemmings_game.utils.*;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.SoundPlayer;
import Proyecto.games.New_Lemmings_game.utils.ScoreDatabase;
import com.entropyinteractive.JGame;
import com.entropyinteractive.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lemmings extends JGame {
    private Menu menu;
    private Pause pause;
    private Settings settings;
    private Score score;
    private Win win;
    private Level_Won level_won;
    private Level_Fail level_fail;
    private Stock stock;
    private LevelManager levelManager;
    private LoadFromFiles loadFromFiles;
    private List<Level> levels = new ArrayList<>();
    private int currentLevel = 0;
    private Spawn spawn;
    private Exit exit;
    private Cursor cursor;

    private static boolean fullScreen = false;
    private GameState gameState;

    private int screenWidth;
    private int screenHeight;
    private boolean prevPausePressed = false;

    private int pointsSum = 0;

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings",800,600);
        game.run(1.0 / 60.0);
    }
    
    @Override
    public void gameStartup() {
        getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                SoundPlayer.stopSound();
            }
        });

        ScoreDatabase.createTable();
        ScoreDatabase.showRanking();

        if (fullScreen) setFullScreen();

        
        stock = new Stock(
            new HashMap<>(Map.of(
                Ability.DIGGER, 5,
                Ability.CLIMB, 0,
                Ability.STOP, 3,
                Ability.UMBRELLA, 0
            ))
        );
        
        try {
            loadLevels();
            cursor = new Cursor(stock, getMouse(), screenWidth, screenHeight, fullScreen);

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        screenWidth = getWidth();
        screenHeight = getHeight();
        gameState = GameState.ON_MENU;
        menu = new Menu(screenWidth, screenHeight, this);
        pause = new Pause(screenWidth, screenHeight);
        settings = new Settings(screenWidth, screenHeight, this);
        score = new Score(screenWidth, screenHeight, this);
        win = new Win(screenWidth, screenHeight);
        level_fail = new Level_Fail(screenWidth,screenHeight);
        level_won = new Level_Won(screenWidth,screenHeight);
        levelManager = new LevelManager();
    }

    @Override
    public void gameUpdate(double delta) {

        switch (gameState){
            case ON_MENU -> {
                menu.update(delta);
            }

            case ON_EDITOR -> {}

            case ON_CONFIG -> {
               settings.update(delta);
            }

            case ON_SCORE -> {
                score.update(delta);
            }

            case PLAYING -> {
                if (getKeyboard().isKeyPressed(KeyEvent.VK_P)) {
                    setGameState(GameState.ON_PAUSE);
                }

                Level current = levels.get(currentLevel);

                cursor.setCurrentLemmings(current.getLemmings()); // Esto es clave
                cursor.setCamX(current.getCamX()); // si tenés cámara que se mueve
                current.update(delta);
                cursor.update(); // <-- actualizás el cursor con el mouse

                if (current.isLevelFinished()) {
                    if (current.isLevelWon()){
                        setGameState(GameState.LEVEL_WON);
                    }
                    else{
                        setGameState(GameState.LEVEL_FAIL);
                    }

                }

            }

            case ON_PAUSE -> {
                pause.update(delta);
            }

            case LEVEL_WON -> {
                level_won.update(delta);
            }

            case LEVEL_FAIL -> {
                level_fail.update(delta);
            }
            case FINISH -> {
                win.update(delta);
//                //Si termino el juego guardo el puntaje
//                for (Level l : levels) pointsSum += l.getLevelScore();
//                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                ScoreDatabase.saveScore(timestamp, pointsSum);
            }
        }


    }

    @Override
    public void gameDraw(Graphics2D g) {

        switch (gameState){
            case ON_MENU -> menu.draw(g);

            case ON_SCORE -> score.draw(g);

            case ON_CONFIG -> settings.draw(g);

            case PLAYING -> {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                levels.get(currentLevel).drawPreLevelScreen(g);
                levels.get(currentLevel).drawLevel(g,800,600);
            }

            case ON_PAUSE -> pause.draw(g);

            case LEVEL_WON -> level_won.draw(g);

            case LEVEL_FAIL -> level_fail.draw(g);

            case FINISH -> win.draw(g);

        }
    }

    private void nextLevel() {
        if (currentLevel < levels.size() - 1) {
            currentLevel++;
        }
        else{
            setGameState(GameState.FINISH);
        }
    }

    private void restartLevel() {
        //levels.set(currentLevel, levels.get(currentLevel).clone());
    }

    private void setFullScreen() {
        JFrame frame = getFrame();
        frame.dispose();
        frame.setUndecorated(true);
        frame.setResizable(false);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);
    }

    private void loadLevels() throws IOException {
        loadFromFiles = new LoadFromFiles();
        File folder = new File("app/src/main/java/Proyecto/games/New_Lemmings_game/Levels");
        //File folder = new File("C:/Users/Laureano/Lemmings_Pong_Game/src/Proyecto/games/New_Lemmings_game/Levels");


        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                Level level = loadFromFiles.loadLevelFromFile(file.getPath());
                levels.add(level);
            }
        } else {
            System.out.println("No se encontraron archivos en la carpeta de niveles.");
        }
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }




    public boolean detectSetting(Mouse m) {
        return mouseTracker(screenWidth - 250, screenHeight - 110, 150, 80, m);
    }

    public boolean detectScore(Mouse m) {
        return mouseTracker(250, screenHeight - 110, 150, 80, m);
    }

    public boolean mouseTracker(int x, int y, int width, int height, Mouse m) {
        int mx = m.getX();
        int my = m.getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && m.isLeftButtonPressed();
    }

    @Override
    public void gameShutdown() {
    }

    public GameState getGameState() {
        return  gameState;
    }

}
