package game.multiplayer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class ServerClient extends Thread {

    private Server server;
    private Socket client;

    public ServerClient(Server server, Socket client) {
        this.server = server;
        this.client = client;
        start();
    }


    @Override
    public void run() {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            while (Server.RUNNING) {
                MultiplayerLogic.singleton.send(out);
                MultiplayerLogic.singleton.receive(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
            server.close(this);
        }
    }
}
