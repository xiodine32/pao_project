package game.engine.entities;

import game.utils.Coords2D;
import game.utils.Vector2D;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public abstract class Object {
    protected Vector2D location;
    protected Coords2D size;

    public abstract void draw();

    public Vector2D getLocation() {
        return location;
    }

    public Coords2D getSize() {
        return size;
    }
}
