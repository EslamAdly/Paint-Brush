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
        
        black =new JButton("black");
        black.setSize(25,25);
        black.setBackground(Color.black);
        black.addActionListener(new ColorChange());
        add(black);
        
        red =new JButton("red");
        red.setSize(25,25);
        red.setBackground(Color.red);
        red.addActionListener(new ColorChange());
        add(red);
        
        green =new JButton("Green");
        green.setSize(15,15);
        green.setBackground(Color.green);
        green.addActionListener(new ColorChange());
        add(green);
        
        blue =new JButton("Yellow");
        blue.setSize(25,25);
        blue.setBackground(Color.blue);
        blue.addActionListener(new ColorChange());
        add(blue);
        
        setLayout(new GridLayout(1, 5, 1, 2));
        setBorder(new TitledBorder("Colors"));
    }
    class ColorChange implements ActionListener{
    

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(((JButton)e.getSource()).getBackground()); 
            frame.curent=((JButton)e.getSource()).getBackground();
        }
    }
}
