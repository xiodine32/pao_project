package game.screen;

import game.engine.KeyboardEventListener;
import game.engine.Map;
import game.engine.entities.Tank;
import game.interfaces.Engine;
import game.interfaces.KeyboardListener;
import game.interfaces.Screen;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class GameScreen implements Screen {

    private KeyboardListener listener;
    private Engine engine;

    private Map map = new Map();
    private Tank tank = new Tank();


    @Override
    public void bindKeys(KeyboardEventListener keyboardEventListener) {
        listener = keyState -> {
            if (keyState.getScancode() == GLFW_KEY_ESCAPE && !keyState.isPressed()) {
                engine.stopRunning();
            }

        };
        keyboardEventListener.addEventListener(listener);
        keyboardEventListener.addEventListener(tank);
    }

    @Override
    public void unbindKeys(KeyboardEventListener keyboardEventListener) {
        keyboardEventListener.removeEventListener(listener);
    }

    @Override
    public void load(Engine engine) {
        this.engine = engine;
        tank.load();
    }

    @Override
    public void unload(Engine engine) {
        tank.unload();
    }

    @Override
    public void draw() {
        tank.draw();
    }

    @Override
    public void tick() {
        tank.tick();
    }
}
