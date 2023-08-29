/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_desktop_app;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class MainPanel {
    public static JPanel createMainPanel(Image convertedImage, String description, String price, String reserved) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        ImageIcon imageIcon = new ImageIcon(convertedImage);
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true); // Wrap text at word boundaries
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setEditable(false); // Make the JTextArea non-editable
        textArea.setText(description);

        JTextField textField1 = new JTextField(10);
        textField1.setText(price);
        textField1.setEditable(false);
        
        JTextField textField2 = new JTextField(10);
        textField2.setText(reserved);
        textField2.setEditable(false);

        mainPanel.add(imageLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(textArea);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(textField1);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(textField2);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Set the maximum height to 100 pixels
        mainPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100)); // Set the preferred height to 100 pixels

        return mainPanel;
    }
    
    public static JPanel createMainPanelFiltered(String filter, int id, Image convertedImage, String description, String price, String reserved) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        ImageIcon imageIcon = new ImageIcon(convertedImage);
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        
        JTextArea descTextArea = new JTextArea();
        descTextArea.setWrapStyleWord(true); // Wrap text at word boundaries
        descTextArea.setLineWrap(true); // Enable line wrapping
        descTextArea.setEditable(false); // Make the JTextArea non-editable
        descTextArea.setText(description);

        JTextField priceField = new JTextField(10);
        priceField.setText(price);
        if(filter.equals("B")){
            priceField.setEditable(false);
        }
                
        JTextField reservedField = new JTextField(10);
        reservedField.setText(reserved);        
        
        JButton updateButton = new JButton("Update");
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFromPriceField = priceField.getText();
                String textFromReservedArea = reservedField.getText();
                DatabaseHandler.update_hotel_rooms_and_users(textFromPriceField, textFromReservedArea, id, reserved);
            }            
        });

        mainPanel.add(imageLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(descTextArea);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(priceField);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(reservedField);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(updateButton);
        
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Set the maximum height to 100 pixels
        mainPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100)); // Set the preferred height to 100 pixels

        return mainPanel;
    }
}
