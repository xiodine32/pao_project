package game.screen;

import game.engine.Camera;
import game.engine.entities.Sprite;
import game.interfaces.Entity;
import game.interfaces.Screen;
import game.utils.Debug;
import game.utils.math.Vector3D;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public class MainMenuScreen implements Screen {

    private Entity sprite = new Sprite("buttons", "test");

    private Camera camera = new Camera(new Vector3D(0, 0, -2));

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
        camera.draw();
        sprite.draw();
        camera.drawEnd();
    }

    @Override
    public void unload() {
        sprite.unload();
        Debug.l("unload");
    }
}
