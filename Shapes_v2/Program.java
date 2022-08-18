// Задание: 
// Разработать программу, позволяющую получить информацию о совокупности цветных геометрических фигур 
// и определить фигуру с максимальной площадью.
// По мере работы фантазии можно добавить и другие фигуры

package Shapes_v2;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        Circle circle1 = new Circle(new Point(), 3, Color.Red);
        Circle circle2 = new Circle(new Point(1), new Point(3), Color.Green);
        Circle circle3 = new Circle();

        Polygon triangle = new Polygon(new Point(), new Point(2), new Point(1, 2), Color.Black);
        Polygon square = new Polygon(new Point(), 3, Color.Blue);
        Polygon rectangle = new Polygon(new Point(), 4, 8, Color.White);
        Polygon square2 = new Polygon();

        List<Shape> shapeList = List.of(circle1, circle2, circle3, triangle, square, rectangle, square2);
        for (Shape shape : shapeList) {
            System.out.println(shape);
        }
        System.out.println();
        System.out.println(Shape.maxAreaShape(shapeList)); 
    }
}
