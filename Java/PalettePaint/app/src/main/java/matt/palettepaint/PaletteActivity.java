// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.HashSet;

/**
 * Create the palette and handle callbacks.
 */
public class PaletteActivity extends AppCompatActivity implements PaletteView.OnColorChangedListener, KnobColorSelectorView.OnAddColorListener, KnobColorSelectorView.OnRemoveColorListener {
    private PaletteView swatchesLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lock the user to portrait view.

        // Create layouts and set them up.
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setBackgroundColor(Color.GRAY);
        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
            rootLayout.setOrientation(LinearLayout.VERTICAL);
        else
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);

        rootLayout.setGravity(Gravity.CENTER);

        // Handle PaletteView
        swatchesLayout = new PaletteView(this);
        swatchesLayout.setPadding(50,100,50,100);
        swatchesLayout.setBackgroundColor(Color.GRAY);

        LinearLayout paletteKnobsLayout = new LinearLayout(this);

        // Handle KnobColorSelectorView
        KnobColorSelectorView knobsButtonsLayout = new KnobColorSelectorView(this);
        knobsButtonsLayout.setGravity(Gravity.CENTER);
        knobsButtonsLayout.setBackgroundColor(Color.GRAY);

        // Handle all app listeners.
        swatchesLayout.setOnColorChangedListener(this);
        knobsButtonsLayout.setOnAddColorListener(this);
        knobsButtonsLayout.setOnRemoveColorListener(this);

        // Format all layouts together.
        rootLayout.addView(swatchesLayout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        rootLayout.addView(knobsButtonsLayout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 3));
        //rootLayout.addView(paletteKnobsLayout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 1950, 1));

        setContentView(rootLayout);

        // Add colors to palette from drawing.
        if (getIntent().hasExtra("drawing")) {
            int drawingIndex = getIntent().getExtras().getInt("drawing");
            HashSet<Integer> colors = new HashSet<Integer>();
            colors.add(Color.BLACK);
            colors.add(Color.WHITE);
            colors.add(Color.GREEN);
            colors.add(Color.RED);
            colors.add(Color.BLUE);
            for (int i = 0; i < Gallery.getInstance().getDrawing(drawingIndex).getStrokeCount(); i++) {
                colors.add(Gallery.getInstance().getDrawing(drawingIndex).getStroke(i).getColor());
            }
            colors.remove(Color.BLACK);
            colors.remove(Color.WHITE);
            colors.remove(Color.GREEN);
            colors.remove(Color.RED);
            colors.remove(Color.BLUE);
            for (Integer color : colors) {
                onAddColor(color);
            }
        }
        else {
            Gallery.getInstance().addNewDrawing();
        }
    }

    // When color is changed on the palette, change color on canvas.
    public void onColorChanged(int paint) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", paint);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    // When add color is pressed, add currently selected color to palette.
    @Override
    public void onAddColor(int paint) {
        swatchesLayout.createChild(paint);
    }

    // When remove color is pressed, remove last color from palette.
    @Override
    public void onRemoveColor() {
        swatchesLayout.removeChild();
    }
}
