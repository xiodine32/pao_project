package game.screen;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public interface Screen {
    void load();

    void tick();

    void draw();

    void unload();
}
