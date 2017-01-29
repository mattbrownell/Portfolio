// Author: Matt Stoker / Matt Brownell
// Class: CS4530
// Due Date: 10/3/2016
// Application will allow a user to define colors and draw using these colors.

package matt.palettepaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles each stroke of a drawing.
 */
public class Stroke implements Serializable {
    public static class Point implements Serializable {
        public float x;
        public float y;
    }

    List<Point> _points = new ArrayList<Point>();
    int _color;

    int getPointCoutn() {
        return _points.size();
    }

    Point getPoint (int pointIndex) {
        return _points.get(pointIndex);
    }

    void addPoint (Point p) {
        _points.add(p);
    }

    public int getColor() {
        return _color;
    }

    public void setColor (int color) {
        _color = color;
    }
}
