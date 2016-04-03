package game.engine;

import game.screen.Screen;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public interface Engine {
    void run(Screen startScreen);

    void init();

    void dispose();

    void changeScreen(Screen newScreen);
}
