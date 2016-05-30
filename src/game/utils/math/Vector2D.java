package game.utils.math;

import java.io.Serializable;

import static org.lwjgl.opengl.GL11.glTranslated;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Vector2D extends Coords2D implements Serializable, Comparable<Vector2D> {

    public Vector2D(double x, double y) {
        super(x, y);
    }

    Vector2D() {
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

    @Override
    public int compareTo(Vector2D o) {
        if (o.getX() == getX() && o.getY() == getY())
            return 0;
        if (getX() - o.getX() == 0)
            return (int) (getY() - o.getY());
        return (int) (getX() - o.getX());
    }
}
