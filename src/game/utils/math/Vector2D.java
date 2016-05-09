package game.utils.math;

import static org.lwjgl.opengl.GL11.glTranslated;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Vector2D extends Coords2D {

    public Vector2D(Coords2D old) {
        super(old.getX(), old.getY());
    }

    public Vector2D(double x, double y) {
        super(x, y);
    }

    public Vector2D() {
        super();
    }

    public Vector2D translate(double deltaX, double deltaY) {
        return new Vector2D(getX() + deltaX, getY() + deltaY);
    }

    public Vector2D scale(double deltaX, double deltaY) {
        return new Vector2D(getX() * deltaX, getY() * deltaY);
    }

    public Vector2D rotate(double angle) {
        // FIXME: 4/10/2016
        return null;
    }

    public void glTranslate() {
        glTranslate(0);
    }

    public void glTranslate(double z) {
        glTranslated(getX(), getY(), z);
    }

    public Vector2D translate(Coords2D delta) {
        return translate(getX() + delta.getX(), getY() + delta.getY());
    }
}
