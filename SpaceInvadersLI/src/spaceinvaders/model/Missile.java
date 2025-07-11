package spaceinvaders.model;

import java.awt.Rectangle;

public class Missile {
    private int x, y;
    private final int speed;
    private boolean visible;
    private final int MISSILE_WIDTH = 3;
    private final int MISSILE_HEIGHT = 7;

    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 10;
        this.visible = true;
    }

    public void move() {
        y -= speed;
        if (y < 0) {
            visible = false;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return MISSILE_WIDTH; }
    public int getHeight() { return MISSILE_HEIGHT; }
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public Rectangle getBounds() { return new Rectangle(x, y, getWidth(), getHeight()); }
}