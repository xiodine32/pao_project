package game.utils;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class DisplayList {

    private int displayID = 0;

    public DisplayList(Runnable consumer) {
        displayID = glGenLists(1);
        glNewList(displayID, GL_COMPILE);
        consumer.run();
        glEndList();
    }

    public void gl() {
        glCallList(displayID);
    }
}
