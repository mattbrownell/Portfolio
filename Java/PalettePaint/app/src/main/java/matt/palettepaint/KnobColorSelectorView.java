// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Handles the Knobs and Buttons for adding and removing colors.
 */
public class KnobColorSelectorView extends LinearLayout implements KnobView.OnAngleChangedListener {

    public interface OnKnobChangedListener {
        void onKnobChanged(int paint);
    }

    public interface OnAddColorListener {
        void onAddColor(int paint);
    }

    public interface OnRemoveColorListener {
        void onRemoveColor();
    }

    private Context appContext;
    private KnobView colorKnobR;
    private KnobView colorKnobG;
    private KnobView colorKnobB;
    private Button removeColor;
    private Button addColor;
    private int color;
    private OnKnobChangedListener _knobChangedListener = null;
    private OnAddColorListener _addColorListener = null;
    private OnRemoveColorListener _removeColorListener = null;

    public OnKnobChangedListener getOnKnobChangedListener() {
        return _knobChangedListener;
    }

    public void setOnKnobChangedListener(OnKnobChangedListener knobChangedListener) {
        _knobChangedListener = knobChangedListener;
    }

    public void setOnRemoveColorListener(OnRemoveColorListener removeColorListener) {
        _removeColorListener = removeColorListener;
    }

    public OnRemoveColorListener getRemoveColorListener() {
        return _removeColorListener;
    }

    public OnAddColorListener getAddColorListener() {
        return _addColorListener;
    }

    public void setOnAddColorListener(OnAddColorListener addColorListener) {
        _addColorListener = addColorListener;
    }

    public KnobColorSelectorView(Context context) {
        super(context);
        appContext = context;

        this.setOrientation(VERTICAL);
        LinearLayout layout = new LinearLayout(context);
        LinearLayout buttons = new LinearLayout(context);

        colorKnobR = new KnobView(appContext);
        colorKnobR.setPadding(20,20,20,20);
        colorKnobR.set_theta(0.0f);
        colorKnobR.setBackgroundColor(Color.GRAY);
        layout.addView(colorKnobR, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        colorKnobG = new KnobView(appContext);
        colorKnobG.setPadding(20,20,20,20);
        colorKnobG.set_theta(0.0f);
        colorKnobG.setBackgroundColor(Color.GRAY);
        layout.addView(colorKnobG, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        colorKnobB = new KnobView(appContext);
        colorKnobB.setPadding(20,20,20,20);
        colorKnobB.set_theta(0.0f);
        colorKnobB.setBackgroundColor(Color.GRAY);
        layout.addView(colorKnobB, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        colorKnobR.setOnAngleChangedListener(this);
        colorKnobG.setOnAngleChangedListener(this);
        colorKnobB.setOnAngleChangedListener(this);

        removeColor = new Button(context);
        removeColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (_removeColorListener !=null)
                    _removeColorListener.onRemoveColor();
            }
        });
        addColor = new Button(context);
        addColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (_addColorListener !=null)
                    _addColorListener.onAddColor(color);
            }
        });
        removeColor.setText("-");
        addColor.setText("+");
        buttons.addView(addColor);
        buttons.addView(removeColor);

        color = Color.argb(255, (int) (Math.min(255, Math.toDegrees(colorKnobR.get_theta())+180)), (int) Math.min(255, Math.toDegrees(colorKnobG.get_theta())+180), (int) Math.min(255, Math.toDegrees(colorKnobB.get_theta())+180));
        removeColor.setBackgroundColor(color);
        addColor.setBackgroundColor(color);

        this.addView(layout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
        this.addView(buttons, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        System.out.println(colorKnobR.get_theta());

        colorKnobR.setKnobPaint(Color.argb(255, (int) Math.min(255, Math.toDegrees(0)+180), 0, 0));
        colorKnobG.setKnobPaint(Color.argb(255, 0, (int) Math.min(255, Math.toDegrees(0)+180), 0));
        colorKnobB.setKnobPaint(Color.argb(255, 0, 0, (int) Math.min(255, Math.toDegrees(0)+180)));
    }

    public void onAngleChanged(float angle, KnobView knob) {
        if (knob == colorKnobR) {
            knob.setKnobPaint(Color.argb(255, (int) Math.min(255, Math.toDegrees(angle)+180), 0, 0));
        }
        if (knob == colorKnobG) {
            knob.setKnobPaint(Color.argb(255, 0, (int) Math.min(255, Math.toDegrees(angle)+180), 0));
        }
        if (knob == colorKnobB) {
            knob.setKnobPaint(Color.argb(255, 0, 0, (int) Math.min(255, Math.toDegrees(angle)+180)));
        }
        color = Color.argb(255, (int) (Math.min(255, Math.toDegrees(colorKnobR.get_theta())+180)), (int) Math.min(255, Math.toDegrees(colorKnobG.get_theta())+180), (int) Math.min(255, Math.toDegrees(colorKnobB.get_theta())+180));
        removeColor.setBackgroundColor(color);
        addColor.setBackgroundColor(color);
    }
}
