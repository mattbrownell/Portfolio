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
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Create the palette background, and handle layout for aplotches.
 */
public class PaletteView extends ViewGroup implements PaintSplotchView.OnSwatchChangedListener{

    public interface OnColorChangedListener {
        void onColorChanged(int paint);
    }

    private PaintSplotchView previous;
    private Context appContext;
    private float scale;
    private OnColorChangedListener _colorChangedListener = null;

    public float getScale() {
        return scale;
    }

    public void setScale(int check) {
        if (check == 1)
            this.scale = scale *= .95f;
        if (check == 0)
            this.scale = scale /= .95f;
    }

    // Construct palette with default splotches.
    public PaletteView(Context context) {
        super(context);
        appContext = context;
        previous = null;
        scale = 1;

        PaintSplotchView blackPaintSplotchView = new PaintSplotchView(context);
        blackPaintSplotchView.set_splotchColor(Color.BLACK);
        this.addView(blackPaintSplotchView);

        PaintSplotchView whitePaintSplotchView = new PaintSplotchView(context);
        whitePaintSplotchView.set_splotchColor(Color.WHITE);
        this.addView(whitePaintSplotchView);

        PaintSplotchView redPaintSplotchView = new PaintSplotchView(context);
        redPaintSplotchView.set_splotchColor(Color.RED);
        this.addView(redPaintSplotchView);

        PaintSplotchView greenPaintSplotchView = new PaintSplotchView(context);
        greenPaintSplotchView.set_splotchColor(Color.GREEN);
        this.addView(greenPaintSplotchView);

        PaintSplotchView bluePaintSplotchView = new PaintSplotchView(context);
        bluePaintSplotchView.set_splotchColor(Color.BLUE);
        this.addView(bluePaintSplotchView);

        blackPaintSplotchView.setOnSwatchChangedListener(this);
        whitePaintSplotchView.setOnSwatchChangedListener(this);
        redPaintSplotchView.setOnSwatchChangedListener(this);
        greenPaintSplotchView.setOnSwatchChangedListener(this);
        bluePaintSplotchView.setOnSwatchChangedListener(this);
    }

    public OnColorChangedListener getOnColorChangedListener() {
        return _colorChangedListener;
    }

    public void setOnColorChangedListener(OnColorChangedListener colorChangedListener) {
        _colorChangedListener = colorChangedListener;
    }

    // Remove highlight.
    private void unHighlight() {
        previous.set_highlighted(false);
    }

    // Create a new splotch with given color.
    public void createChild(int color) {
        PaintSplotchView newSplotch = new PaintSplotchView(appContext);
        newSplotch.set_splotchColor(color);
        this.addView(newSplotch);
        newSplotch.setOnSwatchChangedListener(this);
        setScale(1);
    }

    // Remove previous splotch beside last remaining one.
    public void removeChild() {
        if (this.getChildCount() > 1) {
            this.removeViewAt(this.getChildCount() - 1);
            this.setScale(0);
        }
    }

    // Draw the oval painters palette.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF paletteRect = new RectF();
        paletteRect.left = getPaddingLeft()*0.9f;
        paletteRect.top = getPaddingTop()*0.9f;
        paletteRect.right = getWidth() - getPaddingRight()*0.9f;
        paletteRect.bottom = getHeight() - getPaddingBottom()*0.9f;

        Paint palettePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        palettePaint.setColor(Color.argb(255, 218, 165, 32));

        canvas.drawOval(paletteRect, palettePaint);
    }

    // Setup the layout for all splotches in an oval shape.
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        setWillNotDraw(false);
        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            float theta = (float) (2.0 * Math.PI) / (float)getChildCount() * (float)childIndex;

            float density = getResources().getDisplayMetrics().density;
            float childWidth = 0.5f * 160.0f * density * scale;
            float childHeight = 0.4f * 160.0f * density * scale;
            float radius = getWidth() * 0.5f - childWidth * 0.5f;

            PointF childCenter = new PointF();
            childCenter.x = (float) (getWidth() * 0.5f + (getWidth() * 0.5f - childWidth * 0.7f - getPaddingRight() * 0.8f) * Math.cos(theta));
            childCenter.y = (float) (getHeight() * 0.5f + (getHeight() * 0.5f - childHeight * 0.7f - getPaddingTop() * 0.8f) * Math.sin(theta));

            Rect childRect = new Rect();
            childRect.left = (int)(childCenter.x - childWidth * 0.5f);
            childRect.right = (int)(childCenter.x + childWidth * 0.5f);
            childRect.top = (int)(childCenter.y - childHeight * 0.5f);
            childRect.bottom = (int)(childCenter.y + childHeight * 0.5f);

            View childView = getChildAt(childIndex);
            childView.layout(childRect.left, childRect.top, childRect.right, childRect.bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = 1300;
        int height = 700;

        width = Math.max(width, getSuggestedMinimumWidth());
        height = Math.max(height, getSuggestedMinimumWidth());

        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec) ,
                resolveSize(height, heightMeasureSpec));
    }

    // When splotch is selected, highlight current, unhighlight last.
    @Override
    public void onSwatchChanged(int paint, PaintSplotchView paintSplotchView) {

        if (_colorChangedListener !=null) {
            if (previous != null)
                unHighlight();
            _colorChangedListener.onColorChanged(paint);
            previous = paintSplotchView;
        }

    }
}