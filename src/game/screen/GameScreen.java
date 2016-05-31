package game.screen;

import game.engine.BoundingBoxCollisionDetector;
import game.engine.KeyboardEventListener;
import game.engine.Map;
import game.engine.MapDrawerAdaptor;
import game.engine.entities.BulletManager;
import game.engine.entities.FollowingCamera;
import game.engine.entities.Tank;
import game.interfaces.CollisionDetector;
import game.interfaces.Engine;
import game.interfaces.KeyboardListener;
import game.interfaces.Screen;
import game.multiplayer.MultiplayerLogic;
import game.utils.math.Real2D;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class GameScreen implements Screen {

    public ArrayList<Tank> otherTanks = new ArrayList<>();
    private KeyboardListener listener;
    private Engine engine;
    private Map map = new Map();
    private Tank tank = new Tank();
    private FollowingCamera mobileCamera = new FollowingCamera(tank);

    private MapDrawerAdaptor mapDrawerAdaptor = new MapDrawerAdaptor(map);
    private CollisionDetector collisionDetector = new BoundingBoxCollisionDetector(map);

    public GameScreen() {
        tank.setPosition(new Real2D(1, 1));
    }

    @Override
    public void bindKeys(KeyboardEventListener keyboardEventListener) {
        listener = keyState -> {
            if (keyState.getScancode() == GLFW_KEY_ESCAPE && !keyState.isPressed()) {
                engine.stopRunning();
            }

        };
        keyboardEventListener.addEventListener(listener);
        keyboardEventListener.addEventListener(tank);
        keyboardEventListener.addEventListener(mobileCamera);
    }

    @Override
    public void unbindKeys(KeyboardEventListener keyboardEventListener) {
        keyboardEventListener.removeEventListener(tank);
        keyboardEventListener.removeEventListener(mobileCamera);
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
        MultiplayerLogic.singleton.stop();
    }

    @Override
    public void draw() {
        mobileCamera.draw();
        if (mapDrawerAdaptor == null) {
            mapDrawerAdaptor = new MapDrawerAdaptor(map);
            collisionDetector = new BoundingBoxCollisionDetector(map);
            mapDrawerAdaptor.load();
        }
        mapDrawerAdaptor.draw();
        tank.draw();
        otherTanks.forEach(Tank::draw);
        BulletManager.getInstance().draw();
        mobileCamera.drawEnd();
    }

    @Override
    public void tick() {
        if (mapDrawerAdaptor != null)
            mapDrawerAdaptor.tick();
        BulletManager.getInstance().tick(collisionDetector);
        tank.tick(collisionDetector);
        mobileCamera.tick();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        mapDrawerAdaptor = null;
    }

    public Tank getTank() {
        return tank;
    }
}
