package spaceinvaders.patterns;

import spaceinvaders.model.Alien;
import java.util.List;

public interface MovementStrategy {
    void move(List<Alien> aliens, int boardWidth);
}
