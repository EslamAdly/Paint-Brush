/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintBrush;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ESLAM
 */
public class ToolsPanel extends JPanel {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 50;
    private static final int INITIAL_SIZE = 1;

    private final DrawingPanel drawingPanel;
    private final JButton clearButton;
    private final JButton undoButton;
    private final JButton lineButton;
    private final JButton rectangleButton;
    private final JButton ovalButton;
    private final JButton pencilButton;
    private final JButton eraserButton;
    private final JButton exportButton;
    private final JSpinner sizeSpinner;
    private final JRadioButton solidRadioButton;
    private final JRadioButton dottedRadioButton;
    private final JRadioButton noneRadioButton;

    private final ButtonGroup stateGroup;
    private final JPanel statePanel;

    private final JPanel sizePanel;

    public ToolsPanel(DrawingPanel frame) {
        this.drawingPanel = frame;
        this.setLayout(new GridLayout(1, 7, 5, 5));
        this.setBorder(new TitledBorder("Paint Mode"));
        // 
        clearButton = new JButton("Clear");
        undoButton = new JButton("Undo");
        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        ovalButton = new JButton("Oval");
        pencilButton = new JButton("Pencil");
        exportButton = new JButton("Export");
        eraserButton = new JButton("Eraser");

        sizeSpinner = new JSpinner(new SpinnerNumberModel(INITIAL_SIZE, MIN_SIZE, MAX_SIZE, 1));
        solidRadioButton = new JRadioButton("Solid");
        dottedRadioButton = new JRadioButton("Dotted");
        noneRadioButton = new JRadioButton("None");

        stateGroup = new ButtonGroup();
        statePanel = new JPanel();
        sizePanel = new JPanel();
        
        
        setupStylePanel();
        setupSizePanel();
        setupActionListeners();
        addComponents();
    }

    private void setupStylePanel() {
        stateGroup.add(solidRadioButton);
        stateGroup.add(dottedRadioButton);
        stateGroup.add(noneRadioButton);
        noneRadioButton.setSelected(true);  // Set default selection to solid

        statePanel.setLayout(new GridLayout(3, 1, 2, 2));
        statePanel.add(noneRadioButton);
        statePanel.add(solidRadioButton);
        statePanel.add(dottedRadioButton);
        statePanel.setBorder(new TitledBorder("state"));
    }

    private void setupSizePanel() {
        sizePanel.setBorder(new TitledBorder("Size"));
        sizePanel.add(sizeSpinner);
    }

    private void setupActionListeners() {
        // Set up action listeners for tool buttons
        lineButton.addActionListener(new ToolActionListener(ShapeType.Line));
        rectangleButton.addActionListener(new ToolActionListener(ShapeType.Rectangle));
        ovalButton.addActionListener(new ToolActionListener(ShapeType.Oval));
        pencilButton.addActionListener(new ToolActionListener(ShapeType.Pencil));
        eraserButton.addActionListener(new ToolActionListener(ShapeType.Eraser));

        // Set up action listeners for clear and undo buttons
        clearButton.addActionListener(e -> drawingPanel.clearShapes());
        undoButton.addActionListener(e -> drawingPanel.removeLast());

        // Set up item listener for style radio buttons
        solidRadioButton.addItemListener(e -> drawingPanel.setCurrentShapeStyle(ShapeStyle.SOLID));
        noneRadioButton.addItemListener(e -> drawingPanel.setCurrentShapeStyle(ShapeStyle.NONE));
        dottedRadioButton.addItemListener(e -> drawingPanel.setCurrentShapeStyle(ShapeStyle.DOTTED));

        // Set up change listener for size spinner
        sizeSpinner.addChangeListener(e -> drawingPanel.setCurrentSize((int) sizeSpinner.getValue()));

        exportButton.addActionListener(e -> exportAction());
    }
    
    private void addComponents() {
        //Add buttons to panel

        add(undoButton);
        add(clearButton);
        add(lineButton);
        add(rectangleButton);
        add(ovalButton);
        add(pencilButton);
        add(eraserButton);
        add(statePanel);
        add(sizePanel);
        add(exportButton);
    }

    void exportAction() {
        File imagesDir = new File("./images");
        if (!imagesDir.exists()) {
            imagesDir.mkdir();
        }

        JFileChooser fileChooser = new JFileChooser(imagesDir);
        //make only png files shown to user.
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.setFileFilter(filter);

        while (true) {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                //ensure file is png
                if (!selectedFile.getName().endsWith(".png")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".png");
                }

                //check if file is already exists.
                if (selectedFile.exists()) {
                    //give option to user to overwrite or not.
                    int option = JOptionPane.showConfirmDialog(fileChooser, "File already exists. Do you want to overwrite it?", "Confirm to save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        //overwrite the file with current png
                        drawingPanel.exportToPng(selectedFile);
                        break;
                    }
                } else {
                    drawingPanel.exportToPng(selectedFile);
                    break;
                }
            } else {
                //if user cancel or exit.
                break;
            }
        }
    }

    /**
     * ToolActionListener is a helper class to handle selection of shape tools.
     */
    class ToolActionListener implements ActionListener {

        private final ShapeType type;

        public ToolActionListener(ShapeType type) {
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentShapeType(type);
        }
    }
}
