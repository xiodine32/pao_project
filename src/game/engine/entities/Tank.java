package game.engine.entities;

import game.interfaces.Collidable;
import game.interfaces.CollisionDetector;
import game.interfaces.KeyboardListener;
import game.interfaces.Texture;
import game.utils.KeySem;
import game.utils.KeyState;
import game.utils.math.Vector2D;
import game.utils.math.Vector3D;

import java.io.Serializable;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 */
public class Tank extends Sprite implements KeyboardListener, Serializable, Collidable {

    public static transient final Vector2D SIZE = new Vector2D(1, 1);
    private static transient final int BULLETS_COUNT = 5;
    private static transient final double MOVE_DELTA = 0.08;
    private static transient final double MOVE_BACKWADS = 0.8;
    private static transient final double ROTATION_DELTA = 0.05;
    private static transient final int TICKS_TO_DRAW = 10;
    private static transient Texture texture = null;
    public double deadTick = 0;
    public Vector3D color = null;
    private Vector2D position = new Vector2D(0, 0);
    private double rotation = 90;
    private boolean dead = false;
    private transient KeySem keyW = new KeySem(GLFW_KEY_W);
    private transient KeySem keyS = new KeySem(GLFW_KEY_S);
    private transient KeySem keyA = new KeySem(GLFW_KEY_A);
    private transient KeySem keyD = new KeySem(GLFW_KEY_D);
    private transient KeySem keySpace = new KeySem(GLFW_KEY_SPACE);
    private transient boolean loaded = false;
    private int UID = -1;
    private transient int ticksToDraw = 0;

    public Tank() {
        super("tanks", "tank");
        Random random = new Random();
        color = new Vector3D(Math.min(0.5, random.nextDouble()),
                Math.min(0.5, random.nextDouble()),
                Math.min(0.5, random.nextDouble()));
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    @Override
    public void load() {
        loaded = true;
        super.load();
    }

    @Override
    protected void loadTexture() {
        if (texture == null) {
            super.loadTexture();
            texture = super.texture;
            return;
        }
        super.texture = texture;
    }

    @Override
    public void draw() {
        if (!loaded)
            load();

        glPushMatrix();
        glColor4d(color.getX(), color.getY(), color.getZ(), 1);
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
        if (delta < 0)
            delta *= MOVE_BACKWADS;

        if (keySpace.isPressed() && BulletManager.getInstance().getBulletCount() < BULLETS_COUNT) {
            keySpace.disablePressUntilUp();

            BulletManager.getInstance().add(new Bullet(
                    position.translate(Math.cos(rotation) * 1.2, Math.sin(rotation) * 1.2)
                    , new Vector2D(
                    Math.cos(rotation),
                    Math.sin(rotation))));
        }
        boolean movedX = delta != 0, movedY = delta != 0;
        Vector2D oldPosition = position;
        position = position.translate(
                delta * MOVE_DELTA * Math.cos(rotation),
                0
        );
        if (collisionDetector.collidesWithWorld(position, SIZE)) {
            position = oldPosition;
            movedX = false;
        }

        oldPosition = position;
        position = position.translate(0,
                delta * MOVE_DELTA * Math.sin(rotation)
        );
        if (collisionDetector.collidesWithWorld(position, SIZE)) {
            position = oldPosition;
            movedY = false;
        }

        if (BulletManager.getInstance().collidesWith(position, collisionDetector)) {
            dead = true;
            deadTick = glfwGetTime();
        }

        if (movedX || movedY) {
            if (ticksToDraw == 0) {
                ticksToDraw = TICKS_TO_DRAW;
                setSprite((getSprite() + 1) % 4);
            }
            ticksToDraw--;
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


    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tank tank = (Tank) o;

        return Double.compare(tank.rotation, rotation) == 0 && dead == tank.dead && position.equals(tank.position);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = position.hashCode();
        temp = Double.doubleToLongBits(rotation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dead ? 1 : 0);
        return result;
    }
}
