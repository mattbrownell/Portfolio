package matt.filmviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

/**
 * Created by Matt on 12/4/2016.
 */
public class SavedFilms extends AppCompatActivity implements AdapterView.OnItemClickListener {
    FilmListView filmListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        filmListView = new FilmListView(this, Gallery.getInstance().movies);
        filmListView.setOnItemClickListener(this);

        relativeLayout.addView(filmListView);

        setContentView(relativeLayout);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent filmViewIntent = new Intent();
        filmViewIntent.setClass(getApplicationContext(), FilmView.class);
        filmViewIntent.putExtra("id", Gallery.getInstance().movies.get(position).getId());
        startActivity(filmViewIntent);
    }
}
