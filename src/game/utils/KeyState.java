package game.utils;

/**
 * Created on 09/05/16.
 * pao_project - game.utils - KeyState.
 */
public class KeyState {
    private int scancode;
    private boolean pressed;

    public KeyState(int scancode, boolean pressed) {
        this.scancode = scancode;
        this.pressed = pressed;
    }

    public int getScancode() {
        return scancode;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public String toString() {
        return "KeyState{" +
                "scancode=" + scancode +
                ", pressed=" + pressed +
                '}';
    }
}
