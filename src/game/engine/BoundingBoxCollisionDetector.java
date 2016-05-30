package game.engine;

import game.engine.entities.CollisionDetector;
import game.utils.math.BoundingBox2D;
import game.utils.math.Real2D;
import game.utils.math.Vector2D;
import game.utils.math.Vector3D;

/**
 * Created by NeaguR on 30.05.2016.
 * PROJECT pao_project
 */
public class BoundingBoxCollisionDetector implements CollisionDetector {


    private final Map map;

    public BoundingBoxCollisionDetector(Map map) {
        this.map = map;
    }

    public boolean collides(Vector3D centerA, Vector3D centerB) {
        return false;
    }

    public boolean collidesWithWorld(Vector2D playerPosition) {
        Real2D position = new Real2D(playerPosition.getX(), playerPosition.getY());
        final MapBindings singleton = MapBindings.getSingleton();

        BoundingBox2D playerBoundingBox2D = new BoundingBox2D(
                position.getRealX() + 0.05,
                position.getRealY() + 0.05,
                0.9,
                0.9);

        for (double x = -0.5; x <= 1; x += 0.5) {
            for (double y = -0.5; y <= 1; y += 0.5) {
                int realX = (int) (position.getRealX() + x);
                int realY = (int) (position.getRealY() + y);
                final byte element = map.getElement(realX, realY);
                if (singleton.doesCollideWithMapID(element)) {
                    BoundingBox2D boundingBox2D = new BoundingBox2D(realX, realY, 1, 1);
                    if (playerBoundingBox2D.collidesWith(boundingBox2D))
                        return true;
                }
            }
        }

        return false;
    }
}
