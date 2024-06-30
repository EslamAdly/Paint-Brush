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
import java.util.ArrayList;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author ESLAM
 */
public class Pencil extends Shape {

    //private ArrayList<Pair<Integer,Integer>>points;
    private final ArrayList<Integer> xPoints;
    private final ArrayList<Integer> yPoints;
    private final int size;
    /***
     * 
     * @param x1  start X point of pencil shape 
     * @param y1  start Y point of pencil shape
     * @param size size of shape
     * @param color color of shape
     */
    public Pencil(int x1, int y1,int size, Color color) {
        super(x1, y1, 0, 0, color, false);
        this.size=size;
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        addPoint(x1, y1);
    }

    public void addPoint(int x, int y) {
        xPoints.add(x);
        yPoints.add(y);
    }

//draw without using 2d graphics
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        //System.out.println(size);
        for (int i = 0; i < xPoints.size() - 1; i++) {
            for(int j=0;j<=size;j++){
                g.drawLine(xPoints.get(i), yPoints.get(i)+j, xPoints.get(i + 1), yPoints.get(i + 1)+j);
                g.drawLine(xPoints.get(i), yPoints.get(i)-j, xPoints.get(i + 1), yPoints.get(i + 1)-j);
                
                g.drawLine(xPoints.get(i)+j, yPoints.get(i), xPoints.get(i + 1)+j, yPoints.get(i + 1));
                g.drawLine(xPoints.get(i)-j, yPoints.get(i), xPoints.get(i + 1)-j, yPoints.get(i + 1));
                
            }
        }
    }

}