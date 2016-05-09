package game.screen;

import game.engine.KeyboardEventListener;
import game.engine.entities.MobileCamera;
import game.engine.entities.Sprite;
import game.interfaces.*;
import game.utils.Debug;
import game.utils.math.Vector3D;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public class MainMenuScreen implements Screen {

    private Entity sprite = new Sprite("buttons", "test");

    private Camera camera = new MobileCamera(new Vector3D(0, 0, -2));

    private Engine engine;
    private KeyboardListener listener;

    @Override
    public void load(Engine engine) {
        Debug.l("load");
        this.engine = engine;
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
    public void unload(Engine engine) {
        sprite.unload();
        Debug.l("unload");
    }

    @Override
    public void bindKeys(KeyboardEventListener keyboardEventListener) {
        listener = keyState -> {
            if (keyState.getScancode() == GLFW_KEY_Q && !keyState.isPressed()) {
                engine.stopRunning();
            }

        };
        keyboardEventListener.addEventListener(listener);
    }

    @Override
    public void unbindKeys(KeyboardEventListener keyboardEventListener) {
        keyboardEventListener.removeEventListener(listener);
    }
}
