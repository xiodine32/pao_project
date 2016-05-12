package game.utils.math;

/**
 * Created on 09/05/16.
 * pao_project - game.utils.math - Real2D.
 */
public class Real2D extends Vector2D {

    private final static double STEP = 0.2;

    public Real2D(double x, double y) {
        super(x * STEP, y * STEP);
    }

    public Real2D() {
        super();
    }

    public Real2D translate(double deltaX, double deltaY) {
        return new Real2D(getX() / STEP + deltaX, getY() / STEP + deltaY);
    }

    public Real2D scale(double deltaX, double deltaY) {
        return new Real2D(getX() / STEP * deltaX, getY() / STEP * deltaY);
    }

    @Override
    public void glTranslate() {
        super.glTranslate();
    }

    @Override
    public void glTranslate(double z) {
        super.glTranslate(z);
    }

    @Override
    public String toString() {
        return "Real2D{} " + super.toString();
    }
}
