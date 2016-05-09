package game.interfaces;

/**
 * pao_project - xiodine.
 * 4/3/2016
 */
public interface Engine {
    void run(Screen startScreen);

    void stopRunning();

    void init();

    void dispose();

    void changeScreen(Screen newScreen);

}
