
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends javax.swing.JComponent
{
    private BufferedImage img;
/**
* конструктор класса изображения
*
 */
    public JImageDisplay(int width, int height) {
        int TYPE_INT_RGB = 1;
        img = new BufferedImage(width, height, TYPE_INT_RGB);
        Dimension SIZE = new Dimension(width, height);
        setPreferredSize(SIZE);
    }


    @Override
    protected void paintComponent (Graphics g) {
        g.drawImage (img, 0, 0, img.getWidth(), img.getHeight(), null);
    }


    public void clearImage() {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                img.setRGB(i, j, 0);
            }
        }
    }


    public void drawPixel(int x, int y, int rgbColor) {
        img.setRGB(x, y, rgbColor);
    }
}
