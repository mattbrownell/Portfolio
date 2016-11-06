package matt.mvcbattleship;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Battleship extends AppCompatActivity implements GameListFragment.OnGameSelectedListener, GameFragment.OnChangeMadeListener, GameFragment.NextTurnListener {
    public static int LIST_FRAME_ID = 10;
    public static String LIST_FRAGMENT_TAG = "GameListFragment";
    public static int GAME_FRAME_ID = 11;
    public static String GAME_FRAGMENT_TAG = "GameFragment";
    public GameListFragment _gameListFragment;
    public GameFragment _gameFragment;
    public Button nextTurnButton;
    public boolean phone;
    public LinearLayout rootLayout, listButtonLayout;
    public FrameLayout gameLayout;
    String filename = "data";
    FileOutputStream outputStream;
    FileInputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int i = 0;

        try {
            load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (Gallery.getInstance().getGameCount() == 0) {
            Gallery.getInstance().set_currentGame(0);
            Gallery.getInstance().addNewGame();
        }

        phone = getResources().getBoolean(R.bool.isTab);

        rootLayout = new LinearLayout(this);
        listButtonLayout = new LinearLayout(this);
        listButtonLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout buttonLayout = new LinearLayout(this);
        setContentView(rootLayout);

        int orien = this.getResources().getConfiguration().orientation;
        if (orien == Configuration.ORIENTATION_PORTRAIT && !phone) {
            rootLayout.setOrientation(LinearLayout.VERTICAL);
        }
        else if (orien == Configuration.ORIENTATION_LANDSCAPE && !phone){
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        }

        Button newGameButton = new Button(this);
        newGameButton.setText("New Game");
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gallery.getInstance().addNewGame();
                _gameListFragment.getListView().requestLayout();
                Gallery.getInstance().set_currentGame(Gallery.getInstance().get_currentGame());
            }
        });
        Button deleteGameButton = new Button(this);
        deleteGameButton.setText("End Game");
        deleteGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Gallery.getInstance().getGameCount() != 0) {
                    Gallery.getInstance().deleteGame();
                    _gameListFragment.getListView().requestLayout();
                }
                Gallery.getInstance().set_currentGame(0);
            }
        });
        nextTurnButton = new Button(this);
        nextTurnButton.setText("Next Turn");
        nextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Gallery.getInstance().getGameCount() != 0)
                    _gameFragment.renablePlay();
            }
        });
        //nextTurnButton.setEnabled(false);
        buttonLayout.addView(deleteGameButton,  new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        buttonLayout.addView(newGameButton,  new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        buttonLayout.addView(nextTurnButton,  new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        FrameLayout listLayout = new FrameLayout(this);
        listLayout.setId(LIST_FRAME_ID);
        listButtonLayout.addView(listLayout,  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        listButtonLayout.addView(buttonLayout);


        gameLayout = new FrameLayout(this);
        gameLayout.setId(GAME_FRAME_ID);


        if (orien == Configuration.ORIENTATION_PORTRAIT && !phone) {
            rootLayout.setOrientation(LinearLayout.VERTICAL);
            rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
            rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        }
        else if (orien == Configuration.ORIENTATION_LANDSCAPE && !phone){
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
            rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));
        }
        else if (orien == Configuration.ORIENTATION_PORTRAIT && phone) {
            rootLayout.setOrientation(LinearLayout.VERTICAL);
            rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
            rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0));
        }
        else {
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
            rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }

        FragmentTransaction Transaction = getSupportFragmentManager().beginTransaction();

        _gameListFragment = (GameListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
        _gameFragment = (GameFragment) getSupportFragmentManager().findFragmentByTag(GAME_FRAGMENT_TAG);

        if (_gameListFragment == null) {
            _gameListFragment = new GameListFragment();
            Transaction.add(listLayout.getId(), _gameListFragment, LIST_FRAGMENT_TAG);
        }
        else {
            Transaction.replace(listLayout.getId(), _gameListFragment);
        }
        _gameListFragment.setOnGameSelectedListener(this);
        if (_gameFragment == null) {
            _gameFragment = new GameFragment();
            Transaction.add(gameLayout.getId(), _gameFragment, GAME_FRAGMENT_TAG);
        }
        else {
            Transaction.replace(gameLayout.getId(), _gameFragment);
        }
        _gameFragment.set_onChangeMade(this);
        _gameFragment.setNextTurnListener(this);
        Transaction.commit();
        //if (Gallery.getInstance().getGameCount() != 0)
          //  onGameSelected(0);
    }

    @Override
    public void onBackPressed() {
        int orien = this.getResources().getConfiguration().orientation;
        if (orien == Configuration.ORIENTATION_PORTRAIT && phone) {
            rootLayout.setOrientation(LinearLayout.VERTICAL);
            rootLayout.removeView(listButtonLayout);
            rootLayout.removeView(gameLayout);
            rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
            rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0));
        }
        else {
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
            rootLayout.removeView(listButtonLayout);
            rootLayout.removeView(gameLayout);
            rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3));
            rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    @Override
    public void onGameSelected(int gameIndex) {
        if (Gallery.getInstance().getGameCount() != 0) {
            Gallery.getInstance().set_currentGame(gameIndex);
            _gameFragment.updateView(gameIndex);
            int orien = this.getResources().getConfiguration().orientation;
            if (orien == Configuration.ORIENTATION_PORTRAIT && phone) {
                rootLayout.setOrientation(LinearLayout.VERTICAL);
                rootLayout.removeView(listButtonLayout);
                rootLayout.removeView(gameLayout);
                rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0));
                rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
            }
            else if (phone){
                rootLayout.setOrientation(LinearLayout.HORIZONTAL);
                rootLayout.removeView(listButtonLayout);
                rootLayout.removeView(gameLayout);
                rootLayout.addView(listButtonLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0));
                rootLayout.addView(gameLayout, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3));
            }
        }
    }

    @Override
    public void OnChangeMade() {
        _gameListFragment.update();
        nextTurnButton.setEnabled(true);
        try {
            save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnNextTurn() {
        nextTurnButton.performClick();
    }

    // Save Gallery to IO or die trying.
    public void save() throws FileNotFoundException {
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(Gallery.getInstance().saveGames());
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
            List<Game> games = (ArrayList<Game>) ois.readObject();
            Gallery.getInstance().loadGame(games);
            ois.close();
            Gallery.getInstance().set_currentGame(0);
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
