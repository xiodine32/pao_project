package game.engine;

import game.engine.entities.Sprite;
import game.interfaces.Drawable;
import game.interfaces.Entity;
import game.interfaces.Logic;
import game.interfaces.Texture;
import game.utils.CSVSingleton;
import game.utils.DisplayList;
import game.utils.TextureWrapper;

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
        sprites = new ArrayList<>();
        sprites.add(new Sprite("tiles", "bricks"));
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
        mapDisplay = new DisplayList(() -> {
            for (int i = 0; i < Map.WIDTH; i++)
            for (int j = 0; j < Map.HEIGHT; j++) {
                final byte element = map.getElement(i, j);
                if (element >= 1 && element <= 4) {
                    Sprite sprite = sprites.get(0);
                    sprite.setSprite(element - 1);
                    glPushMatrix();
                    glTranslated((double)i, (double)j, 0);
                    sprite.draw();
                    glPopMatrix();
                }
            }
        });
    }

    @Override
    public void unload() {
        sprites.forEach(Sprite::unload);
    }
}
