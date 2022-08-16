package Shapes;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x) {
        this(x, 0);
    }

    public Point() {
        this(0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", this.getX(), this.getY());
    }
}
