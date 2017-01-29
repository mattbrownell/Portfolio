package matt.filmviewer;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Matt on 12/13/2016.
 */
public class StreamView extends AppCompatActivity implements ListAdapter {

    private ListView listView;
    JSONArray combined = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        listView = new ListView(this);
        listView.setAdapter(this);
        setContentView(listView);

        String id = (String) getIntent().getExtras().get("id").toString();

        GuideboxFindID task = new GuideboxFindID();
        JSONObject id2 = null;
        try {
            id2 = task.execute("search/movie/id/themoviedb/" + id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        String id3 = null;
        try {
            id3 = Integer.toString(id2.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GuideboxFindID task2 = new GuideboxFindID();
        JSONObject sources = null;
        try {
            sources = task2.execute("movie/" + id3).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        JSONArray subscriptionSources = null;
        JSONArray purchaseSources = null;

        try {
            subscriptionSources = sources.getJSONArray("subscription_web_sources");
            purchaseSources = sources.getJSONArray("purchase_web_sources");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setBackground(getResources().getDrawable(R.drawable.gradient_background));

        combined = purchaseSources;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        if (combined != null)
            return combined.length();
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        JSONObject jsonObject = null;
        String display = "";
        String link = "";
        try {
            jsonObject = combined.getJSONObject(position);
            display = jsonObject.getString("display_name");
            link = jsonObject.getString("link");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView displayName = new TextView(getApplicationContext());
        displayName.setText("\n   " + display);
        displayName.setTextColor(Color.WHITE);

        Button button = new Button(getApplicationContext());
        button.setBackground(getResources().getDrawable(R.drawable.gradient_button));
        button.setText("OPEN");
        final String finalLink = link;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalLink));
                startActivity(browserIntent);
            }
        });
        linearLayout.addView(displayName, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        linearLayout.addView(button, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 2));
        return linearLayout;
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
}
