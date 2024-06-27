/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ESLAM
 */
public class Line extends Shape{
    
    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color, false);
    }
    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
