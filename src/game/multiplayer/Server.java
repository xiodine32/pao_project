package game.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class Server extends Thread implements Multiplayer {

    private static final int PORT = 6666;
    public static boolean RUNNING = true;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (RUNNING) {
                Socket socket = serverSocket.accept();
                new ServerClient(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(String address) {
        // does nothing... :)
    }

    @Override
    public void setup() {
        MultiplayerLogic.singleton.setThreadUser(this);
        this.setDaemon(true);
    }

    public void close(ServerClient serverClient) {
        try {
            serverClient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
