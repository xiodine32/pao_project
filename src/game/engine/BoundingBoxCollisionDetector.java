package game.engine;

import game.interfaces.CollisionDetector;
import game.utils.math.BoundingBox2D;
import game.utils.math.Real2D;
import game.utils.math.Vector2D;

/**
 * Created by NeaguR on 30.05.2016.
 * PROJECT pao_project
 */
public class BoundingBoxCollisionDetector implements CollisionDetector {


    private final Map map;

    public BoundingBoxCollisionDetector(Map map) {
        this.map = map;
    }

    public boolean collidesWithBullet(Vector2D playerPosition, Vector2D playerSize, Vector2D bulletPosition, Vector2D bulletSize) {
        BoundingBox2D playerBoundingBox2D = getBoundingBox2D(playerSize, playerPosition);
        Real2D positionBullet = new Real2D(bulletPosition.getX(), bulletPosition.getY());
        BoundingBox2D boundingBox2D = new BoundingBox2D(positionBullet.getX(), positionBullet.getY(), bulletSize.getX(), bulletSize.getY());
        return playerBoundingBox2D.collidesWith(boundingBox2D);
    }

    public boolean collidesWithWorld(Vector2D playerPosition, Vector2D playerSize) {
        final MapBindings singleton = MapBindings.getSingleton();

        Real2D position = new Real2D(playerPosition.getX(), playerPosition.getY());

        BoundingBox2D playerBoundingBox2D = getBoundingBox2D(playerSize, playerPosition);

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

    private BoundingBox2D getBoundingBox2D(Vector2D playerSize, Vector2D playerPosition) {
        Real2D position = new Real2D(playerPosition.getX(), playerPosition.getY());

        return new BoundingBox2D(
                position.getRealX() + 0.05 + (1 - playerSize.getX()) / 2.0,
                position.getRealY() + 0.05 + (1 - playerSize.getY()) / 2.0,
                playerSize.getX() - 0.1,
                playerSize.getY() - 0.1);
    }
}
