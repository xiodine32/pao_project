package game.engine;

import game.interfaces.Drawable;
import game.utils.math.Vector3D;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public class Camera implements Drawable {

    private Vector3D position;

    public Camera(Vector3D position) {
        this.position = position;
    }

    @Override
    public void draw() {
        glPushMatrix();
        glTranslated(position.getX(), position.getY(), position.getZ());
    }


    public void drawEnd() {
        glPopMatrix();
    }
}
