package game.engine;

import game.engine.entities.Sprite;
import game.interfaces.Drawable;
import game.interfaces.Entity;
import game.interfaces.Logic;
import game.utils.DisplayList;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class MapDrawerAdaptor implements Drawable, Logic, Entity {

    Map map;
    private DisplayList mapDisplay;
    private ArrayList<Sprite> sprites;

    public MapDrawerAdaptor(Map map) {
        this.map = map;
        sprites = MapBindings.getSingleton().getSpriteArrayList();
    }

    @Override
    public void draw() {
        mapDisplay.gl();
    }

    @Override
    public void tick() {

    }

    @Override
    public void load() {
        sprites.forEach(Sprite::load);

        final MapBindings singleton = MapBindings.getSingleton();
        mapDisplay = new DisplayList(() -> {
            for (int i = 0; i < Map.WIDTH; i++)
                for (int j = 0; j < Map.HEIGHT; j++) {
                    final byte element = map.getElement(i, j);
                    Sprite sprite = singleton.getSpriteForMapID(element);
                    if (sprite == null)
                        continue;
                    sprite.setSprite(singleton.getSpriteSpriteForMapID(element));
                    glPushMatrix();
                    glTranslated((double) i, (double) -j, 0);
                    sprite.draw();
                    glPopMatrix();
                }
        });
    }

    @Override
    public void unload() {
        sprites.forEach(Sprite::unload);
    }
}
