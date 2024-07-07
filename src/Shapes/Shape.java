package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import paintBrush.ShapeStyle;

/**
 *
 * @author ESLAM
 */
public abstract class Shape {

    // Coordinates of the starting point
    final protected int x1;
    final protected int y1;
    // Coordinates of the ending point
    protected int x2;
    protected int y2;
    // Line thickness
    protected final int size;
    // Color of the shape
    protected final Color color;
    // Style of the shape
    protected final ShapeStyle style;

    /**
     * Constructs a Shape with the specified start and end points, color, and
     * fill state.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param size specifies the thickness of the lines
     * @param color The color of the shape.
     * @param style shapeStyle The style of the shape (None, Solid, Dotted).
     */
    public Shape(int x1, int y1, int size, Color color,ShapeStyle style) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1;
        this.y2 = y1;

        this.size = size;
        this.color = color;
        this.style = style;
    }

    /**
     * Updates the coordinates of end point of the shape.
     *
     * @param x The new x-coordinate of the second point.
     * @param y The new y-coordinate of the second point.
     */
    public void updateCoordinates(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    /**
     * Helper method to get the minimum and maximum coordinates between two
     * points.
     *
     * @return integer array contains minimum and maximum Points of x1,x2,y1,y2
     */
    protected int[] getMinMaxPoints() {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        return new int[]{minX, minY, maxX, maxY};
    }

    /**
     * Draws the shape using the specified Graphics object.
     *
     * @param g The Graphics object to draw with.
     */
    public abstract void draw(Graphics2D g);

}
