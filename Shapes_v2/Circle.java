package Shapes_v2;

public class Circle implements Shape {

    private Point center;
    private double radius;
    private Color color;

    /**
     * Init circle
     * 
     * @param center center point
     * @param radius radius of a circle
     * @param color  color of a circle
     */
    public Circle(Point center, double radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Init circle
     * 
     * @param center      center point
     * @param circlePoint point located on the circle
     * @param color       color of a circle
     */
    public Circle(Point center, Point circlePoint, Color color) {
        this(center, Shape.distance(center, circlePoint), color);
    }

    /**
     * Init circle with radius = 1, center point (0, 0) and color = black
     */
    public Circle() {
        this(new Point(), 1, Color.Black);
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return String.format("Circle, center: %s, radius: %.2f, color: %s, area = %.2f", this.center, this.radius,
                this.color, this.area());
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}
