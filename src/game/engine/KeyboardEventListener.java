package game.engine;

import game.interfaces.KeyboardListener;
import game.interfaces.Observer;
import game.utils.KeyState;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Created on 09/05/16.
 * pao_project - game.engine - KeyboardEventListener.
 */
public class KeyboardEventListener extends GLFWKeyCallback implements Observer<KeyboardListener, KeyState> {

    private ArrayList<KeyboardListener> listeners = new ArrayList<>();

    @Override
    public void addEventListener(KeyboardListener listener) {
        listeners.add(listener);
    }

    @Override
    public void fire(KeyState returnType) {
        listeners.forEach((item) -> item.handleKey(returnType));

    }

    @Override
    public void removeEventListener(KeyboardListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        fire(new KeyState(key, action != GLFW_RELEASE));
    }
}
