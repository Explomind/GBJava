package Shapes_v2;

import java.util.List;

public interface Shape extends Comparable<Shape> {

    public abstract double area();

    public abstract String name();

    public static double distance(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
    }

    public static Shape maxAreaShape(List<Shape> shapeList) {
        double maxArea = shapeList.get(0).area();
        Shape res = shapeList.get(0);
        for (int i = 1; i < shapeList.size(); i++) {
            if (shapeList.get(i).area() > maxArea) {
                maxArea = shapeList.get(i).area();
                res = shapeList.get(i);
            }
        }
        return res;
    }

    @Override
    default int compareTo(Shape s) {
        if (this.area() > s.area())
            return 1;
        else if (this.area() < s.area())
            return -1;
        else
            return 0;
    }
}
