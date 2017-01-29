package matt.filmviewer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 12/19/2016.
 */
public class Film implements Serializable {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    private int id;
    private String name;
    private String posterPath;
    private List<String> genre;
    private String releaseDate;
    private double voteAverage;

    public Film(JSONObject jsonObject) throws JSONException {
        genre = new ArrayList<String>();
        id = jsonObject.getInt("id");
        name = jsonObject.getString("title");
        posterPath = jsonObject.getString("poster_path");
        releaseDate = jsonObject.getString("release_date");
        voteAverage = jsonObject.getDouble("vote_average");
    }
}
