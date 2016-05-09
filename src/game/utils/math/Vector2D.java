package game.utils.math;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Vector2D extends Coords2D {

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
}
