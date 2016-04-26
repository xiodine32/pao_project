package game.screen;

import game.engine.entities.Sprite;
import game.interfaces.Screen;
import game.utils.Debug;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public class MainMenuScreen implements Screen {

    Sprite test = new Sprite(64, 64);

    @Override
    public void load() {
        Debug.l("load");
        test.loadTexture("res/test.png");
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw() {
        test.draw();
    }

    @Override
    public void unload() {
        test.deleteTexture();
        Debug.l("unload");
    }
}
