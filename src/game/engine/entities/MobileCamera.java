package game.engine.entities;

import game.interfaces.KeyboardListener;
import game.interfaces.Logic;
import game.utils.KeyState;
import game.utils.math.Vector3D;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public class MobileCamera extends FixedCamera implements KeyboardListener, Logic {

    private static final double SPEED = 0.05;
    private Vector3D realPosition;
    private Vector3D delta = new Vector3D(0, 0, 0);


    public MobileCamera(Vector3D position) {
        super(position);
        realPosition = new Vector3D(10, 10, 10);
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public void draw() {
        glPushMatrix();
        realPosition.glTranslate();
    }

    @Override
    public void handleKey(KeyState keyState) {
        if (keyState.getScancode() == GLFW_KEY_W) {
            delta = delta.scale(1, 0, 1);
            if (keyState.isPressed())
                delta = delta.translate(0, -SPEED, 0);
        }

        if (keyState.getScancode() == GLFW_KEY_S) {
            delta = delta.scale(1, 0, 1);
            if (keyState.isPressed())
                delta = delta.translate(0, SPEED, 0);
        }
        if (keyState.getScancode() == GLFW_KEY_A) {
            delta = delta.scale(0, 1, 1);
            if (keyState.isPressed())
                delta = delta.translate(SPEED, 0, 0);
        }
        if (keyState.getScancode() == GLFW_KEY_D) {
            delta = delta.scale(0, 1, 1);
            if (keyState.isPressed())
                delta = delta.translate(-SPEED, 0, 0);
        }

        if (keyState.getScancode() == GLFW_KEY_E) {
            delta = delta.scale(1, 1, 0);
            if (keyState.isPressed())
                delta = delta.translate(0, 0, SPEED);
        }
        if (keyState.getScancode() == GLFW_KEY_Q) {
            delta = delta.scale(1, 1, 0);
            if (keyState.isPressed())
                delta = delta.translate(0, 0, -SPEED);
        }
    }

    @Override
    public void tick() {
        final double DELTA = 0.3;

        realPosition = realPosition.translate(
                DELTA * (-realPosition.getX() + position.getX()),
                DELTA * (-realPosition.getY() + position.getY()),
                DELTA * (-realPosition.getZ() + position.getZ())
        );

        if (delta.empty())
            return;

        this.position = this.position.translate(delta);
    }
}
