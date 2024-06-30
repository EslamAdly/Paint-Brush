/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.brush;

import Shapes.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ESLAM
 */
public class DrawingPanel extends JPanel {

    private Shape currentPencil;
    private ShapeType currentShapeType;

    private boolean isDrawing;
    private boolean currentFillState;
    private Color currentColor;
    private int currentPencilSize;

    private final ArrayList<Shape> shapes;
    private int x1, y1, x2, y2;

    CoordinatesBox coordinatesBox;

    public DrawingPanel(CoordinatesBox coordinatesBox) {
        this.coordinatesBox = coordinatesBox;

        this.setBackground(Color.white);
        this.currentColor = Color.black;
        this.currentShapeType = ShapeType.Line;
        this.currentFillState = false;
        this.isDrawing = true;
        this.shapes = new ArrayList<>();
        this.currentPencilSize = 0;

        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseMove());
    }

    public void setCurrentPencilSize(int currentPencilSize) {
        this.currentPencilSize = currentPencilSize;
    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType;
    }

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType = currentShapeType;
    }

    public boolean getcurrentFillState() {
        return currentFillState;
    }

    public void setCurrentFillState(boolean currentFillState) {
        this.currentFillState = currentFillState;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    public void removeLast() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }

    }

    /**
     * Helper method to get the minimum and maximum coordinates between two
     * points.
     *
     * @return integer array contains minimum and maximum Points of x1,x2,y1,y2
     */
    private int[] getMinMaxPoints() {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        return new int[]{minX, minY, maxX, maxY};
    }

    class MouseInput implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            switch (currentShapeType) {
                case Pencil:
                    currentPencil = new Pencil(x1, y1, currentPencilSize, currentColor);
                    break;
                case Eraser:
                    currentPencil = new Pencil(x1, y1, currentPencilSize, Color.WHITE);
                    break;
                default:
                    currentPencil = null;
                    break;
            }
                        isDrawing = true;

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int drawPoints[] = getMinMaxPoints();
            switch (currentShapeType) {
                case Line:
                    shapes.add(new Line(x1, y1, x2, y2, currentColor));
                    break;
                case Rectangle:
                    shapes.add(new Rectangle(drawPoints[0], drawPoints[1], drawPoints[2], drawPoints[3], currentColor, currentFillState));
                    break;
                case Oval:
                    shapes.add(new Oval(drawPoints[0], drawPoints[1], drawPoints[2], drawPoints[3], currentColor, currentFillState));
                    break;
                case Eraser:
                case Pencil:
                    shapes.add(currentPencil);
                    currentPencil = null;
                    break;
            }
            isDrawing = false;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class MouseMove implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            coordinatesBox.setCoordinates(e.getX(), e.getY());
            x2 = e.getX();
            y2 = e.getY();
            if (currentPencil instanceof Pencil) {
                ((Pencil) currentPencil).addPoint(x2, y2);
            }

            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            coordinatesBox.setCoordinates(e.getX(), e.getY());
        }

    }

    @Override
    public void paint(Graphics g) {
        
        if(currentShapeType!=ShapeType.Pencil){
            g=(Graphics)g;
        }
        super.paint(g);

        int drawPoints[] = getMinMaxPoints();

        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (isDrawing) {
            g.setColor(currentColor);
            switch (currentShapeType) {
                case Line:
                    g.drawLine(x1, y1, x2, y2);
                    break;
                case Rectangle:
                    if (currentFillState) {
                        g.fillRect(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                    } else {
                        g.drawRect(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                    }
                    break;
                case Oval:
                    if (currentFillState) {
                        g.fillOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                    } else {
                        g.drawOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                    }
                    break;
                case Pencil:
                case Eraser:
                    currentPencil.draw(g);
                    break;
            }
        }
    }
}
