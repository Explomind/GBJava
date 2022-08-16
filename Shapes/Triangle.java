package Shapes;

import java.util.HashMap;
import java.util.Map;

public class Triangle extends Shape {
    private Point secondNode;
    private Point thirdNode;
    private int type;
    private double area;
    private static final Map<Integer, String> types = new HashMap<>(4);

    static {
        types.put(0, "Isosceles");
        types.put(1, "Right");
        types.put(2, "Equilateral");
        types.put(3, "Random");
    }

    /**
     * Init random triangle
     * 
     * @param firstNode  first node
     * @param secondNode second node
     * @param thirdNode  third node
     * @param color      color of a triangle
     */
    public Triangle(Point firstNode, Point secondNode, Point thirdNode, String color) {
        super(firstNode, color);
        this.secondNode = secondNode;
        this.thirdNode = thirdNode;
        this.type = 3;
        this.area = area();
    }

    /**
     * Init isosceles/right triangle
     * 
     * @param firstNode base node
     * @param base      length of the base of a triangle
     * @param edge      triangle edge length
     * @param type      0 - isosceles triangle, 1 - right triangle
     * @param color     color
     */
    public Triangle(Point firstNode, double base, double edge, int type, String color) {
        this(firstNode, new Point(firstNode.getX() + base, firstNode.getY()),
                new Point(firstNode.getX() + (base - base * type) / 2,
                        firstNode.getY() + 0.866 * edge + 0.134 * edge * type),
                color);
        this.type = type;
        this.area = area();
    }

    /**
     * Init equilateral triangle
     * 
     * @param firstNode base node
     * @param edge      triangle edge length
     */
    public Triangle(Point firstNode, double edge, String color) {
        this(firstNode, edge, edge, 0, color);
        this.type = 2;
        this.area = area();
    }

    public String getType() {
        return types.get(this.type);
    }

    @Override
    public String toString() {
        return String.format("%s, type: %s, area: %.2f\n", super.toString(), this.getType(), this.area);
    }

    public double getArea() {
        return this.area;
    }

    private double area() {
        double a = Shape.distance(super.getBase(), this.secondNode);
        double b = Shape.distance(super.getBase(), this.thirdNode);
        double c = Shape.distance(this.secondNode, this.thirdNode);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
