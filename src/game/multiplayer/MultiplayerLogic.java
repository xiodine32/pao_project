package game.multiplayer;

import game.engine.Map;
import game.engine.entities.Tank;
import game.interfaces.Multiplayer;
import game.interfaces.MultiplayerUser;
import game.screen.GameScreen;
import game.utils.Debug;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Xiodine on 31/05/2016.
 * pao_project
 */
public class MultiplayerLogic implements MultiplayerUser {
    public final static MultiplayerLogic singleton = new MultiplayerLogic();
    private GameScreen gameScreen;
    private Multiplayer threadUser;

    public void setThreadUser(Multiplayer threadUser) {
        this.threadUser = threadUser;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void receive(InputStream inputStream, int uid) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        MultiplayerState state = new MultiplayerState(objectInputStream.readInt());
        if (state.getState() == MultiplayerState.SEND_MAP.getState()) {
            Debug.l("reading map: " + state);
            Object o = objectInputStream.readObject();
            int clientUID = objectInputStream.readInt();
            gameScreen.getTank().setUID(clientUID);
            gameScreen.setMap((Map) o);
            Debug.l("done reading map");
            return;
        }

        if (state.getState() == MultiplayerState.SEND_ME.getState()) {
            Tank player = (Tank) objectInputStream.readObject();
            player.setUID(uid);

            boolean found = false;
            ArrayList<Tank> tanks = new ArrayList<>(gameScreen.otherTanks);

            for (Tank tank : tanks) {
                if (tank.getUID() == uid) {
                    tanks.remove(tank);
                    tanks.add(player);
                    found = true;
                    break;
                }
            }
            if (!found)
                tanks.add(player);

            synchronized (GameScreen.class) {
                gameScreen.otherTanks = tanks;
            }
            return;
        }

        if (state.getState() == MultiplayerState.SEND_OTHERS.getState()) {
            ArrayList<Tank> others = new ArrayList<>();
            Tank server = (Tank) objectInputStream.readObject();
            ArrayList data = (ArrayList) objectInputStream.readObject();
            int clientUID = objectInputStream.readInt();
            for (Object obj : data) {
                final Tank tank = (Tank) obj;
                if (tank.getUID() == clientUID)
                    continue;
                others.add(tank);
            }
            others.add(server);
            synchronized (GameScreen.class) {
                gameScreen.otherTanks = others;
            }
            return;
        }
        Debug.l("don't know what to do with state: " + state);
    }

    @Override
    public void send(OutputStream in, boolean isFirst, int uid) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(in);
        if (isFirst) {
            Debug.l("sent first contact");
            objectOutputStream.writeInt(MultiplayerState.SEND_MAP.getState());
            objectOutputStream.writeObject(gameScreen.getMap());
            objectOutputStream.writeInt(uid);
            objectOutputStream.flush();
            return;
        }

        final Tank tank = gameScreen.getTank();

        if (!threadUser.isServer()) {
            objectOutputStream.writeInt(MultiplayerState.SEND_ME.getState());
            objectOutputStream.writeObject(tank);
            objectOutputStream.flush();
            return;
        }
        // server
        objectOutputStream.writeInt(MultiplayerState.SEND_OTHERS.getState());
        objectOutputStream.writeObject(tank);
        objectOutputStream.writeObject(gameScreen.otherTanks);
        objectOutputStream.writeInt(uid);
        objectOutputStream.flush();
    }

    public void stop() {
        threadUser.interrupt();
    }
}
