// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Custom button for use in bringing the palette activity up.
 */
public class PaletteButton extends View {
    int _splotchColor;

    public PaletteButton(Context context) {
        super(context);
        _splotchColor = Color.BLACK;
    }

    public void set_splotchColor(int splotchColor) {
        _splotchColor = splotchColor;
        invalidate();
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = 200;
        int height = 200;

        width = Math.max(width, getSuggestedMinimumWidth());
        height = Math.max(height, getSuggestedMinimumWidth());

        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec) ,
                resolveSize(height, heightMeasureSpec));
    }
}
