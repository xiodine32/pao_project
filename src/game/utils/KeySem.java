package game.utils;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class KeySem extends BooleanT {

    private int scancode;

    public KeySem(int scancode) {
        this.scancode = scancode;
    }

    public void handle(KeyState state) {
        if (state.getScancode() == scancode) {
            pressed = state.isPressed();
        }
    }
    public void disablePressUntilUp() {
        pressed = false;
    }
}
