package game.engine.entities;

import game.interfaces.Collidable;
import game.interfaces.CollisionDetector;
import game.interfaces.KeyboardListener;
import game.utils.KeySem;
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
public class Tank extends Sprite implements KeyboardListener, Externalizable, Collidable {

    public static final Vector2D SIZE = new Vector2D(1, 1);
    private static final int BULLETS_COUNT = 5;
    private static final double MOVE_DELTA = 0.08;
    private static final double ROTATION_DELTA = 0.05;
    public double deadTick = 0;
    private Vector2D position = new Vector2D(0, 0);
    private double rotation = 90;
    private KeySem keyW = new KeySem(GLFW_KEY_W);
    private KeySem keyS = new KeySem(GLFW_KEY_S);
    private KeySem keyA = new KeySem(GLFW_KEY_A);
    private KeySem keyD = new KeySem(GLFW_KEY_D);
    private KeySem keySpace = new KeySem(GLFW_KEY_SPACE);
    private boolean dead = false;

    public Tank() {
        super("tanks", "test");
    }

    @Override
    public void draw() {
        glPushMatrix();
        glColor4d(1, 1, 1, 1);
        if (dead)
            glColor4d(1, 0, 0, 1);

        glTranslated(position.getX(), position.getY(), 0.1);
        glRotated((rotation - Math.PI / 2) / Math.PI * 180, 0, 0, 1);
        super.draw();
        glPopMatrix();
        glColor4d(1, 1, 1, 1);

    }


    @Override
    public void tick(CollisionDetector collisionDetector) {
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

        if (keySpace.isPressed() && BulletManager.getInstance().getBulletCount() < BULLETS_COUNT) {
            keySpace.disablePressUntilUp();

            BulletManager.getInstance().add(new Bullet(
                    position.translate(Math.cos(rotation) * 1.2, Math.sin(rotation) * 1.2)
                    , new Vector2D(
                    Math.cos(rotation),
                    Math.sin(rotation))));
        }

        Vector2D oldPosition = position;
        position = position.translate(
                delta * MOVE_DELTA * Math.cos(rotation),
                0
        );
        if (collisionDetector.collidesWithWorld(position, SIZE))
            position = oldPosition;

        oldPosition = position;
        position = position.translate(0,
                delta * MOVE_DELTA * Math.sin(rotation)
        );
        if (collisionDetector.collidesWithWorld(position, SIZE))
            position = oldPosition;

        if (BulletManager.getInstance().collidesWith(position, collisionDetector)) {
            dead = true;
            deadTick = glfwGetTime();
        }

        if (dead && glfwGetTime() - deadTick > 5) {
            dead = false;
        }
    }

    @Override
    public void handleKey(KeyState keyState) {
        keyW.handle(keyState);
        keyS.handle(keyState);
        keyA.handle(keyState);
        keyD.handle(keyState);
        keySpace.handle(keyState);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
}
