package Shapes;

public class Circle extends Shape {

    private double radius;
    private double area;

    /**
     * Init circle
     * 
     * @param center center point
     * @param radius radius of a circle
     * @param color  color of a circle
     */
    public Circle(Point center, double radius, String color) {
        super(center, color);
        this.radius = radius;
        this.area = area();
    }

    /**
     * Init circle
     * 
     * @param center      center point
     * @param circlePoint point located on the circle
     * @param color       color of a circle
     */
    public Circle(Point center, Point circlePoint, String color) {
        this(center, Shape.distance(center, circlePoint), color);
        this.area = area();
    }

    /**
     * Init circle with radius = 1, center point (0, 0) and color = black
     */
    public Circle() {
        super();
        this.radius = 1;
        this.area = area();
    }

    @Override
    public String toString() {
        return String.format("%s, center: %s, radius: %.2f, area: %.2f\n", super.toString(), super.getBase(),
                this.radius,
                this.area);
    }

    public double getArea() {
        return this.area;
    }

    private double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}
