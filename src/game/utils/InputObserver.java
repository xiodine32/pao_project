package game.utils;

import game.interfaces.Observer;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.ArrayList;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class InputObserver extends GLFWKeyCallback {
    private static InputObserver singleton;
    private ArrayList<Observer<Integer, java.lang.Boolean>> observers;

    private InputObserver() {
        observers = new ArrayList<>();
        // TODO: setKeyCallback
//        glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
    }

    public static InputObserver getInstance() {
        if (singleton == null)
            singleton = new InputObserver();
        return singleton;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        // TODO Auto-generated method stub
//        keys[key] = action != GLFW_RELEASE;
    }

    public void addEventListener(Observer<Integer, java.lang.Boolean> observer) {
        observers.add(observer);
    }

    public void fire(int eventType, boolean returnType) {
        observers.stream()
                .filter(element -> element.respondsTo(eventType))
                .forEach(element -> element.fired(eventType, returnType));
    }

    public void removeEventListener(Observer<Integer, java.lang.Boolean> observer) {
        observers.remove(observer);
    }
}
