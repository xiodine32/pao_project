package game.engine.entities;

import game.interfaces.Camera;
import game.interfaces.KeyboardListener;
import game.interfaces.Logic;
import game.utils.KeySem;
import game.utils.KeyState;
import game.utils.math.Vector3D;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public class FollowingCamera implements Camera, Logic, KeyboardListener {

    private Tank followingTank;
    private Vector3D realPosition = new Vector3D(0, 0, -10);

    private KeySem keyQ = new KeySem(GLFW_KEY_Q);
    private KeySem keyE = new KeySem(GLFW_KEY_E);

    public FollowingCamera(Tank followingTank) {
        this.followingTank = followingTank;
    }

    @Override
    public void draw() {
        glPushMatrix();
        realPosition.glTranslate();
    }

    @Override
    public void drawEnd() {
        glPopMatrix();
    }

    @Override
    public void tick() {
        double deltaZ = 0;
        if (keyQ.isPressed())
            deltaZ += 0.1;
        if (keyE.isPressed())
            deltaZ -= 0.1;
        Vector3D position = new Vector3D(-followingTank.getPosition().getX(), -followingTank.getPosition().getY(), realPosition.getZ() + deltaZ);

        final double DELTA = 0.1;

        realPosition = realPosition.translate(
                DELTA * (-realPosition.getX() + position.getX()),
                DELTA * (-realPosition.getY() + position.getY()),
                DELTA * (-realPosition.getZ() + position.getZ())
        );
    }

    @Override
    public void handleKey(KeyState keyState) {
        keyQ.handle(keyState);
        keyE.handle(keyState);
    }
}
