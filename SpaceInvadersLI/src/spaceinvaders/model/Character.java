package spaceinvaders.model;

import java.awt.Rectangle;

public abstract class Character {
    public int x, y;
    public int speed;
    protected boolean visible = true;

    public abstract Rectangle getBounds();

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}