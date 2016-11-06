// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Handle all splotches added to PaletteView layout.
 */
public class PaintSplotchView extends View {

    public interface OnSwatchChangedListener {
        void onSwatchChanged(int paint, PaintSplotchView paintSplotchView);
    }

    int _splotchColor = Color.WHITE;
    boolean _highlighted = false;
    OnSwatchChangedListener _swatchChangedListener = null;

    public PaintSplotchView(Context context) {
        super(context);
    }

    public int get_splotchColor() {
        return _splotchColor;
    }

    public void set_splotchColor(int splotchColor) {
        _splotchColor = splotchColor;
        invalidate();
    }

    public boolean is_highlighted() {
        return _highlighted;
    }

    public void set_highlighted(boolean highlighted) {
        _highlighted = highlighted;
        invalidate();
    }

    public OnSwatchChangedListener getOnSwatchChangedListener() {
        return _swatchChangedListener;
    }

    public void setOnSwatchChangedListener(OnSwatchChangedListener swatchChangedListener) {
        _swatchChangedListener = swatchChangedListener;
    }

    // Signal listener when tapped.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.set_highlighted(true);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (_swatchChangedListener !=null)
                _swatchChangedListener.onSwatchChanged(get_splotchColor(), this);
        }

        return true;
    }

    // Draw the splotch, and draw the highlight if highlighted.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF splotchRect = new RectF();
        splotchRect.left = getPaddingLeft();
        splotchRect.top = getPaddingTop();
        splotchRect.right = getWidth() - getPaddingRight();
        splotchRect.bottom = getHeight() - getPaddingBottom();

        Paint splotchPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        splotchPaint.setColor(_splotchColor);

        canvas.drawOval(splotchRect, splotchPaint);

        if (_highlighted) {
            Paint highlightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            highlightPaint.setStyle(Paint.Style.STROKE);
            highlightPaint.setColor(Color.rgb(255-Color.red(this.get_splotchColor()),
                    255-Color.green(this.get_splotchColor()),
                    255-Color.blue(this.get_splotchColor())));
            highlightPaint.setStrokeWidth(getWidth() * 0.03f);
            canvas.drawOval(splotchRect, highlightPaint);
        }
    }
}