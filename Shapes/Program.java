// Задание: 
// Разработать программу, позволяющую получить информацию о совокупности цветных геометрических фигур 
// и определить фигуру с максимальной площадью.
// * Родительский класс Shape 
// * Circle для представления окружности;
// * Square для представления квадрата;
// * Triangle для представления треугольника
// Для указания цвета фигур Color.
// Для представления точек с координатами (x,y) Point.

// По мере работы фантазии можно добавить и другие фигуры

package Shapes;

public class Program {
    public static void main(String[] args) {

        ShapeCol shapeCol = new ShapeCol();

        Circle circle1 = new Circle(new Point(2, 2), 5, "red");
        shapeCol.addShape(circle1, circle1.getArea());

        Circle circle2 = new Circle(new Point(), new Point(2), "blue");
        shapeCol.addShape(circle2, circle2.getArea());

        Circle circle3 = new Circle();
        shapeCol.addShape(circle3, circle3.getArea());

        Square square1 = new Square(new Point(), 4, "green");
        shapeCol.addShape(square1, square1.getArea());

        Square square2 = new Square(new Point(), new Point(1, 2), "purple");
        shapeCol.addShape(square2, square2.getArea());

        Square square3 = new Square();
        shapeCol.addShape(square3, square3.getArea());

        Triangle triangle1 = new Triangle(new Point(), new Point(4), new Point(1, 3), "violet");
        shapeCol.addShape(triangle1, triangle1.getArea());

        Triangle triangle2 = new Triangle(new Point(), 5, "yellow");
        shapeCol.addShape(triangle2, triangle2.getArea());

        Triangle triangle3 = new Triangle(new Point(), 3, 4, 0, "green");
        shapeCol.addShape(triangle3, triangle3.getArea());

        Triangle triangle4 = new Triangle(new Point(), 3, 4, 1, "red");
        shapeCol.addShape(triangle4, triangle4.getArea());

        shapeCol.showCol();
        System.out.println();
        System.out.printf("Shape with max area:\n%s\n", shapeCol.maxAreaShape());
    }
}
