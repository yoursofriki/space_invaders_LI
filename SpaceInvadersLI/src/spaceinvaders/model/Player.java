package spaceinvaders.model;

import java.awt.Rectangle;

public class Player extends Character {
    public boolean moveLeft, moveRight;

    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}