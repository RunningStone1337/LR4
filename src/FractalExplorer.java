import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * Класс пользовательского интерфейса для отображения фракталов
 */
public class FractalExplorer {
    private final Rectangle2D.Double aDouble;
    private final FractalGenerator fractalGenerator;
    private JImageDisplay imageDisplay;
    private final int size;


    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(550);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }


    public FractalExplorer (int size){
        if (size <= 0) throw new IllegalArgumentException("Size must be better than 0; got " + size);
        else this.size = size;
        aDouble = new Rectangle2D.Double();
        new Mandelbrot().getInitialRange(aDouble);
        fractalGenerator = new Mandelbrot();
    }


    private void createAndShowGUI() {
        JFrame frame = new JFrame("Fractals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        fractalGenerator.getInitialRange(aDouble);

        contentPane.setLayout(new BorderLayout());
        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new MyMouseListener().mouseListener);
        contentPane.add(imageDisplay, BorderLayout.CENTER);

        JButton resetButton = new JButton("Сбросить");
        resetButton.addActionListener(e -> {
                    imageDisplay.clearImage();
                    fractalGenerator.getInitialRange(aDouble);
                    drawFractal();
                }
        );
        contentPane.add(resetButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }


    private void drawFractal() {
        double xCoord;
        double yCoord;
        int numIters;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                xCoord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, i);
                yCoord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, j);
                numIters = fractalGenerator.numIterations(xCoord, yCoord);
                if (numIters == -1) imageDisplay.drawPixel(i, j, 0);
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(i, j, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }


    private class MyMouseListener extends MouseAdapter {

        public MouseListener mouseListener = new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                if (size >= 0) {
                    double xCord;
                    double yCord;
                    xCord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, e.getX());
                    yCord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, e.getY());
                    FractalGenerator.recenterAndZoomRange(aDouble, xCord, yCord, 0.5);
                    drawFractal();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        };

    }


}