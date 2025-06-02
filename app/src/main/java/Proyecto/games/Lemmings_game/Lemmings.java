package Proyecto.games.Lemmings_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import com.entropyinteractive.JGame;

import Proyecto.games.Lemmings_game.Model.FirstLevelMapModel;
import Proyecto.games.Lemmings_game.View.FirstLevelMapView;
import Proyecto.games.Lemmings_game.Model.LemmingModel;
import Proyecto.games.Lemmings_game.View.LemmingView;

public class Lemmings extends JGame {

    private FirstLevelMapModel firstLevelMapModel;
    private FirstLevelMapView firstLevelMapView;
    private boolean animation = false; 
    private double blinkTime = 0;
    private boolean showPressText = true;
    private LemmingModel lemmingModel;
    private LemmingView lemmingView;

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }


    @Override
    public void gameStartup() {
        //modelos
        try {
            firstLevelMapModel = new FirstLevelMapModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        lemmingModel = new LemmingModel(225, 100, 1, 1);

        //vistas
        ImageIcon icon = new ImageIcon("app/src/main/resources/images/Lemmings_icon.png"); 
        this.getFrame().setIconImage(icon.getImage());
        lemmingView = new LemmingView(lemmingModel);
        firstLevelMapView = new FirstLevelMapView(firstLevelMapModel);
        lemmingView = new LemmingView(lemmingModel);
    }

    @Override
    public void gameUpdate(double delta) {
        if (!animation) {
            
            if (getMouse().isLeftButtonPressed()) {
                int mx = getMouse().getX();
                int my = getMouse().getY();
                int bx = getWidth()/2 - 100, by = 300, bw = 200, bh = 60;
                if (mx >= bx && mx <= bx + bw && my >= by && my <= by + bh) {
                    animation = true;
                }
            }
            if (getKeyboard().isKeyPressed(10)) {
                animation = true;
            }
            
            blinkTime += delta;
            if (blinkTime >= 0.6) { 
                showPressText = !showPressText;
                blinkTime = 0;
            }
        }else{
            lemmingModel.update(delta);
        }

    }

    @Override
    public void gameDraw(Graphics2D g) {
            Image background = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_back.png").getImage();
            g.drawImage(background, 0, 0, getWidth(), getHeight(),null);
            
            Image lemmings = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_title.png").getImage();
            g.drawImage(lemmings,getWidth()/2-290 , 125, getWidth()/2+200, 160,null);

            Image lemmings_button = new ImageIcon("app\\src\\main\\resources\\images\\Lemmings_button.png").getImage();
            g.drawImage(lemmings_button,getWidth()/2-55 , 275, 120, 120,null);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("Settings", getWidth()-250 , 500);


            if (!animation && showPressText) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Click or Enter", getWidth()/2 - 71, 420);
            }

            if (animation) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                firstLevelMapView.draw(g, 430, 0);
                lemmingView.draw(g);
            }

    }

    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }
    }