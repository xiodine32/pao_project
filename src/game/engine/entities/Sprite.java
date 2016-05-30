package game.engine.entities;

import game.interfaces.Entity;
import game.interfaces.Logic;
import game.interfaces.Texture;
import game.utils.CSVSingleton;
import game.utils.DisplayList;
import game.utils.TextureWrapper;
import game.utils.math.Vector2D;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 09/05/16.
 */
public class Sprite implements Entity {

    private final int spriteWidth;
    private final int spriteHeight;
    private final String path;
    private int sprite;
    private Texture texture;
    private DisplayList [] lists;

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
        lists[sprite].gl();
    }

    @Override
    public void load() {
        texture = TextureWrapper.loadTexture(path);

        lists = new DisplayList[texture.getWidth() / spriteWidth * (texture.getHeight() / spriteHeight)];
        int n = texture.getWidth() / spriteWidth;
        int m = texture.getHeight() / spriteHeight;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int k = i * n + j;
                int finalI = i;
                int finalJ = j;
                lists[k] = new DisplayList(() -> {
                    glBegin(GL_QUADS);
                    glTexCoord2d((finalI) / (double) n, (finalJ) / (double) m);
                    glVertex3d(-0.5, -0.5, 0);
                    glTexCoord2d((finalI) / (double) n, (finalJ + 1) / (double) m);
                    glVertex3d(-0.5, 0.5, 0);
                    glTexCoord2d((finalI + 1) / (double) n, (finalJ + 1) / (double) m);
                    glVertex3d(0.5, 0.5, 0);
                    glTexCoord2d((finalI + 1) / (double) n, (finalJ) / (double) m);
                    glVertex3d(0.5, -0.5, 0);
                    glEnd();
                });
            }
        }
    }

    @Override
    public void unload() {
        texture.delete();
    }


    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

}
