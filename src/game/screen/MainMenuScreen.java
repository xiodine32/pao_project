package game.screen;

import game.engine.KeyboardEventListener;
import game.engine.entities.Button;
import game.engine.entities.MobileCamera;
import game.interfaces.Engine;
import game.interfaces.KeyboardListener;
import game.interfaces.Multiplayer;
import game.interfaces.Screen;
import game.multiplayer.Client;
import game.multiplayer.MultiplayerLogic;
import game.multiplayer.Server;
import game.utils.Debug;
import game.utils.math.Vector2D;
import game.utils.math.Vector3D;

import javax.swing.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public class MainMenuScreen implements Screen {

    private Button host = new Button("host", new Vector2D(-1, 0));
    private Button client = new Button("client", new Vector2D(1, 0));

    private MobileCamera camera = new MobileCamera(new Vector3D(0, 0, -2));

    private Engine engine;
    private KeyboardListener listener;

    @Override
    public void load(Engine engine) {
        Debug.l("load");
        this.engine = engine;
        host.load();
        client.load();
    }

    @Override
    public void tick() {
        camera.tick();

        host.cameraOut();
        if (camera.getPosition().getX() > 1)
            host.cameraOver();

        client.cameraOut();
        if (camera.getPosition().getX() < -1)
            client.cameraOver();

        host.tick();
        client.tick();

        Multiplayer multiplayer = null;
        if (host.isPressed()) {
            multiplayer = new Server();
        }
        if (client.isPressed()) {
            multiplayer = new Client();
        }
        if (multiplayer != null) {
            String dialog = "";
            if (multiplayer instanceof Client)
                dialog = JOptionPane.showInputDialog("IP");
            if (dialog.isEmpty())
                dialog = "localhost";
            multiplayer.init(dialog);
            multiplayer.setup();
            multiplayer.start();
            GameScreen gameScreen = new GameScreen();
            engine.changeScreen(gameScreen);
            MultiplayerLogic.singleton.setGameScreen(gameScreen);
        }
    }

    @Override
    public void draw() {
        camera.draw();
        host.draw();
        client.draw();
        camera.drawEnd();
    }

    @Override
    public void unload(Engine engine) {
        host.unload();
        client.unload();
        Debug.l("unload");
    }

    @Override
    public void bindKeys(KeyboardEventListener keyboardEventListener) {
        listener = keyState -> {
            if (keyState.getScancode() == GLFW_KEY_ESCAPE && !keyState.isPressed()) {
                engine.stopRunning();
            }

        };
        keyboardEventListener.addEventListener(listener);
        keyboardEventListener.addEventListener(camera);
    }

    @Override
    public void unbindKeys(KeyboardEventListener keyboardEventListener) {
        keyboardEventListener.removeEventListener(listener);
        keyboardEventListener.removeEventListener(camera);
    }
}
