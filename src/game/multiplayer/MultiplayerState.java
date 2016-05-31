package game.multiplayer;

import java.io.Serializable;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class MultiplayerState implements Serializable {
    public static final MultiplayerState SEND_MAP = new MultiplayerState(0);
    public static final MultiplayerState SEND_DATA = new MultiplayerState(1);

    private final int state;

    public MultiplayerState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "MultiplayerState{" +
                "state=" + state +
                '}';
    }
}
