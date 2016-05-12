package game.engine;

import java.util.Random;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class Map {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    private byte[][] map = new byte[WIDTH][HEIGHT];

    public Map() {
        generateNewMap();
    }

    public void generateNewMap() {
        generateSeededMap(System.nanoTime());
    }

    private void generateSeededMap(long seed) {
        Random random = new Random(seed);
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++) {
                if (i == 0 || j == 0 || i == WIDTH - 1 || j == HEIGHT - 1) {
                    this.map[i][j] = 1;
                    continue;
                }
                this.map[i][j] = (byte) (random.nextInt(10) == 5 ? 2 : 0);
            }
    }

    public byte getElement(int x, int y) {

        if (x < 0 || y < 0)
            return -1;

        if (x >= WIDTH || y >= HEIGHT)
            return -1;

        return map[x][y];
    }
}
