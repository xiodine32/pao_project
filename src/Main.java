import game.engine.SimpleEngine;
import game.interfaces.Engine;
import game.screen.MainMenuScreen;

/**
 * pao_project - xiodine.
 * 3/28/2016
 */
public class Main {
    public static void main(String[] args) {
        Engine engine = new SimpleEngine();
        engine.init();
        engine.run(new MainMenuScreen());
        engine.dispose();
    }
}
