/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.brush;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ESLAM
 */
public class ColorsPanel extends JPanel{
    JButton green;
    JButton red ;
    JButton black;
    JButton blue;
    MainScene frame;
    ColorsPanel(MainScene frame){
        
        this.frame =frame;
        
        black =createColorButton("Black",Color.black);
        red =createColorButton("RED",Color.red);
        green =createColorButton("Green",Color.green);
        blue=createColorButton("Blue",Color.blue);

        this.add(black);
        this.add(red);
        this.add(green);
        this.add(blue);
        
        setLayout(new GridLayout(1, 5, 1, 2));
        setBorder(new TitledBorder("Colors"));
    }
    /**
     * Creates a color button with the specified label and background color.
     * 
     * @param label the text label of the button
     * @param color the background color of the button
     * @return a JButton with the specified properties
     */
    private JButton createColorButton(String text,Color color){
        JButton button=new JButton(text);
        button.setSize(25,25);
        button.setBackground(color);
        button.addActionListener(new ColorChange());
        return button;
    }
    
    
    class ColorChange implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(((JButton)e.getSource()).getBackground()); 
            frame.current=((JButton)e.getSource()).getBackground();
        }
    }
}
