// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Handle the knobs used for selecting colors.
 */
public class KnobView  extends View {

    public interface OnAngleChangedListener {
        void onAngleChanged(float angle, KnobView knobView);
    }

    private Canvas canvas;
    private Paint _knobPaint = new Paint();
    private RectF _knobRect = new RectF();
    private Paint _nibPaint = new Paint();
    private RectF _nibRect = new RectF();
    private float _theta = 0.0f;
    private OnAngleChangedListener _angleChangedListener = null;

    public KnobView(Context context) {
        super (context);
        _knobPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _knobPaint.setColor(Color.WHITE);
    }

    public float get_theta() {
        return _theta;
    }

    public void set_theta(float theta) {
        _theta = theta;
        invalidate();
    }

    public Paint getKnobPaint() {
        return _knobPaint;
    }

    public void setKnobPaint(int paint) {
        this._knobPaint.setColor(paint);
        this.invalidate();
    }

    public OnAngleChangedListener getOnAngleChangedListener() {
        return _angleChangedListener;
    }

    public void setOnAngleChangedListener(OnAngleChangedListener angleChangedListener) {
        _angleChangedListener = angleChangedListener;
    }

    // When knob is rotated, signal for a new color value and update drawn oval.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF touchPoint = new PointF();
        touchPoint.x = event.getX();
        touchPoint.y = event.getY();

        float theta = (float) Math.atan2(
                touchPoint.y - _knobRect.centerY(),
                touchPoint.x - _knobRect.centerX());
        set_theta(theta);

        if (_angleChangedListener !=null)
            _angleChangedListener.onAngleChanged(theta, this);

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        _knobRect = new RectF();
        _knobRect.left = getPaddingLeft();
        _knobRect.top = getPaddingTop();
        _knobRect.right = getWidth() - getPaddingRight();
        _knobRect.bottom = _knobRect.right - getPaddingBottom();

        //Center knob
        float offset = (getHeight() - _knobRect.height()) *0.5f;
        _knobRect.top += offset;
        _knobRect.bottom += offset;

        float knobRadius = _knobRect.width()*0.4f;

        PointF nibCenter = new PointF();
        nibCenter.x = (float) (_knobRect.centerX() + (float)Math.cos(_theta) * knobRadius);
        nibCenter.y = (float) (_knobRect.centerY() + (float)Math.sin(_theta) * knobRadius);

        float nibRadius = knobRadius / 8.0f;

        _nibRect = new RectF();
        _nibRect.left = nibCenter.x - nibRadius;
        _nibRect.top = nibCenter.y - nibRadius;
        _nibRect.right = nibCenter.x + nibRadius;
        _nibRect.bottom = nibCenter.y + nibRadius;

        _nibPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _nibPaint.setColor(Color.YELLOW);

        canvas.drawOval(_knobRect, _knobPaint);
        canvas.drawOval(_nibRect, _nibPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int width = 300;
        int height = 300;

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
