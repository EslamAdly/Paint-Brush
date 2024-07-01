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

/**
 *
 * @author ESLAM
 */
public class Line extends Shape {

    public Line(int x1, int y1, int x2, int y2, int size, Color color) {
        super(x1, y1, x2, y2, size, color, false);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }
}
