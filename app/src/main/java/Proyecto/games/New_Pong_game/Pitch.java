package Proyecto.games.New_Pong_game;

import Proyecto.games.New_Pong_game.utils.SkinPitch;

import javax.swing.*;
import java.awt.*;

public class Pitch implements Drawable{

    private Image pitchImage;
    private int width, height;


    public Pitch(int width, int height, SkinPitch skinPitch){
        this.width = width;
        this.height = height;

        switch (skinPitch){
            case DEFAULT -> this.pitchImage = new ImageIcon("app\\src\\main\\resources\\cancha1.png").getImage();
            case BASKET -> this.pitchImage = new ImageIcon("app\\src\\main\\resources\\cancha2.png").getImage();
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(pitchImage,0,0,width,height,null);
    }
}
