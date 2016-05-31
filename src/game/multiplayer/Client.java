package game.multiplayer;

import game.interfaces.Multiplayer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class Client extends Thread implements Multiplayer {

    private static final int PORT = 6666;
    public static boolean RUNNING = true;
    private String address;

    @Override
    public void run() {
        try {
            Socket socket = new Socket(address, PORT);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            boolean isFirst = true;
            while (RUNNING) {
                MultiplayerLogic.singleton.receive(in);
                MultiplayerLogic.singleton.send(out, isFirst);
                isFirst = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setup() {
        MultiplayerLogic.singleton.setThreadUser(this);
        this.setDaemon(true);
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public void init(String address) {
        this.address = address;
    }
}
