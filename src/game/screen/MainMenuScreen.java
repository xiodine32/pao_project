package game.screen;

import game.engine.entities.Sprite;
import game.interfaces.Entity;
import game.interfaces.Screen;
import game.utils.Debug;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public class MainMenuScreen implements Screen {

    private Entity sprite = new Sprite("buttons", "test");

    @Override
    public void load() {
        Debug.l("load");
        sprite.load();
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw() {
        sprite.draw();
    }

    @Override
    public void unload() {
        sprite.unload();
        Debug.l("unload");
    }
}
