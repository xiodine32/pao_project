package game.engine;

import game.interfaces.Drawable;
import game.interfaces.Entity;
import game.interfaces.Logic;
import game.interfaces.Texture;
import game.utils.CSVSingleton;
import game.utils.DisplayList;
import game.utils.TextureWrapper;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class MapDrawerAdaptor implements Drawable, Logic, Entity {

    Map map;
    private DisplayList mapDisplay;
    private Texture textures;

    public MapDrawerAdaptor(Map map) {
        this.map = map;
        String path = CSVSingleton.getInstance().get("tiles", "test").split(",")[2];
        textures = TextureWrapper.loadTexture(path);
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
        mapDisplay = new DisplayList(() -> {
            textures.bind();
            glBegin(GL_QUADS);
            for (int i = 0; i < Map.WIDTH; i++)
                for (int j = 0; j < Map.HEIGHT; j++) {
                    glTexCoord2d((i + 0.5) / Map.WIDTH, (j + 0.5) / Map.HEIGHT);
                    glVertex2d(i, j);
                    glTexCoord2d((i + 0.5) / Map.WIDTH, (j + 0.5) / Map.HEIGHT);
                    glVertex2d(i, j + 1);
                    glTexCoord2d((i + 0.5) / Map.WIDTH, (j + 0.5) / Map.HEIGHT);
                    glVertex2d(i + 1, j + 1);
                    glTexCoord2d((i + 0.5) / Map.WIDTH, (j + 0.5) / Map.HEIGHT);
                    glVertex2d(i + 1, j);
                }
            glEnd();
        });
    }

    @Override
    public void unload() {

    }
}
