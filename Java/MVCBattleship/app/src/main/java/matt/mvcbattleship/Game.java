package matt.mvcbattleship;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Matt on 10/21/2016.
 */
public class Game implements Serializable{
    public String id;
    public String name;
    public String player1;
    public String winner;
    public String _status;
    public int missilesLaunched;
    private Grid _grid1;
    private Grid _grid2;

    public Game(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.player1 = null;
        this.winner = "IN_PROGRESS";
        _status = status;
        this.missilesLaunched = 0;
        _grid1 = new Grid();
        _grid2 = new Grid();
    }

    public void setGame(String id, String name, String winner, String status, int missilesLaunched) {
        this.id = id;
        this.name = name;
        this.winner = winner;
        this._status = status;
        this.missilesLaunched = missilesLaunched;
    }

    public Grid getGrid1() {
        return _grid1;
    }

    public Grid getGrid2() {
        return _grid2;
    }

    void setStatus(String status) {
        _status = status;
    }

    public void setUpGrids(String data2) {
        try {
            JSONObject obj = new JSONObject(data2);
            JSONArray playerBoard = obj.getJSONArray("playerBoard");
            JSONArray opponentBoard = obj.getJSONArray("opponentBoard");

            for (int i = 0; i < playerBoard.length(); i++) {
                JSONObject cell = playerBoard.getJSONObject(i);
                int x = cell.getInt("xPos");
                int y = cell.getInt("yPos");
                String status = cell.getString("status");
                _grid1.getCell(x,y).setShipStatus(status);
            }

            for (int i = 0; i < opponentBoard.length(); i++) {
                JSONObject cell = opponentBoard.getJSONObject(i);
                int x = cell.getInt("xPos");
                int y = cell.getInt("yPos");
                String status = cell.getString("status");
                _grid2.getCell(x,y).setShipStatus(status);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int dog = 0;
    }
}
