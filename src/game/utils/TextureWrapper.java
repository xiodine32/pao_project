package game.utils;

import game.interfaces.Texture;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

/**
 * Created on 26/04/16.
 * Package game.utils.
 * Project pao_project.
 */
public final class TextureWrapper implements Texture {

    private final int id;
    private final int width;
    private final int height;


    private TextureWrapper(int width, int height, ByteBuffer data) {
        this.width = width;
        this.height = height;
        this.id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    }

    public static TextureWrapper loadTexture(String path) {
        /* Prepare image buffers */
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        /* Load image */
        stbi_set_flip_vertically_on_load(1);
        ByteBuffer image = stbi_load(path, w, h, comp, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load a texture file!" + " - " + path
                    + System.lineSeparator() + stbi_failure_reason());
        }

        /* Get width and height of image */
        int width = w.get();
        int height = h.get();

        return new TextureWrapper(width, height, image);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void delete() {
        glDeleteTextures(id);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
