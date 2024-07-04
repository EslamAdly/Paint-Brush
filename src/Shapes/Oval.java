/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author ESLAM
 */
public class Oval extends Shape {

    private int drawPoints [];

    public Oval(int x1, int y1, int size, Color color, boolean fillState) {
        super(x1, y1, size, color, fillState);
    }

    @Override
    public void updateCoordinates(int x, int y) {
        x2=x;
        y2=y;
        drawPoints = getMinMaxPoints();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (fillState) {
            g2d.fillOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
        } else {
            g2d.drawOval(drawPoints[0], drawPoints[1], drawPoints[2] - drawPoints[0], drawPoints[3] - drawPoints[1]);
        }
    }

}
