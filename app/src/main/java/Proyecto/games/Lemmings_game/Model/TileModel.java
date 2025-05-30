package Proyecto.games.Lemmings_game.Model;
import java.awt.image.BufferedImage;

public class TileModel {
    private BufferedImage image;

    public TileModel(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

}
