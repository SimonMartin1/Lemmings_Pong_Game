package Proyecto.games.Lemmings_game.Model;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TileModel {
    private BufferedImage image;
    private int tipo; // o "id", o "valor logico"
    private Color color;
    
    public TileModel(BufferedImage image) {
        this.image = image;
        this.tipo = 0; // Por defecto, vacío
        setColorPixelImage(4, 4);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setColorPixelImage(int x, int y) {

        if (x >= 0 && x < this.image.getWidth() && y >= 0 && y < this.image.getHeight()) {
            int pixelRGB = this.image.getRGB(x, y);
            this.color = new Color(pixelRGB, true);
        }

    }
    public Color getColor() {
        return color;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        if(this.color.equals(Color.BLACK)){
            this.tipo = 1;
        }else {
            this.tipo = 0;
        }
    }

    public void setTileColor(Color color) {
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/black.png"));
        }catch (Exception err){
            System.out.println("xd");
        }

        this.color = color;

        if(Color.BLACK.equals(this.color)){
            this.tipo = 0; // vaciado
        }
        else{
            this.tipo = 1;
        }
    }

}
