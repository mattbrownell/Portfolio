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
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Painting on the canvas.
 */
public class PaintAreaView extends View {
    public interface OnPolylineStartedListener {
        void onPolylineStarted();
    }

    public interface OnPolylineEndedListener {
        void onPolylineEnded(PointF[] polyline, Paint paintArray);
    }

    private List<List<PointF>> _polylines = new ArrayList<List<PointF>>();
    private List<List<PointF>> _polylinesBackup = new ArrayList<List<PointF>>();
    private List<Paint> paints = new ArrayList<Paint>();
    private int color;
    private int _strokeWidth;
    private boolean touchEnabled;

    OnPolylineStartedListener _polylineStartedListener = null;
    OnPolylineEndedListener _polylineEndedListener = null;

    public boolean isTouchEnabled() {
        return touchEnabled;
    }

    public void setTouchEnabled(boolean touchEnabled) {
        this.touchEnabled = touchEnabled;
    }

    public OnPolylineStartedListener get_polylineStartedListener() {
        return _polylineStartedListener;
    }

    public void set_polylineStartedListener(OnPolylineStartedListener _polylineStartedListener) {
        this._polylineStartedListener = _polylineStartedListener;
    }

    public OnPolylineEndedListener get_polylineEndedListener() {
        return _polylineEndedListener;
    }

    public void set_polylineEndedListener(OnPolylineEndedListener _polylineEndedListener) {
        this._polylineEndedListener = _polylineEndedListener;
    }

    public void empty() {
        _polylines = new ArrayList<List<PointF>>();
        paints.clear();
        invalidate();
    }

    public void addPolyline (PointF[] polyline, int strokeColor) {
        List<PointF> polylineArray = new ArrayList<PointF>();
        for (int pointIndex = 0; pointIndex < polyline.length; pointIndex++) {
            polylineArray.add(polyline[pointIndex]);
        }
        _polylines.add(polylineArray);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(50);
        paint.setColor(strokeColor);
        paints.add(paint);
        invalidate();
    }

    public int getPaint() {
        return color;
    }

    public void setPaint(int paint) {
        color = paint;
    }

    public PaintAreaView(Context context) {
        super (context);
        color = -50;
        touchEnabled = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oW, int oH) {
        super.onSizeChanged(w, h, oW, oH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (touchEnabled) {
            PointF touchPoint = new PointF(event.getX(), event.getY());
            Paint paint = new Paint();
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(50);
            if (color == -50)
                paint.setColor(Color.BLACK);
            else
                paint.setColor(color);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    _polylines.add(new ArrayList<PointF>());
                    paints.add(paint);
                    if (_polylineStartedListener != null)
                        _polylineStartedListener.onPolylineStarted();
                    break;
                case MotionEvent.ACTION_UP:
                    if (_polylineEndedListener != null) {
                        PointF[] points = new PointF[_polylines.get(_polylines.size() - 1).size()];
                        _polylines.get(_polylines.size() - 1).toArray(points);
                        _polylineEndedListener.onPolylineEnded(points, paint);
                    }
                    invalidate();
                    break;
                default:
                    break;
            }

            List<PointF> points = _polylines.get(_polylines.size() - 1);
            points.add(touchPoint);
            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paintIndex = 0;
        for (int polylineIndex = 0; polylineIndex < _polylines.size(); polylineIndex++) {
            List<PointF> points = _polylines.get(polylineIndex);
            for (int pointIndex = 1; pointIndex < points.size(); pointIndex++) {
                PointF previousPoint = points.get(pointIndex - 1);
                PointF point = points.get(pointIndex);
                canvas.drawLine(previousPoint.x, previousPoint.y, point.x, point.y, paints.get(polylineIndex));
            }
        }
    }
}

