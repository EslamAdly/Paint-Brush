/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.brush;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ESLAM
 */
public class ToolsPanel extends JPanel {
    DrawingPanel drawingPanel;
    
    JButton clear;
    JButton undo;
    
    JButton lineButton;
    JButton rectangleButton ;
    JButton ovalButton;
    JButton pencilButton;
    JButton eraserButton;

    JCheckBox solid;
    public ToolsPanel(DrawingPanel frame) {
        this.drawingPanel = frame;
        
        //Create buttons
        clear=new JButton("Clear");
        undo=new JButton("Undo");
        
        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        ovalButton = new JButton("Oval");
        pencilButton = new JButton("Pencil");
        eraserButton = new JButton("Eraser");
        
        solid=new JCheckBox("Solid");
        
        // Set up action listeners
        lineButton.addActionListener(new ToolActionListener(ShapeType.Line));
        rectangleButton.addActionListener(new ToolActionListener(ShapeType.Rectangle));
        ovalButton.addActionListener(new ToolActionListener(ShapeType.Oval));
        pencilButton.addActionListener(new ToolActionListener(ShapeType.Pencil));
        eraserButton.addActionListener(new ToolActionListener(ShapeType.Eraser));

        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.shapes.clear();
                drawingPanel.repaint();
            }
        });
        
        solid.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                drawingPanel.currentFillState=!drawingPanel.currentFillState;
            }
            
        });
        
        //Add buttons to panel
        add(undo);
        add(clear);
        
        add(lineButton);
        add(rectangleButton);
        add(ovalButton);
        add(pencilButton);
        add(eraserButton);
        add(solid);

        setLayout(new GridLayout(1, 4, 5, 5));
        setBorder(new TitledBorder("Paint Mode"));
    }
    class ToolActionListener implements ActionListener{
        ShapeType type;
        public ToolActionListener(ShapeType type) {
            this.type=type;
        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.currentShapeType=type;
        }
    }
}
