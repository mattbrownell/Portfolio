package matt.mvcbattleship;

import java.io.Serializable;

/**
 * Created by Matt on 10/21/2016.
 */
public class Game implements Serializable{
    private int _playerTurn;
    private String _status;
    private Grid _grid1;
    private Grid _grid2;

    public Game() {
        _playerTurn = 1;
        _status = "In Progress";
        _grid1 = new Grid();
        _grid2 = new Grid();
        _grid1.generateBoats();
        _grid2.generateBoats();
    }

    public Grid get_grid1() {
        return _grid1;
    }

    public void set_grid1(Grid _grid1) {
        this._grid1 = _grid1;
    }

    public Grid get_grid2() {
        return _grid2;
    }

    public void set_grid2(Grid _grid2) {
        this._grid2 = _grid2;
    }

    public int gameWon() {
        if (_grid1.getHits() == 17) {
            return 2;
        }
        else if (_grid2.getHits() == 17) {
            return 1;
        }
        else
            return 0;

    }

    void set_playerTurn(int turn) {
        _playerTurn = turn;
    }

    int get_playerTurn() {
        return _playerTurn;
    }

    void set_status(String status) {
        _status = status;
    }

    String get_status() {
        return _status;
    }
}
