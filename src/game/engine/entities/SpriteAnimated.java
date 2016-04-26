package game.engine.entities;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public abstract class SpriteAnimated extends Sprite {

    protected int spriteSelected;

    public SpriteAnimated(int spriteWidth, int spriteHeight) {
        super(spriteWidth, spriteHeight);
    }

    @Override
    public void draw() {
        super.draw();
        // FIXME: 4/10/2016
    }
}
