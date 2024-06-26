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
public class Rectangle extends Shape{
    private int width,height;
    public Rectangle(int x1,int y1,int x2,int y2,Color color,boolean fillState){
        super(x1, y1, x2, y2,color,fillState);
        width=calcWidth();
        height=calcHeight();
    }
    private int calcWidth(){
        return x2-x1;
    }
    private int calcHeight(){
        return y2-y1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        if(fillState){
            g.fillRect(x1, y1, width, height);
        }
        else{
            g.drawRect(x1, y1, width, height);

        }
    }
    
}
