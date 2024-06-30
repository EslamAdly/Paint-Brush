/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.brush;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ESLAM
 */
public class CoordinatesBox extends JPanel {
    JLabel coordinates;
    public CoordinatesBox() {
        coordinates =new JLabel("Location: 0, 0 px");
        this.add(coordinates);
        this.setBorder(new TitledBorder(""));
    }

    public JLabel getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int x,int y) {
        this.coordinates.setText(String.format("Location: %d, %d px", x, y));
    }
    
}
