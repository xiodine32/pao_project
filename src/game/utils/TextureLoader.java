package game.utils;

/**
 * pao_project - xiodine.
 * 4/10/2016
 */
public class TextureLoader {

    private static TextureLoader singleton = null;

    private TextureLoader() {
    }

    public static TextureLoader getInstance() {
        if (singleton == null)
            singleton = new TextureLoader();
        return singleton;
    }

    public int load(String path) {
        // FIXME: 4/10/2016
        return 0;
    }

    public void unload(int textureID) {
        // FIXME: 4/10/2016
    }
}
