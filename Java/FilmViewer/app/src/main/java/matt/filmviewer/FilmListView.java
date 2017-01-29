package matt.filmviewer;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Matt on 12/2/2016.
 */
public class FilmListView extends ListView implements ListAdapter {

    private List<Film> _movies;
    private OnItemSelectedListener _itemSelectedListener = null;

    public FilmListView(Context context, List<Film> movies) {
        super(context);
        _movies = movies;
        this.setAdapter(this);
        this.setBackground(getResources().getDrawable(R.drawable.gradient_background));
    }

    public int getID(int position) {
        return _movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout root = new LinearLayout(getContext());
        ImageView imageView = new ImageView(getContext());
        HTTPBMP task2 = new HTTPBMP();
        Bitmap bmp = null;
        try {
            bmp = task2.execute(_movies.get(position).getPosterPath()).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bmp);
        root.addView(imageView);

        TextView textView = new TextView(getContext());
        textView.setText("   "  + _movies.get(position).getName() +
                "\n   " + _movies.get(position).getVoteAverage() +
                "\n   " + _movies.get(position).getReleaseDate());
        textView.setTextColor(Color.WHITE);
        root.addView(textView);

        return root;
    }

    @Override
    public int getCount() {
        return _movies.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
