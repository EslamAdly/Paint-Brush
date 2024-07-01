/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.brush;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ESLAM
 */
public class ToolsPanel extends JPanel {
    DrawingPanel drawingPanel;
    
    private JButton clear;
    private JButton undo;
    
    private JButton lineButton;
    private JButton rectangleButton ;
    private JButton ovalButton;
    private JButton pencilButton;
    private JButton eraserButton;
    private JSpinner sizeSpinner;
    private JCheckBox solid;
    
    private JPanel sizePanel;
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

        sizePanel = new JPanel();
        sizePanel.setBorder(new TitledBorder("Size"));
        sizeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
        sizePanel.add(sizeSpinner);
           
        // Set up action listeners
        lineButton.addActionListener(new ToolActionListener(ShapeType.Line));
        rectangleButton.addActionListener(new ToolActionListener(ShapeType.Rectangle));
        ovalButton.addActionListener(new ToolActionListener(ShapeType.Oval));
        pencilButton.addActionListener(new ToolActionListener(ShapeType.Pencil));
        eraserButton.addActionListener(new ToolActionListener(ShapeType.Eraser));

        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clearShapes();
            }
        });
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.removeLast();
            }
        });
        
        solid.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                //drawingPanel.currentFillState=!drawingPanel.currentFillState;
                drawingPanel.setCurrentFillState(!drawingPanel.getcurrentFillState());
            }
            
        });
        
        sizeSpinner.addChangeListener(new ChangeListener (){
            @Override
            public void stateChanged(ChangeEvent e) {
                drawingPanel.setCurrentSize((int)sizeSpinner.getValue());   
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
        add (sizePanel);
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
            drawingPanel.setCurrentShapeType(type);
        }
    }
}
