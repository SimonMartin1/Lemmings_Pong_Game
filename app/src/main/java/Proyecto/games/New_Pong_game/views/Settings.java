package Proyecto.games.New_Pong_game.views;

import Proyecto.games.New_Pong_game.Pong;
import com.entropyinteractive.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la vista y la interacción de la pantalla de configuración del juego.
 * Esta versión está refactorizada para mejorar la mantenibilidad y legibilidad
 * utilizando Enums para el estado y Rectangles para el layout de la UI.
 */
public class Settings {
    private final Pong game;
    private int width;
    private int height;

    private final ArrayList skinsPitchNames = new ArrayList(List.of("DEFAULT", "BASKET"));
    private final ArrayList skinsBallNames = new ArrayList(List.of("DEFAULT", "CRAZY"));

    public Settings(int width, int height, Pong game) {
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2 -140, 145, 400, 40, 20, 20);
        g.drawRoundRect(width/2-145, 325, 95, 35, 20, 20);
        g.drawRoundRect(width/2-145, 280, 95, 35, 20, 20);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width/2-50 , 70);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString("1 Player", width/2-265 , 170);
        g.setColor(Color.WHITE);

        g.drawString("Difficulty", width/2-120 , 170);
        g.drawString("Hard", width/2 , 170);
        g.drawString("Medium", width/2+90 , 170);
        g.drawString("Easy", width/2+200 , 170);

        g.drawString("2 Players", width/2-265 , 215);
        g.drawString("On", width/2-120 , 215);

        g.drawString("WinPoints", width/2-265 , 260);
        g.drawString("15", width/2-120 , 260);
        g.drawString("10", width/2-60 , 260);
        g.drawString("5", width/2 , 260);

        g.drawString("Pitch Skin", width/2-265 , 305);
        g.drawString((String) skinsPitchNames.get(game.getConfig().getSkinPitch().ordinal()), width/2-125 , 305);

        g.drawString("Ball Skin", width/2-265 , 350);
        g.drawString((String) skinsBallNames.get(game.getConfig().getSkinBall().ordinal()), width/2-125 , 350);

        g.drawString("Full Screen", width/2-265 , 395);
        g.drawString("On", width/2-120 , 395);
        g.drawString("Off", width/2-40 , 395);

        g.drawString("Keys", width/2-265 , 440);
        g.drawString("Player1 Up: "+ KeyEvent.getKeyText(game.getConfig().getPlayerOneUp()) +"  Down: "+ KeyEvent.getKeyText(game.getConfig().getPlayerOneDown()), width/2-200 , 440);
        g.drawString("Change Keys", width/2+100 , 440);

        // OPTIONS
        g.drawString("Save", width-325 , height-65);
        g.drawString("Cancel", width-245 , height-65);
        g.drawString("Reset", width-145 , height-65);
    }


    public void update(double delta, Pong game){
        // Detectar eventos y cambiar las config del game correspondientes...
    }

}