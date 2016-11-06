package matt.mvcbattleship;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Created by Matt on 10/21/2016.
 */
public class GridView extends GridLayout implements CellView.OnCellSelectedListener {

    public interface OnValidInteractListener {
        void OnValidInteract();
    }

    private Context _context;
    private CellView[][] cells;
    private int hitcount;
    private int misscount;
    private int _player;
    public boolean _turn;

    public boolean is_turn() {
        return _turn;
    }

    public void set_turn(boolean _turn) {
        this._turn = _turn;
    }

    public OnValidInteractListener getOnValidInteractListener() {
        return onValidInteractListener;
    }

    public void setOnValidInteractListener(OnValidInteractListener onValidInteractListener) {
        this.onValidInteractListener = onValidInteractListener;
    }

    OnValidInteractListener onValidInteractListener;

    public void setupGame(int game) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (_turn) {
                    if (_player == 1) {
                        cells[i][j].set_ship(Gallery.getInstance().getGame(game).get_grid1().getCell(i,j).get_ship());
                    }
                    if (_player == 2) {
                        cells[i][j].set_ship(Gallery.getInstance().getGame(game).get_grid2().getCell(i,j).get_ship());
                    }
                }
                else {
                    cells[i][j].set_ship(false);
                }
                if (_player == 1) {
                    cells[i][j].set_status(Gallery.getInstance().getGame(game).get_grid1().getCell(i,j).get_status());
                }
                if (_player == 2) {
                    cells[i][j].set_status(Gallery.getInstance().getGame(game).get_grid2().getCell(i,j).get_status());
                }
            }
        }
    }

    public void hideBoats(int game) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j].set_ship(false);
            }
        }
    }

    public GridView(Context context, int player) {
        super(context);
        _context = context;
        _player = player;

        if (player == 1) {
            _turn = true;
        }
        else if (player == 2) {
            _turn = false;
        }

        cells = new CellView[10][10];

        this.setColumnCount(10);
        this.setRowCount(10);
        this.setBackgroundColor(Color.BLACK);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                CellView cell = new CellView(context, i, j);
                if (_turn) {
                    if (player==1) {
                        cell.set_ship(Gallery.getInstance().getGame(0).get_grid1().getCell(i, j).get_ship());
                        cell.set_status(Gallery.getInstance().getGame(0).get_grid1().getCell(i, j).get_status());
                    }
                    else if(player == 2) {
                        cell.set_ship(Gallery.getInstance().getGame(0).get_grid2().getCell(i,j).get_ship());
                        cell.set_status(Gallery.getInstance().getGame(0).get_grid2().getCell(i,j).get_status());
                    }
                }
                else {
                    cell.set_ship(false);
                    cell.set_status(0);
                }
                cell.setOnCellSelectedListener(this);
                cells[i][j] = cell;
                this.addView(cell, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
    }

    @Override
    public void onCellSelected(int x, int y) {
        boolean ship = false;
        int status = 0;
        if (_player == 1) {
            ship = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getCell(x, y).get_ship();
            status = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getCell(x, y).get_status();
        }
        else if(_player == 2) {
            ship = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getCell(x, y).get_ship();
            status = Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getCell(x, y).get_status();
        }

        if (!_turn) {
            if (status == 0) {
                if (ship) {
                    cells[x][y].set_status(2);
                    if (_player == 1) {
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getCell(x, y).set_status(2);
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().cellHit();
                    }
                    else {
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getCell(x, y).set_status(2);
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().cellHit();
                    }
                }
                else {
                    cells[x][y].set_status(1);
                    if (_player == 1) {
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getCell(x, y).set_status(1);
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().cellMiss();
                    }
                    else {
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getCell(x, y).set_status(1);
                        Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().cellMiss();
                    }
                }
                //Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().setTotal(Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid1().getTotal()+1);
                //Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().setTotal(Gallery.getInstance().getGame(Gallery.getInstance().get_currentGame()).get_grid2().getTotal()+1);
                if(onValidInteractListener != null) {
                    this.onValidInteractListener.OnValidInteract();
                }
            }
        }
        cells[x][y].invalidate();
    }
}
