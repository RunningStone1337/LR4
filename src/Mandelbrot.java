import java.awt.geom.Rectangle2D;

/**
 * подкласс для фракталов Мандельброта
 */
public class Mandelbrot extends FractalGenerator {

    public static final int MAX = 2000;


    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        // Изменение полей прямоугольника
        range.x = -2.0;
        range.y = -1.5;
        range.height = 3.0;
        range.width = 3.0;
    }


    @Override
    public int numIterations(double x, double y) {
        ComplexNumber compN1 = new ComplexNumber(x, y);
        ComplexNumber compN2 = new ComplexNumber();
        int iterations = 0;


        while (compN2.getX() * compN2.getX() + compN2.getY() * compN2.getY() <= 4 && iterations < MAX) {
            double tempX = compN2.getX();
            compN2.setX(compN2.getX() * compN2.getX() + compN1.getX() - compN2.getY() * compN2.getY());
            compN2.setY(2 * tempX * compN2.getY() + compN1.getY());
            iterations++;
        }
        if (iterations >= MAX) return -1;
        else return iterations;
    }
}