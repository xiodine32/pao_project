package game.multiplayer;

import game.interfaces.Multiplayer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

    private Thread startThread;

    public Client() {
        startThread = Thread.currentThread();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(address, PORT);
            socket.setTcpNoDelay(true);
            InputStream in = new BufferedInputStream(socket.getInputStream());
            OutputStream out = new BufferedOutputStream(socket.getOutputStream());
            boolean isFirst = true;
            while (RUNNING) {
                MultiplayerLogic.singleton.receive(in, 0);
                MultiplayerLogic.singleton.send(out, isFirst, 0);
                Thread.sleep(1000 / 120);
                isFirst = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        startThread.interrupt();
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
