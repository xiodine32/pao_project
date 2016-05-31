package game.multiplayer;

import game.engine.Map;
import game.interfaces.Multiplayer;
import game.interfaces.MultiplayerUser;
import game.screen.GameScreen;
import game.utils.Debug;

import java.io.*;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class MultiplayerLogic implements MultiplayerUser {
    public final static MultiplayerLogic singleton = new MultiplayerLogic();
    private GameScreen gameScreen;
    private Multiplayer threadUser;
    private boolean firstContact = true;

    public void setThreadUser(Multiplayer threadUser) {
        this.threadUser = threadUser;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void receive(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        MultiplayerState state = new MultiplayerState(objectInputStream.readInt());
        firstContact = false;
        Debug.l("recv: " + state);
        if (state.getState() == MultiplayerState.SEND_MAP.getState()) {
            Debug.l("reading map: " + state);
            Object o = objectInputStream.readObject();
            gameScreen.setMap((Map) o);
            Debug.l("done reading map: " + state);
            return;
        }
        Debug.l("reading object");
        Object o = objectInputStream.readObject();
    }

    @Override
    public void send(OutputStream in, boolean isFirst) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(in);
        if (isFirst) {
            Debug.l("sent first contact");
            objectOutputStream.writeInt(MultiplayerState.SEND_MAP.getState());
            objectOutputStream.writeObject(gameScreen.getMap());
            objectOutputStream.flush();
            return;
        }
        objectOutputStream.writeInt(MultiplayerState.SEND_DATA.getState());
        objectOutputStream.writeObject(gameScreen.getTank());
        objectOutputStream.flush();
    }

    public void stop() {
        threadUser.interrupt();
    }
}
