package spaceinvaders.patterns;

import spaceinvaders.model.Alien;
import java.util.List;

public class StandardMovement implements MovementStrategy {

    private final int dropSpeed = 15;

    @Override
    public void move(List<Alien> aliens, int boardWidth) {
        boolean changeDirection = false;

        for (Alien alien : aliens) {
            if (alien.x > boardWidth - 30 || alien.x < 0) {
                changeDirection = true;
                break; // No es necesario seguir comprobando.
            }
        }

        if (changeDirection) {
            for (Alien alien : aliens) {
                alien.y += dropSpeed;
                alien.moveLeft = !alien.moveLeft;
                alien.moveRight = !alien.moveRight;
            }
        }

        for (Alien alien : aliens) {
            if (alien.moveLeft) {
                alien.x -= 1;
            }
            if (alien.moveRight) {
                alien.x += 1;
            }
        }
    }
}