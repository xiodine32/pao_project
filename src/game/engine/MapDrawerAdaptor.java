package game.engine;

import game.interfaces.Drawable;
import game.interfaces.Logic;
import game.utils.DisplayList;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class MapDrawerAdaptor implements Drawable, Logic {

    Map map;

    private DisplayList mapDisplay;

    private Integer displayID = null;

    public MapDrawerAdaptor(Map map) {
        this.map = map;
    }

    @Override
    public void draw() {
        if (mapDisplay == null) {
            mapDisplay = new DisplayList(() -> {
                for (int i = 0; i < Map.WIDTH; i++)
                    for (int j = 0; j < Map.HEIGHT; j++) {

                    }
            });
        }
        mapDisplay.gl();
    }

    @Override
    public void tick() {

    }
}
