// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles each individual painting.
 */
public class Drawing implements Serializable{
    // List of all strokes contained within.
    List<Stroke> _strokes = new ArrayList<Stroke>();

    // Total strokes in painting.
    int getStrokeCount() {
        return _strokes.size();
    }

    // Get an individual stroke.
    Stroke getStroke (int strokeIndex) {
        return _strokes.get(strokeIndex);
    }

    // Add an individual stroke.
    void addStroke(Stroke stroke) {
        _strokes.add(stroke);
    }
}
