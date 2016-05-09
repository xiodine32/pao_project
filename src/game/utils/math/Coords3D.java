package game.utils.math;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Coords3D {
    private double x;
    private double y;
    private double z;


    public Coords3D() {
    }

    public Coords3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


    @Override
    public String toString() {
        return "Coords3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
