// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity for handling the Gallery ListView.
 */
public class GalleryActivity extends AppCompatActivity implements ListAdapter, AdapterView.OnItemClickListener {

    static final int PAINT_REQUEST = 1;
    private ListView galleryListView;
    String filename = "data";
    FileOutputStream outputStream;
    FileInputStream inputStream;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryListView = new ListView(this);
        try {
            load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        galleryListView.setAdapter(this);
        setContentView(galleryListView);

        galleryListView.setOnItemClickListener(this);
        if (Gallery.getInstance().getDrawingCount()==0)
            Gallery.getInstance().addNewDrawing();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        return Gallery.getInstance().getDrawingCount();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView drawingSummaryView = new TextView(this);
        Drawing drawing = Gallery.getInstance().getDrawing(position);
        drawingSummaryView.setText("Drawing " + position + " has " + drawing.getStrokeCount() + " strokes");
        return drawingSummaryView;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent showdrawingIntent = new Intent();
        showdrawingIntent.setClass(this, MainActivity.class);
        showdrawingIntent.putExtra("drawing", position);
        startActivityForResult(showdrawingIntent, PAINT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result OK.d.
        if (requestCode == PAINT_REQUEST) {
            Gallery.getInstance().set_currentDrawing(-1);
            galleryListView.invalidateViews();
            try {
                save();
            } catch (FileNotFoundException ie) {
                ie.printStackTrace();
            }
        }
    }

    // Save Gallery to IO or die trying.
    public void save() throws FileNotFoundException {
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(Gallery.getInstance().saveDrawings());
            oos.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    // Load Gallery from IO.
    public void load() throws FileNotFoundException {
        try {
            inputStream = openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            List<Drawing> drawings = (ArrayList<Drawing>) ois.readObject();
            Gallery.getInstance().loadDrawing(drawings);
            ois.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
