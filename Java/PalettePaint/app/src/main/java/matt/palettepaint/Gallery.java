// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton of all drawings in the application.
 */
public class Gallery implements Serializable{
    private static Gallery _Instance = null;
    List<Drawing> _drawings = new ArrayList<Drawing>();
    // Save current drawing index for use in reinitializing applications.
    private int _currentDrawing;

    // Create it as a singleton.
    public static Gallery getInstance () {
        if (_Instance == null) {
            _Instance = new Gallery();
        }
        return _Instance;
    }

    // Make sure the default index is an impossible value at start.
    private Gallery () {
        _currentDrawing = -1;
    }

    // Return the amount of drawings in the Gallery.
    int getDrawingCount () {
        return _drawings.size();
    }

    // Return the current index in the Gallery.
    public int get_currentDrawing() {
        return _currentDrawing;
    }

    // Set the current index of the Gallery.
    public void set_currentDrawing(int _currentDrawing) {
        this._currentDrawing = _currentDrawing;
    }

    // For use in saving the Gallery to IO.
    List<Drawing> saveDrawings() {
       return _drawings;
    }

    // Load Gallery from IO.
    void loadDrawing(List<Drawing> drawings) {
        _drawings = drawings;
    }

    // Get a drawing based on given index.
    Drawing getDrawing (int drawingIndex) { return _drawings.get(drawingIndex); }

    // Create a new blank drawing.
    void addNewDrawing() {
        _drawings.add(new Drawing());
    }

    // Create a new stroke in a drawing.
    void addStrokeToDrawing(int drawingIndex, Stroke stroke) {
        _drawings.get(drawingIndex).addStroke(stroke);
    }
}
