package game.engine.entities;

import game.interfaces.KeyboardListener;
import game.interfaces.Logic;
import game.utils.KeySem;
import game.utils.KeyState;
import game.utils.math.Vector2D;

import java.awt.event.MouseEvent;
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

    private KeySem keyW = new KeySem(GLFW_KEY_W);
    private KeySem keyS = new KeySem(GLFW_KEY_S);
    private KeySem keyA = new KeySem(GLFW_KEY_A);
    private KeySem keyD = new KeySem(GLFW_KEY_D);

    public Tank() {
        super("tanks", "test");
    }

    @Override
    public void draw() {
        glPushMatrix();
        glTranslated(position.getX(), position.getY(), 0.1);
        glRotated((rotation - Math.PI / 2) / Math.PI * 180, 0, 0, 1);
        super.draw();
        glPopMatrix();
    }

    @Override
    public void tick() {
        if (keyA.isPressed()) {
            rotation += ROTATION_DELTA;
        }
        if (keyD.isPressed()) {
            rotation -= ROTATION_DELTA;
        }
        double delta = 0;
        if (keyW.isPressed()) {
            delta++;
        }
        if (keyS.isPressed()) {
            delta--;
        }

        position = position.translate(
                delta * MOVE_DELTA * Math.cos(rotation),
                delta * MOVE_DELTA * Math.sin(rotation)
        );
    }

    @Override
    public void handleKey(KeyState keyState) {
        keyW.handle(keyState);
        keyS.handle(keyState);
        keyA.handle(keyState);
        keyD.handle(keyState);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    public Vector2D getPosition() {
        return position;
    }
}
