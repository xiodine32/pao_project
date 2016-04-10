package game.engine.entities;

import game.utils.TextureLoader;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class Sprite extends Texture2D {

    protected int spriteWidth;
    protected int spriteHeight;

    @Override
    public void loadTexture(String path) {
        this.textureID = TextureLoader.getInstance().load(path);
    }

    @Override
    public void deleteTexture() {
        TextureLoader.getInstance().unload(this.textureID);
    }

    @Override
    public void draw() {
        // FIXME: 4/10/2016 
    }
}
