package game.engine.entities;

import game.interfaces.Entity;
import game.interfaces.Logic;
import game.interfaces.Texture;
import game.utils.CSVSingleton;
import game.utils.TextureWrapper;
import game.utils.math.Vector2D;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 */
public class Sprite implements Entity, Logic {

    private final int spriteWidth;
    private final int spriteHeight;
    private final String path;
    protected Vector2D location = new Vector2D(0, 0);
    private int sprite;
    private Texture texture;
    private int listID;

    public Sprite(String type, String internalName) {
        final String result = CSVSingleton.getInstance().get(type, internalName);
        String[] splitteds = result.split(",");

        spriteWidth = new Integer(splitteds[0]);
        spriteHeight = new Integer(splitteds[1]);

        path = splitteds[2];

    }

    @Override
    public void draw() {
        texture.bind();
        glCallList(listID);
    }

    @Override
    public void load() {
        texture = TextureWrapper.loadTexture(path);
        listID = glGenLists(1);
        glNewList(listID, GL_COMPILE);
        glBegin(GL_QUADS);
        glTexCoord2d(0, 0);
        glVertex2d(-1, -1);
        glTexCoord2d(0, 1);
        glVertex2d(-1, 1);
        glTexCoord2d(1, 1);
        glVertex2d(1, 1);
        glTexCoord2d(1, 0);
        glVertex2d(1, -1);
        glEnd();
        glEndList();
    }

    @Override
    public void unload() {
        texture.delete();
    }


    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    @Override
    public void tick() {

    }
}
