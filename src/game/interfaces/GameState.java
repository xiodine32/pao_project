package game.interfaces;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public interface GameState {
    int getID();

    String toPacket();

    GameState fromPacket();
}
