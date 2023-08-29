/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_desktop_app;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InsertPanel {
    private static byte[] imageData = null;
    
    public static JPanel createInsertPanel(JTextArea descTextArea, JTextField priceField, JTextField reservedField) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        JButton uploadButton = new JButton("Upload Image");
        JLabel imageLabel = new JLabel();
        
        ImageUtils imgUtils = new ImageUtils();
        
        
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(image));
                    uploadButton.setVisible(false); // Hide the upload button
                    
                    // Convert image data to byte array and display on console
                    try {
                        imageData = imgUtils.convertImageToByteArray(selectedFile);
                        SharedData.setImageData(imageData);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        mainPanel.add(uploadButton);
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(descTextArea);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(priceField);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.add(reservedField);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        mainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Set the maximum height to 100 pixels
        mainPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100)); // Set the preferred height to 100 pixels

        return mainPanel;
    }
}
