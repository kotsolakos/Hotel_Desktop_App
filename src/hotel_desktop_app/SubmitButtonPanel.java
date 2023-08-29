/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_desktop_app;

/**
 * @author Kotsolakos Konstantinos
 **/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SubmitButtonPanel {
    public static JPanel submitButtonPanel(JTextArea descTextArea, JTextField priceField, JTextField reservedField) {
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
                
        JButton submitButton = new JButton("Submit");
        
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFromDescTextArea = descTextArea.getText();
                String textFromPriceField = priceField.getText();
                String textFromReservedArea = reservedField.getText();
                byte[] imageData = SharedData.getImageData();
                
                dbHandler.insert_into_hotel_rooms(imageData, textFromDescTextArea, textFromPriceField, textFromReservedArea);
            }            
        });
        
        submitPanel.add(submitButton, BorderLayout.CENTER);
        
        submitPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Set the maximum height to 30 pixels
        submitPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30)); // Set the preferred height to 30 pixels

        return submitPanel;
    }
}
