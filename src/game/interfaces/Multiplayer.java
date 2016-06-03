package game.interfaces;

import game.multiplayer.MultiplayerLogic;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public interface Multiplayer {
    void init(String address);

    void start();

    default void setup() {
        MultiplayerLogic.singleton.setThreadUser(this);
        this.setDaemon(true);
    }

    void setDaemon(boolean isDaemon);

    boolean isServer();

    void interrupt();
}
