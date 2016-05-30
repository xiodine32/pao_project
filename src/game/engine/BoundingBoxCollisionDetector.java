package game.engine;

import game.interfaces.CollisionDetector;
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

    public boolean collidesWithWorld(Vector2D playerPosition, Vector2D playerSize) {
        Real2D position = new Real2D(playerPosition.getX(), playerPosition.getY());
        final MapBindings singleton = MapBindings.getSingleton();

        BoundingBox2D playerBoundingBox2D = new BoundingBox2D(
                position.getRealX() + 0.05 + (1 - playerSize.getX()) / 2.0,
                position.getRealY() + 0.05 + (1 - playerSize.getY()) / 2.0,
                playerSize.getX() - 0.1,
                playerSize.getY() - 0.1);

        for (double x = -1; x <= 1; x += 0.5) {
            for (double y = -1; y <= 1; y += 0.5) {
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
