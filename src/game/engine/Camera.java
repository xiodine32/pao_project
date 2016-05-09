package game.engine;

import game.interfaces.Drawable;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 * Class ${CLASSNAME}.
 */
public class Camera implements Drawable {

    @Override
    public void draw() {
        glPushMatrix();
        glTranslated(0, 0, -1);
    }


    public void drawEnd() {
        glPopMatrix();
    }
}
