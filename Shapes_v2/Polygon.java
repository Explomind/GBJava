package Shapes_v2;

import java.util.Iterator;
import java.util.List;

public class Polygon implements Shape, Iterator<String> {
    private List<Point> nodes;
    private Type type;
    private Color color;

    /**
     * Init triangle
     * 
     * @param A     point A
     * @param B     point B
     * @param C     point C
     * @param color color of a triangle
     */
    public Polygon(Point A, Point B, Point C, Color color) {
        this.nodes = List.of(A, B, C);
        this.type = Type.Triangle;
        this.color = color;
    }

    /**
     * Init rectangle
     * 
     * @param A          base node
     * @param firstEdge  length of the first edge of a rectangle
     * @param secondEdge length of the second edge of a rectangle
     * @param color      color of a rectangle
     */
    public Polygon(Point A, int firstEdge, int secondEdge, Color color) {
        this.nodes = List.of(A, new Point(A.getX() + firstEdge, A.getY()),
                new Point(A.getX() + firstEdge, A.getY() + secondEdge),
                new Point(A.getX(), A.getY() + secondEdge));
        this.type = Type.Rectangle;
        this.color = color;
    }

    /**
     * Init square
     * 
     * @param A     base node
     * @param edge  square edge length
     * @param color color of a square
     */
    public Polygon(Point A, int edge, Color color) {
        this(A, edge, edge, color);
        this.type = Type.Square;
    }

    /**
     * Init square with base point (0, 0), edge length = 1 and color = black
     */
    public Polygon() {
        this(new Point(), 1, Color.Black);
        this.type = Type.Square;
    }

    public List<Point> getNodes() {
        return nodes;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("%s, color: %s, area: %.2f", this.type, this.color, this.area());
    }

    @Override
    public double area() {
        double AB = Shape.distance(nodes.get(0), nodes.get(1));
        double AC = Shape.distance(nodes.get(0), nodes.get(2));
        double BC = Shape.distance(nodes.get(1), nodes.get(2));
        switch (this.nodes.size()) {
            case 3:
                double p = (AB + AC + BC) / 2;
                return Math.sqrt(p * (p - AB) * (p - AC) * (p - BC));
            case 4:
                return AB * BC;
            default:
                return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Polygon p = (Polygon) obj;
        if (this.type != p.getType()) {
            return false;
        }
        for (int i = 0; i < this.nodes.size(); i++) {
            if (!this.nodes.get(i).equals(p.nodes.get(i))) {
                return false;
            }
        }
        if (this.color == p.getColor()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String name() {
        return this.getType().toString();
    }

    int index;

    @Override
    public boolean hasNext() {
        return index++ < 3;
    }

    @Override
    public String next() {
        switch (index) {
            case 1:
                return String.format("Nodes: %s", nodes);
            case 2:
                return String.format("Type: %s", type);
            default:
                return String.format("Color: %s", color);
        }
    }
}
