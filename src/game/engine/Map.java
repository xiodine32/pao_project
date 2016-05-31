package game.engine;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by xiodine on 5/12/16.
 * pao_project
 */
public class Map implements Serializable {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;
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
                if (i > 5 && j > 5) {
                    this.map[i][j] = (byte) (random.nextInt(20) == 5 ? 2 : 0);
                    if (this.map[i][j] == 0)
                        this.map[i][j] = (byte) (random.nextInt(20) == 5 ? 3 : 0);
                    if (this.map[i][j] == 0)
                        this.map[i][j] = (byte) (random.nextInt(20) == 5 ? 4 : 0);
                }
            }
//        map[1][1] = 1;

    }

    public byte getElement(int x, int y) {

        if (x < 0 || y < 0)
            return -1;

        if (x >= WIDTH || y >= HEIGHT)
            return -1;

        return map[x][y];
    }
}
