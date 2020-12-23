/**
 * Класс для хранения комплексных чисел
 */
public class ComplexNumber {

    // переменные для комплексной и действительной части
    public double x; // действительная часть
    public double y; // комплексная часть


    ComplexNumber() {
        this.x = 0.0;
        this.y = 0.0;
    }


    ComplexNumber(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}