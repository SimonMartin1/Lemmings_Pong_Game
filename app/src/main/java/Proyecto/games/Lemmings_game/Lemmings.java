package Proyecto.games.Lemmings_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.Timer;

import com.entropyinteractive.JGame;

import Proyecto.games.Lemmings_game.Model.FirstLevelMapModel;
import Proyecto.games.Lemmings_game.View.FirstLevelMapView;


public class Lemmings extends JGame {

    private FirstLevelMapModel firstLevelMapModel;
    private FirstLevelMapView firstLevelMapView;
    private boolean animation = false; 
    private double blinkTime = 0;
    private boolean showPressText = true;

    public Lemmings(String title, int width, int height) {
        super(title, width, height);
    }
    public static void main(String[] args) {
        Lemmings game = new Lemmings("Lemmings", 800, 600);
        game.run(1.0 / 60.0); // 60 FPS
    }


    @Override
    public void gameStartup() {
        
            try {
            firstLevelMapModel = new FirstLevelMapModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //vistas
        firstLevelMapView = new FirstLevelMapView(firstLevelMapModel);
        
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
        }
    }

    @Override
    public void gameDraw(Graphics2D g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));
            g.drawString("LEMMINGS", getWidth()/2 - 135, 150);
            
            g.setColor(new Color(34, 35, 37));
            g.fillRect(getWidth()/2 - 74, 315, 150, 40);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.drawString("START", getWidth()/2 - 50, 345);
            
            if (!animation && showPressText) {
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                g.drawString("Click or Enter", getWidth()/2 - 71, 400);
            }

            if (animation) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                firstLevelMapView.draw(g);
            }
    }


    @Override
    public void gameShutdown() {
        // Guardar datos, cerrar recursos
    }
    }