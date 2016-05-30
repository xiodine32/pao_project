package game.interfaces;

import game.utils.math.Vector2D;

/**
 * Created by NeaguR on 30.05.2016.
 * PROJECT pao_project
 */
public interface CollisionDetector {
    boolean collidesWithBullet(Vector2D playerPosition, Vector2D playerSize, Vector2D bulletPosition, Vector2D bulletSize);

    boolean collidesWithWorld(Vector2D playerPosition, Vector2D playerSize);
}
