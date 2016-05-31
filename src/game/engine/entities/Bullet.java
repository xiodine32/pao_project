package game.engine.entities;

import game.interfaces.Collidable;
import game.interfaces.CollisionDetector;
import game.interfaces.Texture;
import game.utils.math.Vector2D;

import java.io.Serializable;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Xiodine on 30/05/2016.
 * pao_project
 */
public class Bullet extends Sprite implements Collidable, Serializable {

    public transient static final Vector2D SIZE = new Vector2D(0.5, 0.5);
    private transient static final double MOVE_DELTA = 0.1;
    private transient static final double TIME = 10;
    private transient static Texture loadedTexture = null;
    private transient static boolean loadedTextureBool = false;


    private double timeLeft = TIME;
    private double animationLeft = TIME;
    private transient double lastTick = -1;
    private transient boolean loaded = false;
    private boolean alive = true;

    private Vector2D position;
    private Vector2D velocity;
    private int UID;

    public Bullet(Vector2D position, Vector2D velocity) {
        super("bullets", "bullet");
        this.velocity = velocity;
        this.position = position;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    @Override
    public void tick(CollisionDetector collisionDetector) {

        if (lastTick == -1)
            lastTick = glfwGetTime();

        timeLeft -= (glfwGetTime() - lastTick);

        if (timeLeft < 0) {
            alive = false;
            return;
        }

        if (animationLeft - timeLeft > 0.25) {
            animationLeft = timeLeft;
            setSprite((getSprite() + 1) % 4);
        }
        lastTick = glfwGetTime();
        Vector2D oldOld = position;
        Vector2D oldPosition = position;
        position = position.translate(
                MOVE_DELTA * velocity.getX(),
                0
        );
        if (collisionDetector.collidesWithWorld(position, SIZE)) {
            position = oldPosition;
            velocity = velocity.scale(-1, 1);
        }

        oldPosition = position;
        position = position.translate(0,
                MOVE_DELTA * velocity.getY()
        );
        if (collisionDetector.collidesWithWorld(position, SIZE)) {
            position = oldPosition;
            velocity = velocity.scale(1, -1);
        }
        if (oldOld.equals(position)) {
            alive = false;
        }
    }

    @Override
    public void draw() {
        if (!loaded) {
            loaded = true;
            texture = loadedTexture;
            if (!loadedTextureBool) {
                loadedTextureBool = true;
                loadTexture();
                loadedTexture = texture;
            }
            loadDisplayLists();
        }

        if (!alive) {
            return;
        }
        glPushMatrix();
        glColor4d(1, 0, 0, timeLeft);
        if (timeLeft > 1) {
            glColor4d(1, timeLeft / TIME, timeLeft / TIME, 1);
        }
//        glTranslated(-SIZE.getX(), SIZE.getY(), 0);
        position.glTranslate();
        glScaled(SIZE.getX(), SIZE.getY(), 1);
        super.draw();
        glPopMatrix();
        glColor4d(1, 1, 1, 1);
    }

    public boolean isAlive() {
        return alive;
    }

    public Vector2D getPosition() {
        return position;
    }
}
