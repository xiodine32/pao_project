package game.utils.math;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Coords2D {
    private double x;
    private double y;

    public Coords2D() {
    }

    public Coords2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Coords2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
