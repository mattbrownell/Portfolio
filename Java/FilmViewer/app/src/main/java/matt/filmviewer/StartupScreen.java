package matt.filmviewer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class StartupScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_startup);

        try {
            FileInputStream inputStream = openFileInput("FilmViewerData");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            List<Film> films = (ArrayList<Film>) ois.readObject();
            Gallery.getInstance().movies = films;
            ois.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        final GridLayout grid = (GridLayout) findViewById(R.id.grid);
        final int finalCount = Gallery.getInstance().movies.size();

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsyncTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (grid.getChildCount() < finalCount) {
                            Bitmap bmp = null;
                            try {
                                HTTPBMP task2 = new HTTPBMP();
                                try {
                                    bmp = task2.execute(Gallery.getInstance().movies.get(grid.getChildCount()).getPosterPath()).get();
                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ImageView imageView = new ImageView(getApplicationContext());
                            imageView.setImageBitmap(bmp);
                            grid.addView(imageView);
                        }
                    }
                });
            }
        };
        timer.schedule(doAsyncTask, 0, 200);

        findViewById(R.id.list_films).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filmViewIntent = new Intent();
                filmViewIntent.setClass(getApplicationContext(), SavedFilms.class);
                startActivity(filmViewIntent);
            }
        });

        findViewById(R.id.recomend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recommenderIntent = new Intent();
                recommenderIntent.setClass(getApplicationContext(), Recommender.class);
                startActivity(recommenderIntent);
            }
        });

    }
}
