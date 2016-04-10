package game.interfaces;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public interface MultiplayerUser {

    /**
     * Sends packet to other user.
     *
     * @return string
     */
    String sendPacket();

    /**
     * Handles tick packet from other user.
     *
     * @param packet string
     */
    void handlePacket(String packet);
}
