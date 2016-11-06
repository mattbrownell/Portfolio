package matt.mvcbattleship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 10/21/2016.
 */
public class Gallery implements Serializable{
    private static Gallery _Instance = null;
    List<Game> _games = new ArrayList<Game>();
    private int _currentGame;

    public static Gallery getInstance() {
        if (_Instance == null) {
            _Instance = new Gallery();
        }
        return _Instance;
    }

    private Gallery () {
        _currentGame = -1;
    }

    public void deleteGame() {
        _games.remove(_currentGame);
    }

    int getGameCount () {
        return _games.size();
    }

    public int get_currentGame() {
        return _currentGame;
    }

    public void set_currentGame(int currentGame) {
        _currentGame = currentGame;
    }

    List<Game> saveGames() {
        return _games;
    }

    void loadGame(List<Game> games) {
        _games = games;
    }

    void addNewGame() {
        _games.add(new Game());
    }

    Game getGame (int index) {
        return _games.get(index);
    }
}
