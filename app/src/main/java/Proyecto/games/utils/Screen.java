package Proyecto.games.utils;

import java.awt.*;

public abstract class Screen implements Drawable {
    protected int width,height;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta);
}
