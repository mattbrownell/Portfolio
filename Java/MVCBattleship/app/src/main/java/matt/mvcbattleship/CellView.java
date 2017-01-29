package matt.mvcbattleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Matt on 10/21/2016.
 */
public class CellView extends View {
    public interface OnCellSelectedListener {
        void onCellSelected(int x, int y);
    }

    private String _status;
    private int _x;
    private int _y;
    public boolean cellEnabled;
    private OnCellSelectedListener _cellSelectedListener = null;

    public CellView(Context context) {
        super(context);
        _status = "NONE";
        cellEnabled = false;
    }

    public CellView(Context context, int x, int y) {
        super(context);
        _status = "NONE";
        _x = x;
        _y = y;
        cellEnabled = false;
    }

    public void setStatus(String status) {
        _status = status;
        this.invalidate();
    }

    public OnCellSelectedListener getOnCellSelectedListener() {
        return _cellSelectedListener;
    }

    public void setOnCellSelectedListener(OnCellSelectedListener cellSelectedListener) {
        _cellSelectedListener = cellSelectedListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (cellEnabled) {
            if (_cellSelectedListener != null)
                _cellSelectedListener.onCellSelected(_x, _y);
            return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF backgroundRect = new RectF();
        backgroundRect.left = (getPaddingLeft());
        backgroundRect.top = (getPaddingTop());
        backgroundRect.right = ((getWidth() - getPaddingRight()));
        backgroundRect.bottom = ((getHeight() - getPaddingBottom()));

        Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.BLACK);
        canvas.drawRect(backgroundRect, backgroundPaint);

        RectF shipRect = new RectF();
        shipRect.left = (float) (getPaddingLeft() * 0.9);
        shipRect.top = (float) (getPaddingTop() * 0.9);
        shipRect.right = (float) ((getWidth() - getPaddingRight())* 0.9);
        shipRect.bottom = (float) ((getHeight() - getPaddingBottom())* 0.9);

        float offset = (getHeight() - shipRect.width())*0.5f;
        shipRect.top += offset;
        shipRect.bottom += offset;

        Paint shipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (_status.equals("SHIP") || _status.equals("HIT")) {
            shipPaint.setColor(Color.GRAY);
        }
        else {
            shipPaint.setColor(Color.BLUE);
        }
        canvas.drawRect(shipRect, shipPaint);

        PointF center = new PointF();
        center.x  = shipRect.width()/4;
        center.y = shipRect.width()/4;

        RectF statusRect = new RectF();
        statusRect.left = center.x - getPaddingLeft();
        statusRect.top = center.y - getPaddingTop();
        statusRect.right = center.x + (getWidth() - getPaddingRight())/2;
        statusRect.bottom = center.y + (statusRect.right - getPaddingBottom())/2;

        Paint statusPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (_status.equals("MISS")){
            statusPaint.setColor(Color.WHITE);
            canvas.drawOval(statusRect, statusPaint);
        }
        else if(_status.equals("HIT")) {
            statusPaint.setColor(Color.RED);
            canvas.drawOval(statusRect, statusPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int dpi = getResources().getDisplayMetrics().densityDpi;

        int height = (int) (1*dpi*.12);
        int width = (int) (1*dpi*.12);
        boolean phone = getResources().getBoolean(R.bool.isTab);
        if (phone) {
            height = (int) (height*1.5);
            width = (int) (width*1.5);
        }

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSpec;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSpec;
        }

        if (widthMode != MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            if (width < height) {
                width = height;
            }
            if (height < width) {
                height = width;
            }
        }

        width = Math.max(width, getSuggestedMinimumWidth());
        height = Math.max(height, getSuggestedMinimumWidth());

        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec) ,
                resolveSize(height, heightMeasureSpec));
    }
}
