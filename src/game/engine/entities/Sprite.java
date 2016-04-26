package game.engine.entities;

import game.utils.TextureWrapper;

import static org.lwjgl.opengl.GL11.*;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Sprite extends Object {

    protected TextureWrapper texture;

    protected int spriteWidth;
    protected int spriteHeight;

    public Sprite(int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public void loadTexture(String path) {
        this.texture = TextureWrapper.loadTexture(path);
    }

    public void deleteTexture() {
        this.texture.delete();
    }

    @Override
    public void draw() {
        this.texture.bind();
        glColor4d(1, 1, 1, 1);
        glBegin(GL_QUADS);

        glTexCoord2d(0, 0);
        glVertex3d(-1, -1, -2);
        glTexCoord2d(0, 1);
        glVertex3d(-1, 1, -2);
        glTexCoord2d(1, 1);
        glVertex3d(1, 1, -2);
        glTexCoord2d(1, 0);
        glVertex3d(1, -1, -2);
        glEnd();
    }
}
