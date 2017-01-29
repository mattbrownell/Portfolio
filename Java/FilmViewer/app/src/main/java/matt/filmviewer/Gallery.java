package matt.filmviewer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 12/2/2016.
 */
public class Gallery implements Serializable{
    private static Gallery _Instance = null;
    List<Film> movies = new ArrayList<>();

    public static Gallery getInstance() {
        if (_Instance == null) {
            _Instance = new Gallery();
        }
        return _Instance;
    }

    public void addMovie(JSONObject movie) throws JSONException {
        Film film = new Film(movie);
        movies.add(film);
    }
}
