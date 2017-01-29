package matt.mvcbattleship;

import java.io.Serializable;

/**
 * Created by Matt on 10/21/2016.
 */
public class Cell implements Serializable {
    public String _status = "NONE";

    public void setShipStatus(String status) {
        _status = status;
    }
}
