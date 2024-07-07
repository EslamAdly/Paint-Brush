/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintBrush;

import Shapes.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ESLAM
 */
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Shape currentShape;
    private ShapeType currentShapeType;

    //Flag to check if the mouse has been dragged. Ensures mouseReleased logic only executes if a drag event has occurred
    private boolean hasDragged;
    private ShapeStyle currentShapeStyle;
    private Color currentColor;
    private int currentSize;
    private final ArrayList<Shape> shapes;
    CoordinatesBox coordinatesBox;

    public DrawingPanel(CoordinatesBox coordinatesBox) {
        this.coordinatesBox = coordinatesBox;

        this.setBackground(Color.white);
        this.currentColor = Color.black;
        this.currentShapeType = ShapeType.Line;
        this.currentShapeStyle = ShapeStyle.NONE;
        this.shapes = new ArrayList<>();
        this.currentSize = 1;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    // Setters for various properties

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    public ShapeStyle getcurrentFillState() {
        return currentShapeStyle;
    }

    public void setCurrentShapeStyle(ShapeStyle currentShapeStyle) {
        this.currentShapeStyle = currentShapeStyle;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * Clears all shapes from the panel.
     */
    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    /**
     * Removes the last shape from the list of shapes (undo).
     */
    public void removeLast() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //cast g to Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }

        if (currentShape != null) {
            //cap: The end cap style, which defines how the end points of lines are drawn.
            //join: The line join style, which defines how two line segments are joined together
            g2d.setStroke(new BasicStroke(currentSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setColor(currentColor);
            currentShape.draw(g2d);
        }

    }

    //MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x1 = e.getX();
        int y1 = e.getY();
        switch (currentShapeType) {
            case Line:
                currentShape = new Line(x1, y1, currentSize, currentColor,currentShapeStyle);
                break;
            case Rectangle:
                currentShape = new Rectangle(x1, y1, currentSize, currentColor, currentShapeStyle);
                break;
            case Oval:
                currentShape = new Oval(x1, y1, currentSize, currentColor, currentShapeStyle);
                break;
            case Pencil:
                currentShape = new Pencil(x1, y1, currentSize, currentColor);
                break;
            case Eraser:
                currentShape = new Pencil(x1, y1, currentSize, Color.WHITE);
                break;
            default:
                currentShape = null;
                break;
        }

        hasDragged = false;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (hasDragged && currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Set crosshair cursor when drawing starts
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //MouseMotionListener methods
    @Override
    public void mouseDragged(MouseEvent e) {
        coordinatesBox.setCoordinates(e.getX(), e.getY());
        int x2 = e.getX();
        int y2 = e.getY();
        if (currentShape != null) {
            currentShape.updateCoordinates(x2, y2);
        }
        hasDragged = true;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        coordinatesBox.setCoordinates(e.getX(), e.getY());
    }

    public void exportToPng(File outFile) {

        BufferedImage outImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        //Create Graphics2D (g2d)to draw into this BufferedImage(outImage).
        Graphics2D g2d = outImage.createGraphics();

        //paint DrawingPanel to BufferedImage(outImage)
        this.paint(g2d);
        //dispose(): helps to release these resources back to the system, ensuring efficient resource management.
        g2d.dispose();
        try {
            // Write the BufferedImage to a file
            ImageIO.write(outImage, "png", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
