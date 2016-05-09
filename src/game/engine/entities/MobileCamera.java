package game.engine.entities;

import game.utils.math.Vector3D;

import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public class MobileCamera extends FixedCamera {

    private Vector3D realPosition;

    public MobileCamera(Vector3D position) {
        super(position);
        realPosition = new Vector3D(10, 10, 10);
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public void draw() {
        final double DELTA = 0.3;

        realPosition = realPosition.translate(
                DELTA * (-realPosition.getX() + position.getX()),
                DELTA * (-realPosition.getY() + position.getY()),
                DELTA * (-realPosition.getZ() + position.getZ())
        );

        glPushMatrix();
        realPosition.glTranslate();
    }
}
