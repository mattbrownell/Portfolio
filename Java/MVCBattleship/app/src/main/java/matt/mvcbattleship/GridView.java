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
        void OnValidInteract(int x, int y);
    }

    private CellView[][] cells;
    private int _player;

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
                if (_player == 1) {
                    cells[i][j].setStatus(Gallery.getInstance().getGame(game).getGrid1().getCell(i,j)._status);
                }
                if (_player == 2) {
                    cells[i][j].setStatus(Gallery.getInstance().getGame(game).getGrid2().getCell(i, j)._status);
                }
            }
        }
    }

    public GridView(Context context, int player) {
        super(context);
        _player = player;

        cells = new CellView[10][10];

        this.setColumnCount(10);
        this.setRowCount(10);
        this.setBackgroundColor(Color.BLACK);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                CellView cell = new CellView(context, i, j);
                cell.setOnCellSelectedListener(this);
                cells[i][j] = cell;
                if (_player==2) {
                    cell.cellEnabled = true;
                }
                this.addView(cell, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
    }

    @Override
    public void onCellSelected(int x, int y) {
        if(onValidInteractListener != null) {
            this.onValidInteractListener.OnValidInteract(x,y);
        }
        cells[x][y].invalidate();
    }
}
