package spaceinvaders.patterns;

import spaceinvaders.model.Alien;

public class AlienFactory {
    public Alien createAlien(String type, int x, int y) {
        if ("STANDARD".equalsIgnoreCase(type)) {
            return new Alien(x, y, 10);
        }
        return null;
    }
}