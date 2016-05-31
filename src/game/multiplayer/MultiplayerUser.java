package game.multiplayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public interface MultiplayerUser {
    void receive(InputStream in) throws IOException, ClassNotFoundException;

    void send(OutputStream in) throws IOException, ClassNotFoundException;
}
