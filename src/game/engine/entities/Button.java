package game.engine.entities;

import game.interfaces.KeyboardListener;
import game.interfaces.Logic;
import game.utils.KeyState;

/**
 * Created on 09/05/16.
 */
public class Button extends Sprite implements Logic, KeyboardListener {

    public Button(String internalName) {
        super("button", internalName);

    }

    @Override
    public void tick() {

    }

    @Override
    public void handleKey(KeyState keyState) {

    }
}
