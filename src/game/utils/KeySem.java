package game.utils;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class KeySem {

    private int scancode;
    private boolean pressed;

    public KeySem(int scancode) {
        this.scancode = scancode;
    }

    public boolean isPressed() {
        return pressed;
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
