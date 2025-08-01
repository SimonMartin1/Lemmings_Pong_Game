// Refactor de Lemmings.java
// Elimina los modelos, vistas y controladores de nivel, pero mantiene GameMenuView, GamePauseView, etc.

package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.View.*;
import Proyecto.games.New_Lemmings_game.View.Menu;
import Proyecto.games.New_Lemmings_game.utils.ConfigLemmings;
import Proyecto.games.New_Lemmings_game.utils.*;
import Proyecto.games.utils.GameState;
import Proyecto.games.utils.SoundPlayer;
import Proyecto.games.New_Lemmings_game.utils.ScoreDatabase;
import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lemmings extends JGame {
    private Menu gameMenu;
    private Pause gamePauseView;
    private Settings gameSettingsView;
    private Score gameScoreView;
    private Win gameWinView;
    private Stock stock;
    private List<Level> levels = new ArrayList<>();
    private int currentLevel = 0;
    private Spawn spawn;
    private Exit exit;
    private Cursor cursor;

    private ConfigLemmings.Settings settings, backupSettings;
    private static boolean fullScreen = false;
    private boolean isInMenu = true, isInSettings = false, isInScore = false, gamePaused = false, gameWin = false, musicOff = true;
    private GameState gameState = GameState.ON_MENU;

    private int screenWidth = getWidth();
    private int screenHeight = getHeight();
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
        initSettings();
        backupSettings();
        playTrack();

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

        gameMenu = new Menu(getWidth(), getHeight(), this);
        gamePauseView = new Pause(screenWidth, screenHeight);
        gameSettingsView = new Settings(screenWidth, screenHeight, this);
        gameScoreView = new Score(screenWidth, screenHeight, this);
        gameWinView = new Win(screenWidth, screenHeight);
    }

    private void initSettings() {
        settings = ConfigLemmings.loadSettings();
        musicOff = settings.musicOff;
        fullScreen = settings.fullScreen;
    }

    private void backupSettings() {
        backupSettings = new ConfigLemmings.Settings();
        backupSettings.fullScreen = fullScreen;
        backupSettings.musicOff = musicOff;
    }

    private void playTrack() {
        if (!musicOff) SoundPlayer.playSound("app/src/main/resources/cantinadelpela.wav");
    }

    @Override
    public void gameUpdate(double delta) {
        if (isInSettings || isInScore) return;

        if (isInMenu) {
            //System.out.println("entre al menu");
            gameMenu.update(delta);
            if (detectPlay(getMouse()) || detectPlay(getKeyboard())) isInMenu = false;
            if (detectSetting(getMouse()) || getKeyboard().isKeyPressed(KeyEvent.VK_C)) isInSettings = !isInSettings;
            if (detectScore(getMouse()) || getKeyboard().isKeyPressed(KeyEvent.VK_S)) isInScore = !isInScore;
        } else {
            if (getKeyboard().isKeyPressed(KeyEvent.VK_P)) {
                gamePaused = true;
                if (getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)) isInMenu = true;
            }
            if (gameWin && getKeyboard().isKeyPressed(KeyEvent.VK_ENTER)) isInMenu = true;
            if (gamePaused && getKeyboard().isKeyPressed(KeyEvent.VK_M)) gamePaused = false;

            if (!gamePaused) {
                Level current = levels.get(currentLevel);
                //current.update(delta);

                cursor.setCurrentLemmings(current.getLemmings()); // Esto es clave
                cursor.setCamX(current.getCamX()); // si tenés cámara que se mueve
                current.update(delta);
                cursor.update(); // <-- actualizás el cursor con el mouse
                if (current.isLevelFinished()) {
                    if (current.isLevelWon()) nextLevel();
                    //else restartLevel();
                }
            }
        }
    }

    @Override
    public void gameDraw(Graphics2D g) {
        if (isInMenu) {
            gameMenu.drawmenu(g);
            if (isInSettings) gameSettingsView.drawmenu(g);
            if (isInScore) gameScoreView.draw(g);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            levels.get(currentLevel).drawPreLevelScreen(g);
            levels.get(currentLevel).drawLevel(g,800,600);
            if (gamePaused) gamePauseView.draw(g);
            if (gameWin) gameWinView.draw(g);
        }
    }

    private void nextLevel() {
        if (currentLevel < levels.size() - 1) {
            currentLevel++;
        } else {
            /* //Aca va la BD
            for (Level l : levels) pointsSum += l.getPoints();
            gameWin = true;
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ScoreDatabase.saveScore(timestamp, pointsSum);*/
        }
    }

    /*
    
    private void restartLevel() {
        System.out.println("Reiniciando nivel actual");
        levels.set(currentLevel, levels.get(currentLevel).clone());
    }*/

    private void setFullScreen() {
        JFrame frame = getFrame();
        frame.dispose();
        frame.setUndecorated(true);
        frame.setResizable(false);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);
    }
    
    private void loadLevels() throws IOException {
        // Crear spawn y salida
        spawn = new Spawn(600, 100,300); // Coordenadas X, Y
        exit = new Exit(1000, 300,300);   // Coordenadas X, Y


        // Crear el mapa (dependiendo de tu clase)

        // Borrar los atributos exitX y exitY NO OLVIDARRR

        Game_Map map = new Game_Map(1, 300, null, 700, 100, spawn ,exit); // o lo que corresponda
        levels.add(new Level(map, stock, 4, 10, currentLevel, "Just digging", exit, 600, 110));
        //levels.add(new Level(map, stock, screenWidth, screenHeight, currentLevel, getTitle(), exit, pointsSum, currentLevel));
        //levels.add(new Level(1, "Just digging", 0.25, 3, screenWidth, screenHeight));
        //levels.add(new Level(2, "Cap 2", 1.0, 3, screenWidth, screenHeight));
        //levels.add(new Level(3, "Cap 3", 0.8, 5, screenWidth, screenHeight));
    }

    public boolean detectPlay(Mouse m) {
        return mouseTracker(screenWidth / 2 - 100, 300, 200, 60, m) && !isInSettings;
    }

    public boolean detectPlay(Keyboard k) {
        return k.isKeyPressed(10) && !isInSettings && !isInScore;
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
        ConfigLemmings.saveSettings(musicOff, fullScreen);
    }

    public boolean getIsinMenu() {
        return isInMenu; 
    }

    public boolean getIsinScore() {
        return isInScore; 
    }

    public boolean getIsinsettings() {
        return isInSettings;
    }
}
