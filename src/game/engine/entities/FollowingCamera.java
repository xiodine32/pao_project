package game.engine.entities;

import game.interfaces.Camera;
import game.interfaces.Logic;
import game.utils.math.Vector3D;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public class FollowingCamera implements Camera, Logic {

    private Tank followingTank;
    private Vector3D realPosition = new Vector3D(0, 0, -1);;

    public FollowingCamera(Tank followingTank) {
        this.followingTank = followingTank;
    }

    @Override
    public void draw() {
        glPushMatrix();
        realPosition.glTranslate();
    }

    @Override
    public void drawEnd() {
        glPopMatrix();
    }

    @Override
    public void tick() {
        Vector3D position = new Vector3D(-followingTank.getPosition().getX(), -followingTank.getPosition().getY(), -10);

        final double DELTA = 0.2;

        realPosition = realPosition.translate(
                DELTA * (-realPosition.getX() + position.getX()),
                DELTA * (-realPosition.getY() + position.getY()),
                DELTA * (-realPosition.getZ() + position.getZ())
        );
    }
}
