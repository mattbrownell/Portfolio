package matt.filmviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Matt on 12/2/2016.
 */
public class FilmView extends AppCompatActivity {
    JSONObject movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_filmlayout);

        final int id = (Integer) getIntent().getExtras().get("id");

        MovieData task = new MovieData();
        movie = null;
        try {
            movie = task.execute(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        HTTPBMP task2 = new HTTPBMP();
        Bitmap bmp = null;
        try {
            bmp = task2.execute(movie.getString("poster_path")).get();
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        String title = null;
        List<String> genres = null;
        double rating = 0;
        String release = null;
        String synopsis = null;
        try {
            title = movie.getString("title");
            genres = new ArrayList<>();
            JSONArray jsonArray = movie.getJSONArray("genres");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String name = jsonObject2.getString("name");
                genres.add(name);
            }
            rating = movie.getDouble("vote_average");
            release = movie.getString("release_date");
            synopsis = movie.getString("overview");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView titleText = (TextView)findViewById(R.id.title);
        titleText.setText(title);

        TextView genreText = (TextView)findViewById(R.id.genre);
        genreText.setText(genres.get(0));

        TextView ratingText = (TextView)findViewById(R.id.rating);
        ratingText.setText(rating + "");

        TextView releaseText = (TextView)findViewById(R.id.release);
        releaseText.setText(release);

        TextView synopsisText = (TextView)findViewById(R.id.synopsis);
        synopsisText.setText("Overview: " + synopsis);

        ImageView posterImage = (ImageView)findViewById(R.id.imageView);
        posterImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, 600, 900, false));
        posterImage.setMaxWidth(250);
        posterImage.setMaxHeight(200);

        Button save = (Button) findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean flag = false;
                    for (Film film : Gallery.getInstance().movies) {
                        if (movie.getInt("id") == film.getId()) {
                            Gallery.getInstance().movies.remove(film);
                            flag = true;
                        }
                    }
                    if (flag == false)
                        Gallery.getInstance().addMovie(movie);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Save Gallery to IO or die trying.
                try {
                    FileOutputStream outputStream = openFileOutput("FilmViewerData", Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(Gallery.getInstance().movies);
                    oos.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }

            }
        });

        Button streaming = (Button) findViewById(R.id.button);
        streaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filmStreamIntent = new Intent();
                filmStreamIntent.setClass(getApplicationContext(), StreamView.class);
                try {
                    filmStreamIntent.putExtra("id", movie.getInt("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(filmStreamIntent);
            }
        });
    }
}
