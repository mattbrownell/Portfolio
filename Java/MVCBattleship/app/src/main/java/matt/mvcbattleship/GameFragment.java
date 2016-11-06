package matt.mvcbattleship;

import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Matt on 10/21/2016.
 */
public class GameFragment extends Fragment implements GridView.OnValidInteractListener{

    public interface OnChangeMadeListener {
        void OnChangeMade();
    }

    public interface NextTurnListener {
        void OnNextTurn();
    }

    public static GameFragment onInstance() {
        return new GameFragment();
    }

    public GameFragment() {

    }

    GridView _player1;
    GridView _player2;
    public LinearLayout gameLayout;
    public FrameLayout spacer;

    public NextTurnListener getNextTurnListener() {
        return nextTurnListener;
    }

    public void setNextTurnListener(NextTurnListener nextTurnListener) {
        this.nextTurnListener = nextTurnListener;
    }

    public NextTurnListener nextTurnListener;

    public OnChangeMadeListener get_onChangeMade() {
        return _onChangeMade;
    }

    public void set_onChangeMade(OnChangeMadeListener _onChangeMade) {
        this._onChangeMade = _onChangeMade;
    }

    private OnChangeMadeListener _onChangeMade = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gameLayout = new LinearLayout(getActivity());
        spacer = new FrameLayout(getActivity());

        int orien = this.getResources().getConfiguration().orientation;
        if (orien == 1) {
            gameLayout.setOrientation(LinearLayout.VERTICAL);
        }
        else {
            gameLayout.setOrientation(LinearLayout.HORIZONTAL);
        }

        boolean phone = getResources().getBoolean(R.bool.isTab);

        final Button nextTurnButton = new Button(getActivity());
        nextTurnButton.setText("Next Turn");
        nextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextTurnListener != null) {
                    nextTurnListener.OnNextTurn();
                }
            }
        });

        spacer.setPadding(20,20,20,20);
        _player1 = new GridView(getActivity(), 1);
        _player1.setOnValidInteractListener(this);
        _player2 = new GridView(getActivity(), 2);
        _player2.setOnValidInteractListener(this);
        gameLayout.addView(_player1,  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        if (phone) {
            gameLayout.addView(nextTurnButton);
        }
        else {
            gameLayout.addView(spacer);
        }

        gameLayout.addView(_player2,  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));


        if (Gallery.getInstance().get_currentGame() != -1) {
            updateView(Gallery.getInstance().get_currentGame());
        }

        if (Gallery.getInstance().get_currentGame() != -1) {
            int turn = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_playerTurn();
            if (turn == 3) {
                updateView(Gallery.getInstance().get_currentGame());
            }
        }

        _player2.hideBoats(Gallery.getInstance().get_currentGame());
        _player1.hideBoats(Gallery.getInstance().get_currentGame());

        return gameLayout;
    }

    public void updateView(int game) {
        int turn = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_playerTurn();
        if (turn == 1) {
            _player1.set_turn(true);
            _player2.set_turn(false);
        }
        else if (turn == 2){
            _player1.set_turn(false);
            _player2.set_turn(true);
        }
        else if (turn ==3) {
            _player1.set_turn(true);
            _player2.set_turn(true);
        }
        _player1.setupGame(game);
        _player2.setupGame(game);
        _player2.hideBoats(Gallery.getInstance().get_currentGame());
        _player1.hideBoats(Gallery.getInstance().get_currentGame());
    }

    @Override
    public void OnValidInteract() {
        if (Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getHits() == 17 ||
                Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getHits() == 17) {
            _player1.set_turn(true);
            _player2.set_turn(true);
            Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).set_playerTurn(3);
            if (Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getHits() == 17) {
                Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).set_status("Player 2 Won");
            }
            else if (Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getHits() == 17) {
                Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).set_status("Player 1 Won");
            }
        }
        else if (_player1.is_turn()) {
//            _player1.set_turn(false);
//            _player2.set_turn(true);
            _player2.set_turn(true);
            _player1.set_turn(true);
            Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).set_playerTurn(2);
            _player2.hideBoats(Gallery.getInstance().get_currentGame());
            _player1.hideBoats(Gallery.getInstance().get_currentGame());
        }
        else if (_player2.is_turn()){
//            _player1.set_turn(true);
//            _player2.set_turn(false);
            _player2.set_turn(true);
            _player1.set_turn(true);
            Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).set_playerTurn(1);
            _player2.hideBoats(Gallery.getInstance().get_currentGame());
            _player1.hideBoats(Gallery.getInstance().get_currentGame());
        }
        else {
            _player2.set_turn(false);
            _player1.set_turn(false);
        }
        if (_onChangeMade != null) {
            _onChangeMade.OnChangeMade();
        }
    }

    public void renablePlay() {
        int turn = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_playerTurn();
        if (turn == 2) {
             _player1.set_turn(false);
             _player2.set_turn(true);
        }
        else if (turn == 1) {
            _player1.set_turn(true);
            _player2.set_turn(false);
        }
        _player1.setupGame(Gallery.getInstance().get_currentGame());
        _player2.setupGame(Gallery.getInstance().get_currentGame());
    }
}
