// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/*
Controls and creates the various layouts and models used by the application.
 */
public class MainActivity extends AppCompatActivity implements PaintAreaView.OnPolylineEndedListener, ValueAnimator.AnimatorUpdateListener {

    // Save layouts for use in listeners.
    private PaintAreaView canvas;
    private int _currentDrawing;
    static final int SWATCH_REQUEST = 1;
    private boolean orien = true;
    private int currentTime;
    private PaletteButton swatch;
    private boolean playButtonPressed;
    private ValueAnimator animation;
    private Button playButton;
    private Button forwardButton;
    private boolean endAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Lock the user to portrait view.
        playButtonPressed = false;
        endAnimation = true;
        animation = new ValueAnimator();

        // Handle PaintAreaView
        canvas = new PaintAreaView(this);
        canvas.setBackgroundColor(Color.WHITE);
        canvas.set_polylineEndedListener(this);

        // Create layouts and set them up.
        LinearLayout rootLayout = new LinearLayout(this);
        LinearLayout buttonLayout = new LinearLayout(this);

        final Button backButton = new Button(this);
        forwardButton = new Button(this);

        // Change app layout according to phone orientation.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rootLayout.setOrientation(LinearLayout.VERTICAL);
            orien = true;
        }
        else {
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
            buttonLayout.setOrientation(LinearLayout.VERTICAL);
            orien = false;
        }

        // Determine whether the app was initialized using the ListView, next/previous buttons, or a new Drawing.
        if (Gallery.getInstance().get_currentDrawing() > -1) {
            _currentDrawing = Gallery.getInstance().get_currentDrawing();
            insertDrawing(_currentDrawing);
        }
        else if (getIntent().hasExtra("drawing")) {
            int drawingIndex = getIntent().getExtras().getInt("drawing");
            _currentDrawing = drawingIndex;
            Gallery.getInstance().set_currentDrawing(_currentDrawing);
            insertDrawing(drawingIndex);
        }
        else {
            Gallery.getInstance().addNewDrawing();
        }

        // Determine whether forward or back buttons should be disabled at launch.
        if (_currentDrawing == 0) {
            backButton.setEnabled(false);
        }
        if (_currentDrawing == Gallery.getInstance().getDrawingCount()-1 && Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount() == 0) {
            forwardButton.setEnabled(false);
        }

        // Handle the swatch selector.
        swatch = new PaletteButton(this);
        swatch.setPadding(30,10,30,10);
        swatch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaletteActivity.class);
                intent.putExtra("drawing", _currentDrawing);
                MainActivity.this.startActivityForResult(intent, SWATCH_REQUEST);
            }
        });

        // Handles forward traversal through the ListView.
        forwardButton.setText("->");
        forwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (_currentDrawing+1 != Gallery.getInstance().getDrawingCount()) {
                    _currentDrawing += 1;
                    if (_currentDrawing+1 == Gallery.getInstance().getDrawingCount() && Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount() == 0) {
                        forwardButton.setEnabled(false);
                    }
                    insertDrawing(_currentDrawing);
                    Gallery.getInstance().set_currentDrawing(_currentDrawing);
                    if (!backButton.isEnabled()) {
                        backButton.setEnabled(true);
                    }
                }
                else {
                    Gallery.getInstance().addNewDrawing();
                    _currentDrawing += 1;
                    insertDrawing(_currentDrawing);
                    forwardButton.setEnabled(false);
                    Gallery.getInstance().set_currentDrawing(_currentDrawing);
                    if (!backButton.isEnabled()) {
                        backButton.setEnabled(true);
                    }
                }
                canvas.invalidate();
            }
        });

        // Handles backwards traversal through the ListView.
        backButton.setText("<-");
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (_currentDrawing != 0) {
                    _currentDrawing -= 1;
                    insertDrawing(_currentDrawing);
                    Gallery.getInstance().set_currentDrawing(_currentDrawing);
                    forwardButton.setEnabled(true);
                }
                if (_currentDrawing == 0) {
                    backButton.setEnabled(false);
                }
                canvas.invalidate();
            }
        });

        // Handles animation of the drawing using a ValueAnimator.
        playButton = new Button(this);
        playButton.setText("|>");
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!playButtonPressed) {
                    if (Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount() != 0) {
                        float animationValue = 0f;

                        endAnimation = false;

                        swatch.setEnabled(false);
                        forwardButton.setEnabled(false);
                        backButton.setEnabled(false);
                        playButton.setText("| |");
                        canvas.setTouchEnabled(false);

                        canvas.empty();

                        animation.setDuration(5000);
                        animation.setIntValues(0, Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount());
                        animation.addUpdateListener(MainActivity.this);
                        animation.start();
                        playButtonPressed = true;
                    }
                }
                else {
                    animation.cancel();
                    canvas.empty();

                    canvas.setTouchEnabled(true);
                    swatch.setEnabled(true);
                    forwardButton.setEnabled(true);
                    if (_currentDrawing != 0)
                        backButton.setEnabled(true);
                    playButton.setText("|>");
                    insertDrawing(_currentDrawing);

                    playButtonPressed = false;
                }
            }
        });

        // Format all layouts together.
        rootLayout.addView(canvas, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        buttonLayout.addView(swatch, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        buttonLayout.addView(backButton);
        buttonLayout.addView(forwardButton);
        buttonLayout.addView(playButton);
        rootLayout.addView(buttonLayout);

        setContentView(rootLayout);
    }

    // When a line is made in the paint, add it to the drawing Gallery.
    @Override
    public void onPolylineEnded(PointF[] polyline, Paint paint) {
        if (Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount() == 0) {
            forwardButton.setEnabled(true);
        }
        Stroke stroke = new Stroke();
        stroke.setColor(paint.getColor());
        for (PointF polylinePoint : polyline) {
            Stroke.Point strokePoint = new Stroke.Point();
            float pixelsPerInch = getResources().getDisplayMetrics().density*160f;
            strokePoint.x = polylinePoint.x/pixelsPerInch;
            strokePoint.y = polylinePoint.y/pixelsPerInch;
            stroke.addPoint(strokePoint);
        }
        Gallery.getInstance().addStrokeToDrawing(_currentDrawing, stroke);
    }

    // Create the drawing on the canvas for display.
    public void insertDrawing (int drawingIndex) {
        canvas.empty();
        float pixelsPerInch = getResources().getDisplayMetrics().density*160f;
        Drawing drawing = Gallery.getInstance().getDrawing(drawingIndex);
        for (int strokeIndex = 0; strokeIndex < drawing.getStrokeCount(); strokeIndex++) {
            Stroke stroke = drawing.getStroke(strokeIndex);
            PointF[] polyline = new PointF[stroke.getPointCoutn()];
            for (int pointIndex = 0; pointIndex < stroke.getPointCoutn(); pointIndex++) {
                Stroke.Point strokePoint = stroke.getPoint(pointIndex);
                PointF polylinePoint = new PointF();
                polylinePoint.x = strokePoint.x * pixelsPerInch;
                polylinePoint.y = strokePoint.y * pixelsPerInch;
                polyline[pointIndex] = polylinePoint;
            }
            canvas.addPolyline(polyline, stroke.getColor());
        }
        canvas.invalidate();
    }

    // When the color is selected in the palette set the currently selected color and display.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SWATCH_REQUEST) {
            if (resultCode == RESULT_OK) {
                int magenta = Color.MAGENTA;
                int result = data.getIntExtra("result", magenta);
                canvas.setPaint(result);
                swatch.set_splotchColor(result);
            }
        }
    }

    // Animate the drawing through ticks from 0 to StrokeCount.
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (!endAnimation) {
            int currentStroke = (int) animation.getAnimatedValue();
            float pixelsPerInch = getResources().getDisplayMetrics().density * 160f;
            if (currentStroke < Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount()) {
                Stroke stroke = Gallery.getInstance().getDrawing(_currentDrawing).getStroke(currentStroke);
                PointF[] polyline = new PointF[stroke.getPointCoutn()];
                for (int pointIndex = 0; pointIndex < stroke.getPointCoutn(); pointIndex++) {
                    Stroke.Point strokePoint = stroke.getPoint(pointIndex);
                    PointF polylinePoint = new PointF();
                    polylinePoint.x = strokePoint.x * pixelsPerInch;
                    polylinePoint.y = strokePoint.y * pixelsPerInch;
                    polyline[pointIndex] = polylinePoint;
                    int dog;
                }
                canvas.addPolyline(polyline, stroke.getColor());
                canvas.invalidate();
            }
            if (currentStroke == Gallery.getInstance().getDrawing(_currentDrawing).getStrokeCount()) {
                playButton.callOnClick();
                endAnimation = true;
            }
        }
    }
}
