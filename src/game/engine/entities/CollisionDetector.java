package game.engine.entities;

import game.utils.math.Vector2D;
import game.utils.math.Vector3D;

/**
 * Created by NeaguR on 30.05.2016.
 * PROJECT pao_project
 */
public interface CollisionDetector {
    boolean collides(Vector3D centerA, Vector3D centerB);

    boolean collidesWithWorld(Vector2D playerPosition);
}
