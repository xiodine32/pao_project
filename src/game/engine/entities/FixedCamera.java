package game.engine.entities;

import game.interfaces.Camera;
import game.utils.math.Vector3D;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public abstract class FixedCamera implements Camera {

    protected Vector3D position;

    public FixedCamera(Vector3D position) {
        this.position = position;
    }

    public Vector3D getPosition() {
        return position;
    }

    @Override
    public void draw() {
        glPushMatrix();
        position.glTranslate();
    }

    @Override
    public void drawEnd() {
        glPopMatrix();
    }
}
