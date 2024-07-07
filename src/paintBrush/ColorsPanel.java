/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintBrush;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ESLAM
 */
public class ColorsPanel extends JPanel {

    private static final int COLORS_SIZE = 20;
    private static final int DEFAULT_SIZE = 6;
    private final JButton editColors;
    private final JPanel colorsPalette;
    private int currentColorIndex;
    private final ArrayList<JButton> colorButtons;
    private final DrawingPanel drawingPanel;

    ColorsPanel(DrawingPanel frame) {
        this.drawingPanel = frame;
        currentColorIndex = DEFAULT_SIZE;
        colorButtons = new ArrayList<>(COLORS_SIZE);

        editColors = new JButton("Edit Colors");
        colorsPalette = new JPanel();
        initializeColorButtons();

        colorsPalette.setLayout(new GridLayout(2, 5, 5, 5));

        editColors.addActionListener(e -> editColorAction());

        setBorder(new TitledBorder("Colors"));
        setLayout(new BorderLayout(10, 10));
        add(colorsPalette, BorderLayout.WEST);
        add(editColors, BorderLayout.EAST);
    }

    /**
     * Creates a color button with the specified label and background color.
     *
     * @param label the text label of the button
     * @param color the background color of the button
     * @return a JButton with the specified properties
     */
    private JButton createColorButton(Color color) {
        JButton button = new JButton();
        button.setPreferredSize(new java.awt.Dimension(40, 40));
        button.setBackground(color);
        button.addActionListener(new ColorChange());
        return button;
    }

    /**
     * Initializes the color buttons with default and white colors.
     */
    private void initializeColorButtons() {
        // Create initial color buttons
        colorButtons.add(createColorButton(Color.BLACK));
        colorButtons.add(createColorButton(Color.RED));
        colorButtons.add(createColorButton(Color.GREEN));
        colorButtons.add(createColorButton(Color.BLUE));
        colorButtons.add(createColorButton(Color.CYAN));
        colorButtons.add(createColorButton(Color.WHITE));

        // Fill remaining buttons with white color
        for (int i = colorButtons.size(); i < COLORS_SIZE; i++) {
            colorButtons.add(createColorButton(Color.WHITE));
        }

        // Add buttons to panel
        for (JButton button : colorButtons) {
            colorsPalette.add(button);
        }
    }

    private void editColorAction() {
        Color selectedColor = JColorChooser.showDialog(null, "Edit Color", Color.white);
        // System.out.println(colorButtons.indexOf());
        if (selectedColor != null && !isAlreadyAdded(selectedColor)) {
            colorButtons.get(currentColorIndex).setBackground(selectedColor);
            currentColorIndex++;
            if (currentColorIndex == COLORS_SIZE) {
                currentColorIndex = DEFAULT_SIZE;
            }
        }
    }

    /**
     * Checks if the specified color is already added to the color buttons.
     *
     * @param color the color to check
     * @return true if the color is already added, false otherwise
     */
    private boolean isAlreadyAdded(Color color) {
        for (JButton button : colorButtons) {
            if (button.getBackground().equals(color)) {
                return true;
            }
        }
        return false;
    }

    class ColorChange implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentColor(((JButton) e.getSource()).getBackground());
        }
    }
}
