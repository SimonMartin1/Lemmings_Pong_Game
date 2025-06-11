package Proyecto.games.Pong_game.View;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PitchView {
    private final Image pitch1,pitch2,pitch3;
    public PitchView(){
        pitch1 = new ImageIcon("app\\src\\main\\resources\\cancha1.png").getImage();
        pitch2 = new ImageIcon("app\\src\\main\\resources\\cancha2.png").getImage();
        pitch3 = new ImageIcon("app\\src\\main\\resources\\cancha3.png").getImage();
    }
    public void draw(Graphics2D g, int width, int height,PitchSkins option){
            Image res;
        switch(option){
            default  -> res=pitch1;
            case BLACK -> res=pitch1;
            case BLUE -> res=pitch2;
            case BASKET -> res=pitch3;

        }
        g.drawImage(res,0,0,width,height,null);
    }
}
