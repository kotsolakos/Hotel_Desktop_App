/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel_desktop_app;

/**
 *
 * @author user
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class Hotel_Desktop_App {
    private static byte[] imageData = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hotel Desktop App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create content for the first tab
        JPanel tab1Content = new JPanel();
        tab1Content.setLayout(new BorderLayout());
        JPanel stackedPanels1 = StackedPanel.createStackedPanels();
        tab1Content.add(stackedPanels1, BorderLayout.CENTER);
        tabbedPane.addTab("All Rooms", tab1Content);

        // Create content for the second tab
        JPanel tab2Content = new JPanel();
        tab2Content.setLayout(new BorderLayout());
        JPanel stackedPanels2 = StackedPanel.createStackedPanelsFiltered("A");
        tab2Content.add(stackedPanels2, BorderLayout.CENTER);
        tabbedPane.addTab("Available Rooms", tab2Content);

        // Create content for the third tab
        JPanel tab3Content = new JPanel();
        tab3Content.setLayout(new BorderLayout());
        JPanel stackedPanels3 = StackedPanel.createStackedPanelsFiltered("B");
        tab3Content.add(stackedPanels3, BorderLayout.CENTER);
        tabbedPane.addTab("Booked Rooms", tab3Content);
        
        // Create content for the fourth tab
        JPanel tab4Content = new JPanel();
        tab4Content.setLayout(new BorderLayout());
        JPanel stackedPanels4 = StackedPanel.createInsertStackedPanels(imageData);
        tab4Content.add(stackedPanels4, BorderLayout.CENTER);
        tabbedPane.addTab("Add new Room", tab4Content);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
