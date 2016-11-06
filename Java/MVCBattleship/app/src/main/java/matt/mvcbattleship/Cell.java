package matt.mvcbattleship;

import java.io.Serializable;

/**
 * Created by Matt on 10/21/2016.
 */
public class Cell implements Serializable {
    private boolean _ship = false;
    private int _status = 0;

    public void set_ship(boolean ship) {
        _ship = ship;
    }

    public boolean get_ship() {
        return _ship;
    }

    public void set_status(int status) {
        _status = status;
    }

    public int get_status() {
        return _status;
    }
}
