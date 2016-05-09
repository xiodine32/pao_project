package game.interfaces;

import game.engine.KeyboardEventListener;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public interface Screen extends Logic, Drawable {
    void bindKeys(KeyboardEventListener keyboardEventListener);

    void unbindKeys(KeyboardEventListener keyboardEventListener);

    void load(Engine engine);

    void unload(Engine engine);
}
