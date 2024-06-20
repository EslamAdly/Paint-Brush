package Shapes;
import java.awt.*;

/**
 *
 * @author ESLAM
 */
public abstract class Shape  {
    //start point
    final int x1;
    final int y1;
    //end point
    final int x2; 
    final int y2;
    Color color;
    final boolean fillState;
    /**
     * 
     * @param x1 
     * @param y1
     * @param x2
     * @param y2
     * @param color
     * @param fillState true if that shape in solid ,false otherwise 
     */
    public Shape(int x1, int y1, int x2, int y2,Color color ,boolean fillState) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color=color;
        this.fillState=fillState;
    }
    
}
/*
line  (x1,y1,x2,y2)
oval  (x1 , y1 ,width ,hight) 
rect  (x1 , y1 ,width ,hight)
*/