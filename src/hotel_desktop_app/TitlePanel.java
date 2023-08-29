/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_desktop_app;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class TitlePanel {
    public static JPanel createTitlePanel() {        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));

        JLabel imageFieldLabel = new JLabel("IMAGE");
        Font font = imageFieldLabel.getFont(); // Get the current font
        Font biggerFont = font.deriveFont(Font.BOLD, 14); // Create a new font with increased size
        imageFieldLabel.setFont(biggerFont); // Set the new font        
        titlePanel.add(Box.createRigidArea(new Dimension(25, 0))); // 10 pixels gap
        titlePanel.add(imageFieldLabel);
        
        JLabel descriptionFieldLabel = new JLabel("DESCRIPTION");
        descriptionFieldLabel.setFont(biggerFont); // Set the new font        
        titlePanel.add(Box.createRigidArea(new Dimension(740, 0))); // 10 pixels gap
        titlePanel.add(descriptionFieldLabel);
        
        JLabel priceFieldLabel = new JLabel("PRICE");
        priceFieldLabel.setFont(biggerFont); // Set the new font
        titlePanel.add(Box.createRigidArea(new Dimension(795, 0))); // 10 pixels gap
        titlePanel.add(priceFieldLabel);
        
        JLabel reservedFieldLabel = new JLabel("RESERVED BY");
        reservedFieldLabel.setFont(biggerFont); // Set the new font
        titlePanel.add(Box.createRigidArea(new Dimension(54, 0))); // 10 pixels gap
        titlePanel.add(reservedFieldLabel);
        
        
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25)); // Set the maximum height to 100 pixels
        titlePanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 25)); // Set the preferred height to 100 pixels
        return titlePanel;
    }
}
