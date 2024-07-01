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
public class Oval extends Shape {

    private int width, height;

    public Oval(int x1, int y1, int x2, int y2, int size, Color color, boolean fillState) {
        super(x1, y1, x2, y2, size, color, fillState);

        width = calcWidth();
        height = calcHeight();
    }

    private int calcWidth() {
        return x2 - x1;
    }

    private int calcHeight() {
        return y2 - y1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (fillState) {
            g2d.fillOval(x1, y1, width, height);
        } else {
            g2d.drawOval(x1, y1, width, height);
        }
    }

}
