package matt.mvcbattleship;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class Battleship extends AppCompatActivity implements GameListFragment.OnGameSelectedListener, GameFragment.OnChangeMadeListener {
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
    public String status;
    String filename = "data";
    FileOutputStream outputStream;
    FileInputStream inputStream;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = "WAITING";

        context = this;

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsyncTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            try {
                                ServerCommunicatorTask task = new ServerCommunicatorTask();
                                String lobby = listOfGames(task);
                                populateGallery(lobby);
                                updateGrids();
                                load();
                                save();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                });
            }
        };
        timer.schedule(doAsyncTask, 0, 500);

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
                final Dialog dialog = new Dialog(context);
                dialog.setTitle("Game:");

                LinearLayout linLayout = new LinearLayout(context);
                linLayout.setOrientation(LinearLayout.VERTICAL);

                // set the custom dialog components - text, image and button
                TextView text = new TextView(context);
                text.setText("Enter Game Name & Player Name: ");

                final EditText gameName = new EditText(context);
                final EditText playerName = new EditText(context);

                Button dialogButton = new Button(context);
                dialogButton.setText("Continue");
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        if (gameName.getText().toString().length() == 0 || playerName.getText().toString().length() == 0)
                            return;

                        ServerCommunicatorTask task = new ServerCommunicatorTask();
                        String data = createNewGame(task, gameName.getText().toString(), playerName.getText().toString());
                        try {
                            JSONObject obj = new JSONObject(data);
                            Gallery.getInstance().gameIds.add(obj.getString("gameId"));
                            Gallery.getInstance().playerIds.add(obj.getString("playerId"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                linLayout.addView(text);
                linLayout.addView(gameName);
                linLayout.addView(playerName);
                linLayout.addView(dialogButton);
                dialog.addContentView(linLayout, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                dialog.show();
            }
        });
        nextTurnButton = new Button(this);
        nextTurnButton.setText("Toggle Status");
        nextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    save();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (status.equals("WAITING")) {
                    status = "PLAYING";
                    ServerCommunicatorTask task = new ServerCommunicatorTask();
                    String lobby = listOfGames(task);
                    populateGallery(lobby);
                }
                else if (status.equals("PLAYING")) {
                    status = "DONE";
                    ServerCommunicatorTask task = new ServerCommunicatorTask();
                    String lobby = listOfGames(task);
                    populateGallery(lobby);
                }
                else if (status.equals("DONE")) {
                    status = "WAITING";
                    ServerCommunicatorTask task = new ServerCommunicatorTask();
                    String lobby = listOfGames(task);
                    populateGallery(lobby);
                }
                try {
                    load();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Gallery.getInstance()._games.clear();
            }
        });
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
        Transaction.commit();
    }

    private void populateGallery(String lobby) {
        try {
            JSONArray array = new JSONArray(lobby);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String id = obj.getString("id");
                if (Gallery.getInstance().findGame(id) != null) {
                    Game game = Gallery.getInstance().findGame(id);
                    if (game.id.equals(Gallery.getInstance().id))
                        game.player1 = Gallery.getInstance().playerId;
                    if (Gallery.getInstance().gameIds.contains(id)) {
                        int index = Gallery.getInstance().gameIds.indexOf(id);
                        game.player1 = Gallery.getInstance().playerIds.get(index);
                    }
                    continue;
                }
                Gallery.getInstance().addNewGame(obj);
                ServerCommunicatorTask task = new ServerCommunicatorTask();
                _gameListFragment.getListView().requestLayout();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        Game game = Gallery.getInstance()._games.get(gameIndex);
        if (game.player1 != null && game._status.equals("WAITING")) {
            return;
        }
        Gallery.getInstance().set_currentGame(gameIndex);

        try {
            ServerCommunicatorTask task = new ServerCommunicatorTask();
            String data = gameDetails(task, Gallery.getInstance().getGame(gameIndex).id);
            final Dialog dialog = new Dialog(context);
            dialog.setTitle("Game:");

            LinearLayout linLayout = new LinearLayout(context);
            linLayout.setOrientation(LinearLayout.VERTICAL);


            JSONObject obj = new JSONObject(data);
            String name = obj.getString("name");
            String player1 = obj.getString("player1");
            String player2 = obj.getString("player2");
            String winner = obj.getString("winner");
            String status = obj.getString("status");
            int missilesLaunched = obj.getInt("missilesLaunched");

            // set the custom dialog components - text, image and button
            TextView text = new TextView(context);
            text.setText("Name: " + name + "\nPlayer 1 Name: " + player1
                    + "\nPlayer 2 Name: " + player2 + "\nWinner: " + winner
                    + "\nStatus: " + status + "\nMissiles Launched: " + missilesLaunched);


            Button dialogButton = new Button(context);
            dialogButton.setText("Continue");
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            linLayout.addView(text);
            linLayout.addView(dialogButton);
            dialog.addContentView(linLayout, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            dialog.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (game.player1 == null && game._status.equals("PLAYING"))
            return;
        if (game._status.equals("DONE"))
            return;
        if (game.player1 == null && game._status.equals("WAITING")) {
            ServerCommunicatorTask task = new ServerCommunicatorTask();
            String data = joinGame(task, game.id);
            try {
                JSONObject obj = new JSONObject(data);
                game.player1 = obj.getString("playerId");
                if (!Gallery.getInstance().gameIds.contains(game.id)) {
                    Gallery.getInstance().gameIds.add(game.id);
                    Gallery.getInstance().playerIds.add(obj.getString("playerId"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ServerCommunicatorTask task = new ServerCommunicatorTask();
        String data = getBoard(task, game.id, game.player1);
        if (data == null) {
            return;
        }
        game.setUpGrids(data);
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
        _gameListFragment.getListView().requestLayout();
    }

    @Override
    public void OnChangeMade(int x, int y) {
        try {
            ServerCommunicatorTask task = new ServerCommunicatorTask();
            Game game = Gallery.getInstance()._games.get(Gallery.getInstance().get_currentGame());
            if (game.player1 != null) {
                String data = turn(task, game.id, game.player1);
                JSONObject obj = new JSONObject(data);
                if (obj.getString("winner").equals("IN_PROGRESS")) {
                    try {
                        final Dialog dialog = new Dialog(context);
                        dialog.setTitle("Game:");

                        LinearLayout linLayout = new LinearLayout(context);
                        linLayout.setOrientation(LinearLayout.VERTICAL);

                        // set the custom dialog components - text, image and button
                        TextView text = new TextView(context);
                        text.setText("Winner: " + obj.getString("winner"));

                        Button dialogButton = new Button(context);
                        dialogButton.setText("Continue");
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        linLayout.addView(text);
                        linLayout.addView(dialogButton);
                        dialog.addContentView(linLayout, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                        dialog.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (obj.getBoolean("isYourTurn")) {
                    ServerCommunicatorTask task2 = new ServerCommunicatorTask();
                    guessHit(task2, game.id, game.player1, x, y);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateGrids() {
        if (Gallery.getInstance().get_currentGame() > 0) {
            if ((Gallery.getInstance()._games.size() > Gallery.getInstance().get_currentGame())) {
                Game game = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame());
                ServerCommunicatorTask task = new ServerCommunicatorTask();
                if (game.id != null && game.player1 != null) {
                    String data = getBoard(task, game.id, game.player1);
                    if (data == null)
                        return;
                    game.setUpGrids(data);
                    _gameFragment.updateView(Gallery.getInstance().get_currentGame());
                }
            }
        }
    }

    // Save Gallery to IO or die trying.
    public void save() throws FileNotFoundException {
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(Gallery.getInstance().saveGames(context).toString());
            oos.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Load Gallery from IO.
    public void load() throws FileNotFoundException {
        try {
            inputStream = openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            String data = (String) ois.readObject();
            JSONArray array = new JSONArray(data);
            Gallery.getInstance().loadGames(array);
            ois.close();
        } catch (IOException | JSONException | ClassNotFoundException ie) {
            ie.printStackTrace();
        }
    }

    private String listOfGames(ServerCommunicatorTask task) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            data = task.execute("/api/v2/lobby?status=" + status, "GET", json).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String gameDetails(ServerCommunicatorTask task, String id) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            data = task.execute("/api/v2/lobby/" + id, "GET", json).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String createNewGame(ServerCommunicatorTask task, String game, String player) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            json.put("gameName", game);
            json.put("playerName", player);
            data = task.execute("/api/v2/lobby/", "POST", json).get();
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String joinGame(ServerCommunicatorTask task, String id) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            json.put("playerName", "Battleboy");
            json.put("id", id);
            data = task.execute("/api/v2/lobby/" + id, "PUT", json).get();
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String guessHit(ServerCommunicatorTask task, String id, String playerID, int x, int y) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            json.put("playerId", playerID);
            json.put("xPos", x);
            json.put("yPos", y);
            data = task.execute("/api/v2/games/" + id, "POST", json).get();
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String turn(ServerCommunicatorTask task, String id, String playerId) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            data = task.execute("/api/v2/games/" + id + "?playerId=" + playerId, "GET", json).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String getBoard(ServerCommunicatorTask task, String id, String playerId) {
        String data = null;
        try {
            JSONObject json = new JSONObject();
            data = task.execute("/api/v2/games/" + id + "/boards?playerId=" + playerId, "GET", json).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }

    private class ServerCommunicatorTask extends AsyncTask<Object, Integer, String> {
        @Override
        protected String doInBackground(Object... params) {
            String data = null;
            try {
                data = ((new ServerCommunicator().getData((String) params[0], (String) params[1], (JSONObject) params[2])));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }
}
