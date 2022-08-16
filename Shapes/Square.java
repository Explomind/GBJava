package Shapes;

public class Square extends Shape {
    private Point diagPoint;
    private double area;

    /**
     * Init square
     * 
     * @param basePoint base node
     * @param diagPoint node located diagonally from the base
     * @param color     color of a square
     */
    public Square(Point basePoint, Point diagPoint, String color) {
        super(basePoint, color);
        this.diagPoint = diagPoint;
        this.area = area();
    }

    /**
     * Init square
     * 
     * @param node  base node
     * @param edge  square edge length
     * @param color color of a square
     */
    public Square(Point node, double edge, String color) {
        this(node, new Point(node.getX() + edge, node.getY() + edge), color);
        this.area = area();
    }

    /**
     * Init square with base point (0, 0), edge length = 1 and color = black
     */
    public Square() {
        this(new Point(), 1, "black");
        this.area = area();
    }

    public double getEdge() {
        return Math.sqrt(Math.pow(Shape.distance(super.getBase(), this.diagPoint), 2) / 2);
    }

    @Override
    public String toString() {
        return String.format("%s, edge: %.2f, area: %.2f\n", super.toString(), this.getEdge(), this.area);
    }

    public double getArea() {
        return this.area;
    }

    private double area() {
        return Math.pow(this.getEdge(), 2);
    }
}
