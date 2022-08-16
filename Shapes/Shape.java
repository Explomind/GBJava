package Shapes;

public class Shape {
    private Point basePoint;
    private String color;

    /**
     * Init base shape
     * 
     * @param basePoint base point
     * @param color     color of a shape
     */
    public Shape(Point base, String color) {
        this.basePoint = base;
        this.color = color;
    }

    public Shape(Point base) {
        this(base, "black");
    }

    public Shape() {
        this(new Point());
    }

    public Point getBase() {
        return basePoint;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Shape: %s, color: %s", this.getClass().getSimpleName(), color);
    }

    public static double distance(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
    }
}
