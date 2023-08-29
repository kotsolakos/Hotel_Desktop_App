/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_desktop_app;

import java.awt.Dimension;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class StackedPanel {
    public static JPanel createInsertStackedPanels(byte[] imageData) {
        JPanel stackedPanels = new JPanel();
        stackedPanels.setLayout(new BoxLayout(stackedPanels, BoxLayout.Y_AXIS));
        
        //Create the components here so that they can be passed through to other methods
        JTextArea descTextArea = new JTextArea();
        descTextArea.setWrapStyleWord(true); // Wrap text at word boundaries
        descTextArea.setLineWrap(true); // Enable line wrapping
        JTextField priceField = new JTextField(10);
        JTextField reservedField = new JTextField(10);
        InsertPanel insPanel = new InsertPanel();
        
        JPanel titlePanel = new TitlePanel().createTitlePanel(); // Create the Title Labels

        JPanel mainPanel = insPanel.createInsertPanel(descTextArea, priceField, reservedField); // Create the panel that inserts information here
        
        JPanel submitPanel = new SubmitButtonPanel().submitButtonPanel(descTextArea, priceField, reservedField);
                
        stackedPanels.add(titlePanel);
        stackedPanels.add(Box.createRigidArea(new Dimension(0, 5)));
        
        stackedPanels.add(mainPanel);
        stackedPanels.add(Box.createRigidArea(new Dimension(0, 5)));
        
        stackedPanels.add(submitPanel);

        return stackedPanels;
    }
    
    public static JPanel createStackedPanels() {
        JPanel stackedPanels = new JPanel();
        stackedPanels.setLayout(new BoxLayout(stackedPanels, BoxLayout.Y_AXIS));
        DatabaseHandler dbHandler = new DatabaseHandler();
        ImageUtils imgUtils = new ImageUtils();
        
        String description;
        String price;
        String reserved;
        byte[] imageD;
        
        try (Connection connection = dbHandler.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel_rooms");

            while (resultSet.next()) {
                imageD = resultSet.getBytes("image");
                description = resultSet.getString("description");
                price = resultSet.getString("price");
                reserved = resultSet.getString("reserved_by");
                
                Image convertedImage = imgUtils.convertByteArrayToImage(imageD);                
                JPanel mainPanel = MainPanel.createMainPanel(convertedImage, description, price, reserved); // Create the mainPanel here
                stackedPanels.add(mainPanel);
                stackedPanels.add(Box.createRigidArea(new Dimension(0, 5)));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return stackedPanels;
    }
    
    public static JPanel createStackedPanelsFiltered(String filter) {
        JPanel stackedPanels = new JPanel();
        stackedPanels.setLayout(new BoxLayout(stackedPanels, BoxLayout.Y_AXIS));
        DatabaseHandler dbHandler = new DatabaseHandler();
        ImageUtils imgUtils = new ImageUtils();
        
        String description;
        String price;
        String reserved;
        byte[] imageD;
        int id;
        
        if(filter.equals("A")){            
            try (Connection connection = dbHandler.getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel_rooms Where reserved_by = '' Or reserved_by is null");
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    imageD = resultSet.getBytes("image");
                    description = resultSet.getString("description");
                    price = resultSet.getString("price");
                    reserved = resultSet.getString("reserved_by");
                    
                    Image convertedImage = imgUtils.convertByteArrayToImage(imageD);                     
                    JPanel mainPanel = MainPanel.createMainPanelFiltered(filter, id, convertedImage, description, price, reserved); // Create the mainPanel here
                    stackedPanels.add(mainPanel);
                    stackedPanels.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else{
            try (Connection connection = dbHandler.getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel_rooms Where reserved_by <> ''");
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    imageD = resultSet.getBytes("image");
                    description = resultSet.getString("description");
                    price = resultSet.getString("price");
                    reserved = resultSet.getString("reserved_by");
                    
                    Image convertedImage = imgUtils.convertByteArrayToImage(imageD);
                    JPanel mainPanel = MainPanel.createMainPanelFiltered(filter, id, convertedImage, description, price, reserved); // Create the mainPanel here
                    stackedPanels.add(mainPanel);
                    stackedPanels.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return stackedPanels;
    }
}
