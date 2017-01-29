package matt.mvcbattleship;


import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Matt on 10/21/2016.
 */
public class GameListFragment extends ListFragment implements ListAdapter, AdapterView.OnItemClickListener {

    public interface OnGameSelectedListener {
        void onGameSelected(int gameIndex);
    }

    public static GameListFragment newInstance() {
        return new GameListFragment();
    }

    public GameListFragment() {

    }

    private ListView listView;
    private OnGameSelectedListener _gameSelectedListener = null;

    public OnGameSelectedListener getOnGameSelectedListener() {
        return _gameSelectedListener;
    }

    public void setOnGameSelectedListener(OnGameSelectedListener gameSelectedListener) {
        _gameSelectedListener = gameSelectedListener;
    }

    @Override
    public ListView onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(getActivity());
        listView.setAdapter(this);

        return listView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        return Gallery.getInstance().getGameCount();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView gameSummaryView = new TextView(getActivity());

        Game game = Gallery.getInstance().getGame(position);
        if (game.player1 != null) {
            gameSummaryView.setText("Game Name: " + game.name + "\nStatus: " + game._status + "\nJoined");
        }
        else {
            gameSummaryView.setText("Game Name: " + game.name + "\nStatus: " + game._status);
        }
        return gameSummaryView;
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
        if (_gameSelectedListener != null) {
            _gameSelectedListener.onGameSelected(position);
        }
    }

    public void update() {
        listView.invalidateViews();
    }
}
