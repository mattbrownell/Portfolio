package matt.mvcbattleship;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Matt on 10/22/2016.
 */
public class Grid implements Serializable {
    private Cell[][] _cells;

    public Grid() {
        _cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
                _cells[i][j] = cell;
            }
        }
    }

    public Cell getCell(int x, int y) {
        return _cells[x][y];
    }
}