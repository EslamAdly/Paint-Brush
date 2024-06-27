package Shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ESLAM
 */
public abstract class Shape {

    //start point
    final int x1;
    final int y1;
    //end point
    final int x2;
    final int y2;

    Color color;

    final boolean fillState;

    /**
     * Constructs a Shape with the specified start and end points, color, and
     * fill state.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param color The color of the shape.
     * @param fillState True if the shape is filled, false otherwise.
     */
    public Shape(int x1, int y1, int x2, int y2, Color color, boolean fillState) {

        if (this instanceof Line) {
            // Keep original coordinates for lines
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        } else {
            // Ensure x1 and y1 are the smallest values
            this.x1 = Math.min(x1, x2);
            this.y1 = Math.min(y1, y2);

            // Ensure x2 and y2 are the greatest values
            this.x2 = Math.max(x1, x2);
            this.y2 = Math.max(y1, y2);
        }
        this.color = color;
        this.fillState = fillState;
    }

    /**
     * Draws the shape using the specified Graphics object.
     *
     * @param g The Graphics object to draw with.
     */
    public abstract void draw(Graphics g);

}
