/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.Color;

/**
 *
 * @author ESLAM
 */
public class Rectangle extends Shape{
    private float width,height;
    Rectangle(int x1,int y1,int x2,int y2,Color color,boolean fillState){
        super(x1, y1, x2, y2,color,fillState);
        width=calcWidth();
        height=calcHeight();
    }
    private float calcWidth(){
        return x2-x1;
    }
    private float calcHeight(){
        return y2-y1;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
