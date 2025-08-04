package Proyecto.games.utils;

import java.awt.*;

public abstract class Screen implements Drawable {
<<<<<<< HEAD
    protected int width,height;
=======
    protected int width;
    protected int height;
>>>>>>> 8db4588f9d1602f37fde3334e3f3fda5728fec42

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
