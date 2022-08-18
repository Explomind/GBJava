package Shapes_v2;

import java.util.List;

public interface Shape {

    public abstract double area();

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
}
