package game.engine.entities;

import game.interfaces.Collidable;
import game.interfaces.CollisionDetector;
import game.interfaces.Texture;
import game.utils.math.Vector2D;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Xiodine on 30/05/2016.
 * pao_project
 */
public class Bullet extends Sprite implements Collidable, Externalizable {

    private static final double MOVE_DELTA = 0.1;
    private static final double TIME = 10;
    private static Texture loadedTexture = null;
    private static boolean loadedTextureBool = false;
    private double timeLeft = TIME;
    private double animationLeft = TIME;
    private double lastTick = -1;
    private boolean alive = true;
    private Vector2D size = new Vector2D(0.5, 0.5);
    private Vector2D position;
    private Vector2D velocity;
    private boolean loaded = false;

    public Bullet(Vector2D position, Vector2D velocity) {
        super("bullets", "bullet");
        this.velocity = velocity;
        this.position = position;
    }

    public Bullet() {
        super("bullets", "test");
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
        if (collisionDetector.collidesWithWorld(position, size)) {
            position = oldPosition;
            velocity = velocity.scale(-1, 1);
        }

        oldPosition = position;
        position = position.translate(0,
                MOVE_DELTA * velocity.getY()
        );
        if (collisionDetector.collidesWithWorld(position, size)) {
            position = oldPosition;
            velocity = velocity.scale(1, -1);
        }
//        if (oldOld.equals(position)) {
//            alive = false;
//        }
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
//        glTranslated(-size.getX(), size.getY(), 0);
        position.glTranslate();
        glScaled(size.getX(), size.getY(), 1);
        super.draw();
        glPopMatrix();
        glColor4d(1, 1, 1, 1);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(timeLeft);
        out.writeObject(position);
        out.writeObject(velocity);
        out.writeBoolean(alive);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        timeLeft = in.readDouble();
        position = (Vector2D) in.readObject();
        velocity = (Vector2D) in.readObject();
        alive = in.readBoolean();
    }


    public boolean isAlive() {
        return alive;
    }

}
