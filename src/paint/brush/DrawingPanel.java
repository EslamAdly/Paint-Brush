/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.brush;

import Shapes.Line;
import Shapes.Oval;
import Shapes.Rectangle;
import Shapes.Shape;
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
    
    public Color currentColor;
    ShapeType currentShapeType;
    boolean currentFillState;
    ArrayList<Shape> shapes;
    int x1, y1, x2, y2;
    
    CoordinatesBox coordinatesBox;

    public DrawingPanel(CoordinatesBox coordinatesBox) {
        this.coordinatesBox=coordinatesBox;
        
        this.setBackground(Color.white);
        this.currentColor = Color.black;
        this.currentShapeType = ShapeType.Line;
        this.currentFillState = false;
        this.shapes = new ArrayList<>();
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseMove());
    }

    class MouseInput implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println(e.getX() + "   " + e.getY() + "   " + currentColor);
            x1 = e.getX();
            y1 = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int minx = Math.min(x1, x2);
            int maxx = Math.max(x1, x2);
            int miny = Math.min(y1, y2);
            int maxy = Math.max(y1, y2);
            switch (currentShapeType) {
                case Line:
                    shapes.add(new Line(x1, y1, x2, y2, currentColor));
                    break;
                case Rectangle:
                    shapes.add(new Rectangle(minx, miny, maxx, maxy, currentColor, currentFillState));
                    break;
                case Oval:
                    shapes.add(new Oval(minx, miny, maxx, maxy, currentColor, currentFillState));
                    break;
            }
            System.out.println("now");
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
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            coordinatesBox.setCoordinates(e.getX(), e.getY());
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        int minx = Math.min(x1, x2);
        int maxx = Math.max(x1, x2);
        int miny = Math.min(y1, y2);
        int maxy = Math.max(y1, y2);

        for (Shape shape : shapes) {
            shape.draw(g);
        }

        g.setColor(currentColor);
        switch (currentShapeType) {
            case Line:
                g.drawLine(x1, y1, x2, y2);
                break;
            case Rectangle:
                if (currentFillState) {
                    g.fillRect(minx, miny, maxx - minx, maxy - miny);
                } else {
                    g.drawRect(minx, miny, maxx - minx, maxy - miny);
                }
                break;
            case Oval:
                if (currentFillState) {
                    g.fillOval(minx, miny, maxx - minx, maxy - miny);
                } else {
                    g.drawOval(minx, miny, maxx - minx, maxy - miny);
                }
                break;
        }
    }
}
