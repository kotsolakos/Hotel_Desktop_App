/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel_desktop_app;

/**
 *
 * @author user
 */
public class SharedData {
    private static byte[] imageData = null;
    
    public static void setImageData (byte[] newArray) {
        imageData = newArray;
    }
    
    public static byte[] getImageData () {
        return(imageData);
    }
}
