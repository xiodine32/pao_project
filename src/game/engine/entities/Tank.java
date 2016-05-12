package game.engine.entities;

import game.interfaces.KeyboardListener;
import game.interfaces.Logic;
import game.utils.KeyState;
import game.utils.math.Vector2D;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 */
public class Tank extends Sprite implements Logic, KeyboardListener, Externalizable {

    static final double MOVE_DELTA = 0.05;
    static final double ROTATION_DELTA = 0.1;

    private int delta = 0;
    private int deltaRotation = 0;
    private Vector2D position = new Vector2D(0, 0);
    private double rotation;

    public Tank() {
        super("tanks", "test");
    }

    @Override
    public void draw() {
        glPushMatrix();
        glTranslated(position.getX(), position.getY(), -10);
        glRotated((rotation - Math.PI / 2) / Math.PI * 180, 0, 0, 1);
        super.draw();
        glPopMatrix();
    }

    @Override
    public void tick() {
        if (deltaRotation != 0) {
            rotation += deltaRotation * ROTATION_DELTA;
        }
        if (delta != 0) {
            position = position.translate(
                    delta * MOVE_DELTA * Math.cos(rotation),
                    delta * MOVE_DELTA * Math.sin(rotation)
            );
        }
    }

    @Override
    public void handleKey(KeyState keyState) {
        if (keyState.getScancode() == GLFW_KEY_W) {
            delta = 0;
            if (keyState.isPressed())
                delta++;
        }
        if (keyState.getScancode() == GLFW_KEY_S) {
            delta = 0;
            if (keyState.isPressed())
                delta--;
        }
        if (keyState.getScancode() == GLFW_KEY_A) {
            deltaRotation = 0;
            if (keyState.isPressed())
                deltaRotation = 1;
        }
        if (keyState.getScancode() == GLFW_KEY_D) {
            deltaRotation = 0;
            if (keyState.isPressed())
                deltaRotation = -1;
        }

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}
