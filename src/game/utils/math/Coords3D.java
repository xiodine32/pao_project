package game.utils.math;

import java.io.Serializable;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Coords3D implements Serializable {
    private double x;
    private double y;
    private double z;


    Coords3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Coords3D from2D(Coords2D from, double z) {
        return new Coords3D(from.getX(), from.getY(), z);
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

    public boolean empty() {
        return getX() == 0 && getY() == 0 && getZ() == 0;
    }
}
