package game.engine.entities;

import game.interfaces.KeyboardListener;
import game.interfaces.Logic;
import game.utils.BooleanT;
import game.utils.KeyState;
import game.utils.math.Vector2D;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 */
public class Button extends Sprite implements Logic, KeyboardListener {

    private static final double TIME = 2;
    private final Vector2D vector2D;
    private BooleanT overCamera = new BooleanT();
    private double timeSpent = 0;
    private double lastTick = -1;
    private boolean pressed = false;

    public Button(String internalName, Vector2D vector2D) {
        super("buttons", internalName);
        this.vector2D = vector2D;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public void tick() {
        if (!overCamera.isPressed()) {
            lastTick = glfwGetTime();
            if (timeSpent > 0) {
                timeSpent -= 0.1;
                if (timeSpent < 0)
                    timeSpent = 0;
            }
            return;
        }
        if (lastTick == -1)
            lastTick = glfwGetTime();
        timeSpent += glfwGetTime() - lastTick;
        lastTick = glfwGetTime();
        if (timeSpent > TIME)
            pressed = true;
    }

    public void cameraOver() {
        overCamera.setPressed(true);
    }

    public void cameraOut() {
        overCamera.setPressed(false);
    }

    @Override
    public void draw() {
        glPushMatrix();
        glColor4d(1, 1 - timeSpent / TIME, 1 - timeSpent / TIME, 1);
        vector2D.glTranslate();
        super.draw();
        glColor4d(1, 1, 1, 1);
        glPopMatrix();
    }



    @Override
    public void handleKey(KeyState keyState) {

    }
}
