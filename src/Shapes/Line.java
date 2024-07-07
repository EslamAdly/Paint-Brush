/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import paintBrush.ShapeStyle;

/**
 *
 * @author ESLAM
 */
public class Line extends Shape {

    public Line(int x1, int y1, int size, Color color, ShapeStyle style) {
        super(x1, y1, size, color, style);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(color);

        switch (style) {
            case SOLID:
            case NONE:
                g2d.drawLine(x1, y1, x2, y2);
                break;
            case DOTTED:
                float[] dash = { size * 5f,size * 5f};
                g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.CAP_SQUARE, 1.0f, dash, 0.0f));
                g2d.drawLine(x1, y1, x2, y2);
                break;
        }
    }
}
