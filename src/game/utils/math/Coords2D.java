package game.utils.math;

import java.io.Serializable;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Coords2D implements Serializable {
    private double x;
    private double y;

    Coords2D() {
    }

    Coords2D(double x, double y) {
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

    public boolean empty() {
        return getX() == 0 && getY() == 0;
    }
}
