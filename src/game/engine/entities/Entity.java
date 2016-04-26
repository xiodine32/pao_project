package game.engine.entities;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public abstract class Entity extends SpriteAnimated {

    protected boolean alive;

    public Entity(int spriteWidth, int spriteHeight) {
        super(spriteWidth, spriteHeight);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void draw() {
        // FIXME: 4/10/2016
    }

    public abstract void tick();
}
