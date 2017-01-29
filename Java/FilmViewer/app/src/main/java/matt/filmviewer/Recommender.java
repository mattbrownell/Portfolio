package matt.filmviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Matt on 12/2/2016.
 */
public class Recommender extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener, AdapterView.OnItemClickListener {
    private RatingBar ratingBar;
    private Spinner spinner;
    private EditText editText;
    private FilmListView filmListView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        setContentView(R.layout.activity_recommender);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        spinner = (Spinner) findViewById(R.id.spinner);
        editText = (EditText) findViewById(R.id.editText);

        ratingBar.setOnRatingBarChangeListener(this);
        spinner.setOnItemSelectedListener(this);
        editText.setOnEditorActionListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        updateMovies();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateMovies();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.KEYCODE_ENTER) {
            updateMovies();
        }
        return false;
    }

    private void updateMovies() {
        MovieFinder mt = new MovieFinder();

        String genre = spinner.getSelectedItem().toString();
        switch (genre) {
            case "Action" :
                genre = "28";
                break;
            case "Adventure" :
                genre = "12";
                break;
            case "Animation" :
                genre = "16";
                break;
            case "Comedy" :
                genre = "35";
                break;
            case "Crime" :
                genre = "80";
                break;
            case "Documentary" :
                genre = "99";
                break;
            case "Drama" :
                genre = "18";
                break;
            case "Family" :
                genre = "10751";
                break;
            case "Fantasy" :
                genre = "14";
                break;
            case "History" :
                genre = "36";
                break;
            case "Horror" :
                genre = "27";
                break;
            case "Music" :
                genre = "10402";
                break;
            case "Mystery" :
                genre = "9648";
                break;
            case "Romance" :
                genre = "10749";
                break;
            case "Science Fiction" :
                genre = "878";
                break;
            case "TV Film" :
                genre = "10770";
                break;
            case "Thriller" :
                genre = "53";
                break;
            case "War" :
                genre = "10752";
                break;
            case "Western" :
                genre = "37";
                break;
        }

        JSONObject movieArray = new JSONObject();

        try {
            movieArray = mt.execute(genre, Integer.parseInt(editText.getText().toString()), ratingBar.getRating()).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        List<Film> films = new ArrayList<>();
        JSONArray jsonArray;

        try {
            jsonArray = movieArray.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                Film  film = new Film(jsonObject2);
                films.add(film);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.removeView(filmListView);

        filmListView = new FilmListView(this, films);
        filmListView.setOnItemClickListener((AdapterView.OnItemClickListener) context);

        linearLayout.addView(filmListView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        int orien = this.getResources().getConfiguration().orientation;
        if (orien == 1) {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
        else {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        }

        filmListView.requestLayout();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent filmViewIntent = new Intent();
        filmViewIntent.setClass(getApplicationContext(), FilmView.class);
        filmViewIntent.putExtra("id", filmListView.getID(position));
        startActivity(filmViewIntent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
