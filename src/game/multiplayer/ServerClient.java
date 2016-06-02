package game.multiplayer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class ServerClient extends Thread {

    private static int UID = 0;
    private Server server;
    private Socket client;
    private int uid = 0;
    public ServerClient(Server server, Socket client) {
        this.server = server;
        this.client = client;
        start();
        uid = ++UID;
    }


    @Override
    public void run() {
        try {
            InputStream in = new BufferedInputStream(client.getInputStream());
            OutputStream out = new BufferedOutputStream(client.getOutputStream());
            boolean isFirst = true;
            while (Server.RUNNING) {
                MultiplayerLogic.singleton.send(out, isFirst, uid);
                MultiplayerLogic.singleton.receive(in, uid);
                isFirst = false;
                Thread.sleep(1000 / 120);
            }
        } catch (Exception e) {
            e.printStackTrace();
            server.close(this);
        }
    }
}
