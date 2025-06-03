package Proyecto.games.Lemmings_game.View;

import Proyecto.games.Lemmings_game.Model.LemmingModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream; 
import java.util.ArrayList;
import java.util.List;


public class LemmingView{
    //private static final int FRAME_WIDTH = 16;  // Ancho de un fotograma individual
    //private static final int FRAME_HEIGHT = 16; // Alto de un fotograma individual
    //private static final int FRAME_COUNT = 4; // pone la cantidad de frames que tenés
    //private int currentFrame = 0;
    //private long lastFrameTime = 0;
    private LemmingModel model;
    //private BufferedImage spriteSheet;  // Imagen que contiene todos los frames
    //private BufferedImage subImage;

    public LemmingView(LemmingModel model) {
        this.model = model;
    
    }
    /* 
    public void loadSpriteSheet() {
        try {
            // Carga el spritesheet desde la carpeta de recursos
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/lemming.png"));

            //BufferedImage subImage = spriteSheet.getSubimage(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        } catch (IOException e) {
        }
    }*/

    public void draw(Graphics g) {
        //int frameIndex = 1; // Cambiá esto para elegir el frame que quieras (0, 1, 2, ...)

        // Calculamos la posición del frame en el sprite sheet
        //int sx = 0;
        //int sy = 0; // si están todos en una fila, Y es siempre 0
        //BufferedImage subImage = spriteSheet.getSubimage(sx, sy, 51, 20);

        //g.drawImage(spriteSheet, model.getX(), model.getY(), null);
        g.setColor(Color.RED);
        //g.drawImage(walkingFrames.get(0), model.getX(), model.getY(), null);
        g.fillRect(model.getX(), model.getY(), 10, 10);
    }
    

}
