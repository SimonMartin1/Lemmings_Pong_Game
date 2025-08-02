package Proyecto.games.utils;

import java.awt.*;

public abstract class Screen implements Drawable {
    int width;
    int height;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta);
}
