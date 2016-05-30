package game.engine;

import game.engine.entities.Sprite;

import java.util.ArrayList;

/**
 * Created by NeaguR on 30.05.2016.
 * PROJECT pao_project
 * <p>
 * Contains all the code related to the map.
 */
public class MapBindings {

    /* SINGLETON CODE */

    static private MapBindings instance = new MapBindings();
    private ArrayList<Sprite> spriteArrayList = new ArrayList<>();

    /* CLASS CODE */

    private MapBindings() {
        spriteArrayList.add(new Sprite("tiles", "bricks"));
    }

    public static MapBindings getSingleton() {
        return instance;
    }

    public ArrayList<Sprite> getSpriteArrayList() {
        return spriteArrayList;
    }

    public Sprite getSpriteAtIndex(int index) {
        if (index == -1)
            return null;
        return spriteArrayList.get(index);
    }

    public Sprite getSpriteForMapID(byte mapID) {
        final int spriteIndexForMapID = getSpriteIndexForMapID(mapID);
        return getSpriteAtIndex(spriteIndexForMapID);
    }


    public int getSpriteIndexForMapID(byte mapID) {
        if (mapID >= 1 && mapID <= 4) {
            return 0;
        }
        return -1;
    }

    public int getSpriteSpriteForMapID(byte mapID) {
        if (mapID >= 1 && mapID <= 4) {
            return mapID - 1;
        }
        return 0;
    }

    public boolean doesCollideWithMapID(byte mapID) {
        return mapID != 0;
    }
}
