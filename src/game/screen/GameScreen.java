package game.screen;

import game.engine.KeyboardEventListener;
import game.engine.Map;
import game.engine.MapDrawerAdaptor;
import game.engine.entities.MobileCamera;
import game.engine.entities.Tank;
import game.interfaces.Engine;
import game.interfaces.KeyboardListener;
import game.interfaces.Screen;
import game.utils.math.Vector3D;

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

    private MobileCamera mobileCamera = new MobileCamera(new Vector3D(-20, -20, -40));

    private MapDrawerAdaptor mapDrawerAdaptor = new MapDrawerAdaptor(map);


    @Override
    public void bindKeys(KeyboardEventListener keyboardEventListener) {
        listener = keyState -> {
            if (keyState.getScancode() == GLFW_KEY_ESCAPE && !keyState.isPressed()) {
                engine.stopRunning();
            }

        };
        keyboardEventListener.addEventListener(listener);
        keyboardEventListener.addEventListener(tank);
//        keyboardEventListener.addEventListener(mobileCamera);
    }

    @Override
    public void unbindKeys(KeyboardEventListener keyboardEventListener) {
        keyboardEventListener.removeEventListener(tank);
        keyboardEventListener.removeEventListener(listener);
    }

    @Override
    public void load(Engine engine) {
        this.engine = engine;
        tank.load();
        mapDrawerAdaptor.load();
    }

    @Override
    public void unload(Engine engine) {
        tank.unload();
        mapDrawerAdaptor.unload();
    }

    @Override
    public void draw() {
        mobileCamera.draw();
        mapDrawerAdaptor.draw();
        tank.draw();
        mobileCamera.drawEnd();
    }

    @Override
    public void tick() {
        mobileCamera.tick();
        mapDrawerAdaptor.tick();
        tank.tick();
    }
}
