package paint.brush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MainScene extends JFrame {
    private DrawingPanel drawingPanel;
    private ToolsPanel toolsPanel;
    private ColorsPanel colorsPanel;
    private CoordinatesBox coordinatesBox;

    public MainScene() {
        // Set up the main frame
        setTitle("Paint Brush");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Use BorderLayout for the main frame

        // Initialize panels
        coordinatesBox = new CoordinatesBox();
        drawingPanel = new DrawingPanel(coordinatesBox);
        toolsPanel = new ToolsPanel(drawingPanel);
        colorsPanel = new ColorsPanel(drawingPanel);

        // Create panels for top and bottom
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns
        topPanel.add(toolsPanel);
        topPanel.add(colorsPanel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout for simplicity
        bottomPanel.add(coordinatesBox);

        // Create middlePanel with GridLayout (larger size)
        JPanel middlePanel = new JPanel(new GridLayout(1, 1)); // 1 row, 1 column
        middlePanel.add(drawingPanel);

        // Add top, middle, and bottom panels to the main frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        

        //Add MouseMotionListener to update coordinates
//        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                coordinatesBox.setCoordinates(e.getX() , e.getY());
//            }
//        });
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainScene mainScene = new MainScene();
            mainScene.setVisible(true);
        });
    }
}
enum ShapeType {
    Line, Rectangle, Oval, Pencil, Eraser;
}