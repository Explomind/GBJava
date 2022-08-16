package Shapes;

import java.util.ArrayList;

public class ShapeCol {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Double> areas = new ArrayList<>();

    public void addShape(Shape shape, double area) {
        shapes.add(shape);
        areas.add(area);
    }

    public Shape getShape(int index) {
        return shapes.get(index);
    }

    public double getArea(int index) {
        return areas.get(index);
    }

    public Shape maxAreaShape() {
        int maxAreaKey = 0;
        double maxArea = 0;
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i) > maxArea) {
                maxAreaKey = i;
                maxArea = areas.get(i);
            }
        }
        return shapes.get(maxAreaKey);
    }

    public void showCol() {
        for (int i = 0; i < shapes.size(); i++) {
            System.out.printf("%s", shapes.get(i));
        }
    }
}
