package game.engine;

import game.interfaces.GameState;

import java.io.Externalizable;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public abstract class Packet implements Externalizable {
    GameState gameState;
    double timestamp;
}
