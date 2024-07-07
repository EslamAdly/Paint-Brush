/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import paintBrush.ShapeStyle;

/**
 *
 * @author ESLAM
 */
public class Oval extends Shape {

    private int drawPoints[];

    public Oval(int x1, int y1, int size, Color color, ShapeStyle style) {
        super(x1, y1, size, color, style);
    }

    @Override
    public void updateCoordinates(int x, int y) {
        x2 = x;
        y2 = y;
        drawPoints = getMinMaxPoints();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        switch (style) {
            case NONE:
                g2d.drawOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                break;
            case SOLID:
                g2d.fillOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                break;
            case DOTTED:
                float[] dash = { size * 5f,size *5f};
                g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 2.0f, dash, 0.0f));
                g2d.drawOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
                break;
            default:
                break;
        }
    }

}
