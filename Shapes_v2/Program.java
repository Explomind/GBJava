// Задание: 
// Разработать программу, позволяющую получить информацию о совокупности цветных геометрических фигур 
// и определить фигуру с максимальной площадью.
// По мере работы фантазии можно добавить и другие фигуры

package Shapes_v2;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        Circle circle1 = new Circle(new Point(), 3, Color.Red);
        Circle circle2 = new Circle(new Point(), new Point(3), Color.Red);
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
        System.out.printf("Shape with max area:\n%s\n", Shape.maxAreaShape(shapeList));
        System.out.println();

        char c;
        int k = 0;
        int m = 4;
        switch (shapeList.get(k).compareTo(shapeList.get(m))) {
            case 1:
                c = '>';
                break;
            case -1:
                c = '<';
                break;
            default:
                c = '=';
                break;
        }
        System.out.printf("Area of %s %c area of %s\n", shapeList.get(k).name(), c,
                shapeList.get(m).name());
        System.out.println();

        System.out.println(circle1.equals(circle2));

        Polygon square3 = new Polygon();
        System.out.println(square3);
        System.out.println(square2.equals(square3));
        System.out.println();

        System.out.println("Circle2");
        while (circle2.hasNext()) {
            System.out.println(circle2.next());
        }
        System.out.println();

        System.out.println("Rectangle");
        while (rectangle.hasNext()) {
            System.out.println(rectangle.next());
        }
    }
}
