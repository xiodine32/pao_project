package game.multiplayer;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public interface Multiplayer {
    void init(String address);

    void start();

    void setup();

    boolean isServer();

    void interrupt();
}
