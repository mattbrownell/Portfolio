package matt.mvcbattleship;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 10/21/2016.
 */
public class Gallery implements Serializable{
    private static Gallery _Instance = null;
    List<Game> _games = new ArrayList<Game>();
    List<String> gameIds = new ArrayList<String>();
    List<String> playerIds = new ArrayList<String>();
    private int _currentGame;
    public String id = null;
    public String playerId = null;
    String filename = "data";
    FileInputStream inputStream;
    FileOutputStream outputStream;

    public static Gallery getInstance() {
        if (_Instance == null) {
            _Instance = new Gallery();
        }
        return _Instance;
    }

    private Gallery () {
        _currentGame = 0;
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

    JSONArray saveGames(Context context) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < gameIds.size(); i++) {
            String id = gameIds.get(i);
            String playerId = playerIds.get(i);
            JSONObject obj = new JSONObject();
            obj.put("id", id);
            obj.put("playerId", playerId);
            array.put(obj);
        }
        return array;
    }

    void loadGames(JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (!gameIds.contains(obj.getString("id"))) {
                gameIds.add(obj.getString("id"));
                playerIds.add(obj.getString("playerId"));
            }
        }
    }

    Game findGame(String id) {
        for (Game game : _games) {
            if (game.id.equals(id)) {
                return game;
            }
        }
        return null;
    }

    void addNewGame(JSONObject jsonObject) throws JSONException {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String status = jsonObject.getString("status");
        Game game = new Game(id, name, status);
        _games.add(game);
        if (this.id != null) {
            if (this.id.equals(id)) {
                game.player1 = playerId;
            }
        }
    }

    Game getGame (int index) {
        return _games.get(index);
    }
}
