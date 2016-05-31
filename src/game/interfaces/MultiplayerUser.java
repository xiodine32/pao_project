package game.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public interface MultiplayerUser {
    void receive(InputStream inputStream, int uid) throws IOException, ClassNotFoundException;

    void send(OutputStream in, boolean isFirst, int uid) throws IOException, ClassNotFoundException;
}
