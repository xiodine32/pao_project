package game.utils.math;

import static org.lwjgl.opengl.GL11.glTranslated;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Vector3D extends Coords3D {

    public Vector3D(Coords3D old) {
        super(old.getX(), old.getY(), old.getZ());
    }

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3D() {
        super();
    }

    public Vector3D translate(double deltaX, double deltaY, double deltaZ) {
        return new Vector3D(getX() + deltaX, getY() + deltaY, getZ() + deltaZ);
    }

    public Vector3D scale(double deltaX, double deltaY, double deltaZ) {
        return new Vector3D(getX() * deltaX, getY() * deltaY, getZ() * deltaZ);
    }

    public Vector3D rotate(double angle) {
        // FIXME: 4/10/2016
        return null;
    }

    public void glTranslate() {
        glTranslated(getX(), getY(), getZ());
    }


    public Vector3D translate(Coords3D delta) {
        return translate(delta.getX(), delta.getY(), delta.getZ());
    }
}
