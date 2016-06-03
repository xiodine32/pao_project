package game.multiplayer;

import game.engine.Map;
import game.engine.entities.Bullet;
import game.engine.entities.BulletManager;
import game.engine.entities.Tank;
import game.interfaces.Multiplayer;
import game.interfaces.MultiplayerUser;
import game.screen.GameScreen;
import game.utils.Debug;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

        // server received data
        if (state.getState() == MultiplayerState.SEND_ME.getState()) {
            Tank player = (Tank) objectInputStream.readObject();
            ArrayList bullets = (ArrayList) objectInputStream.readObject();
            ArrayList<Bullet> newAdded = new ArrayList<>();
            for (Object obj : bullets) {
                Bullet bullet = (Bullet) obj;
                bullet.setUID(uid);
                newAdded.add(bullet);
            }

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
                ArrayList<Bullet> newBullets = new ArrayList<>(BulletManager.getInstance().getOtherBullets().stream().filter(o -> o.getUID() != uid).collect(Collectors.toList()));
                newBullets.addAll(newAdded);
                BulletManager.getInstance().setOtherBullets(newBullets);
            }
            return;
        }

        // client received data
        if (state.getState() == MultiplayerState.SEND_OTHERS.getState()) {
            ArrayList<Tank> others = new ArrayList<>();
            Tank server = (Tank) objectInputStream.readObject();
            ArrayList data = (ArrayList) objectInputStream.readObject();


            int clientUID = objectInputStream.readInt();

            ArrayList dataBullets = (ArrayList) objectInputStream.readObject();
            ArrayList<Bullet> bullets = new ArrayList<>();
            for (Object obj : dataBullets) {
                final Bullet bullet = (Bullet) obj;
                if (bullet.getUID() != clientUID)
                    bullets.add(bullet);
            }

            for (Object obj : data) {
                final Tank tank = (Tank) obj;
                if (tank.getUID() == clientUID)
                    continue;
                others.add(tank);
            }
            others.add(server);
            synchronized (GameScreen.class) {
                gameScreen.otherTanks = others;
                BulletManager.getInstance().setOtherBullets(bullets);
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
            objectOutputStream.writeObject(BulletManager.getInstance().getBullets());
            objectOutputStream.flush();
            return;
        }
        // server
        objectOutputStream.writeInt(MultiplayerState.SEND_OTHERS.getState());
        objectOutputStream.writeObject(tank);
        objectOutputStream.writeObject(gameScreen.otherTanks);
        objectOutputStream.writeInt(uid);
        ArrayList<Bullet> others = new ArrayList<>(BulletManager.getInstance().getOtherBullets().stream().filter(bullet -> bullet.getUID() != uid).collect(Collectors.toList()));
        others.addAll(BulletManager.getInstance().getBullets());
        objectOutputStream.writeObject(others);
        objectOutputStream.flush();
    }

    public void stop() {
        threadUser.interrupt();
    }
}
