package matt.mvcbattleship;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Matt on 10/22/2016.
 */
public class Grid implements Serializable{
    private Cell[][] _cells;
    private int _hits;
    private int _misses;
    private int _total;

    public Grid() {
        _cells = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
                _cells[i][j] = cell;
            }
        }
    }

    public void cellHit() {
        _hits++;
        _total++;
    }

    public void cellMiss() {
        _misses++;
        _total++;
    }

    public Cell getCell(int x, int y) {
        return _cells[x][y];
    }

    public Cell[][] getCells() {
        return _cells;
    }

    public int getHits() {
        return _hits;
    }

    public void setHits(int hits) {
        this._hits = hits;
    }

    public int getMisses() {
        return _misses;
    }

    public void setMisses(int misses) {
        this._misses = misses;
    }

    public int getTotal() {
        return _hits+_misses;
    }

    public void setTotal(int total) {
        this._total = total;
    }

    public void generateBoats() {
        int ships = 0;
        while (ships < 17) {
            if (ships == 0) {
                int random1 = ThreadLocalRandom.current().nextInt(1,3);
                if (random1 == 1) {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 10);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 6);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX+1][randomY].get_ship() &&
                            !_cells[randomX+2][randomY].get_ship() &&
                            !_cells[randomX+3][randomY].get_ship() &&
                            !_cells[randomX+4][randomY].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX+1][randomY].set_ship(true);
                        _cells[randomX+2][randomY].set_ship(true);
                        _cells[randomX+3][randomY].set_ship(true);
                        _cells[randomX+4][randomY].set_ship(true);
                        ships += 5;
                    }
                    else {

                    }
                }
                else {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 6);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 10);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX][randomY+1].get_ship() &&
                            !_cells[randomX][randomY+2].get_ship() &&
                            !_cells[randomX][randomY+3].get_ship() &&
                            !_cells[randomX][randomY+4].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX][randomY+1].set_ship(true);
                        _cells[randomX][randomY+2].set_ship(true);
                        _cells[randomX][randomY+3].set_ship(true);
                        _cells[randomX][randomY+4].set_ship(true);
                        ships += 5;
                    }
                    else {

                    }
                }
            }
            if (ships == 5) {
                int random1 = ThreadLocalRandom.current().nextInt(1,3);
                if (random1 == 1) {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 10);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 7);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX+1][randomY].get_ship() &&
                            !_cells[randomX+2][randomY].get_ship() &&
                            !_cells[randomX+3][randomY].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX+1][randomY].set_ship(true);
                        _cells[randomX+2][randomY].set_ship(true);
                        _cells[randomX+3][randomY].set_ship(true);
                        ships += 4;
                    }
                    else {

                    }
                }
                else {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 7);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 10);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX][randomY+1].get_ship() &&
                            !_cells[randomX][randomY+2].get_ship() &&
                            !_cells[randomX][randomY+3].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX][randomY+1].set_ship(true);
                        _cells[randomX][randomY+2].set_ship(true);
                        _cells[randomX][randomY+3].set_ship(true);
                        ships += 4;
                    }
                    else {

                    }
                }
            }
            if (ships == 9) {
                int random1 = ThreadLocalRandom.current().nextInt(1,3);
                if (random1 == 1) {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 10);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 8);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX+1][randomY].get_ship() &&
                            !_cells[randomX+2][randomY].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX+1][randomY].set_ship(true);
                        _cells[randomX+2][randomY].set_ship(true);
                        ships += 3;
                    }
                    else {

                    }
                }
                else {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 8);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 10);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX][randomY+1].get_ship() &&
                            !_cells[randomX][randomY+2].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX][randomY+1].set_ship(true);
                        _cells[randomX][randomY+2].set_ship(true);
                        ships += 3;
                    }
                    else {

                    }
                }
            }
            if (ships == 12) {
                int random1 = ThreadLocalRandom.current().nextInt(1,3);
                if (random1 == 1) {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 10);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 8);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX+1][randomY].get_ship() &&
                            !_cells[randomX+2][randomY].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX+1][randomY].set_ship(true);
                        _cells[randomX+2][randomY].set_ship(true);
                        ships += 3;
                    }
                    else {

                    }
                }
                else {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 8);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 10);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX][randomY+1].get_ship() &&
                            !_cells[randomX][randomY+2].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX][randomY+1].set_ship(true);
                        _cells[randomX][randomY+2].set_ship(true);
                        ships += 3;
                    }
                    else {

                    }
                }
            }
            if (ships == 15) {
                int random1 = ThreadLocalRandom.current().nextInt(1,3);
                if (random1 == 1) {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 10);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 9);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX+1][randomY].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX+1][randomY].set_ship(true);
                        ships += 5;
                    }
                    else {

                    }
                }
                else {
                    int randomY = ThreadLocalRandom.current().nextInt(0, 9);
                    int randomX = ThreadLocalRandom.current().nextInt(0, 10);
                    if (!_cells[randomX][randomY].get_ship() &&
                            !_cells[randomX][randomY+1].get_ship()) {
                        _cells[randomX][randomY].set_ship(true);
                        _cells[randomX][randomY+1].set_ship(true);
                        ships += 5;
                    }
                    else {

                    }
                }
            }
        }
    }
}
