package hotel_desktop_app;

/**
 * @author Kotsolakos Konstantinos
 **/

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    public static Image convertByteArrayToImage(byte[] byteArray) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            BufferedImage bufferedImage = ImageIO.read(bis);
            return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static byte[] convertImageToByteArray(File imageFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            byte[] imageData = new byte[(int) imageFile.length()];
            fis.read(imageData);
            return imageData;
        }
    }

}
