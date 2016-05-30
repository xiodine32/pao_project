package game.utils.math;

/**
 * Created by NeaguR on 30.05.2016.
 * PROJECT pao_project
 */
public class BoundingBox2D {
    private final double x;
    private final double y;
    private final double xEnd;
    private final double yEnd;

    public BoundingBox2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.xEnd = x + width;
        this.yEnd = y + height;
    }

    public boolean collidesWith(BoundingBox2D boundingBox2D) {
        double xLeft = Math.max(boundingBox2D.x, x);
        double xRight = Math.min(boundingBox2D.xEnd, xEnd);
        double yTop = Math.min(boundingBox2D.y, y);
        double yBottom = Math.max(boundingBox2D.yEnd, yEnd);

        return xEnd > boundingBox2D.x &&
                x < boundingBox2D.xEnd &&
                yEnd > boundingBox2D.y &&
                y < boundingBox2D.yEnd;
    }

    @Override
    public String toString() {
        return "BoundingBox2D{" +
                "x=" + x +
                ", y=" + y +
                ", xEnd=" + xEnd +
                ", yEnd=" + yEnd +
                '}';
    }
}
