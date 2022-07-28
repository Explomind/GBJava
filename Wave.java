import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Wave {

    static class Point {
        public int x;
        public int y;
        public int value;
        public ArrayList<Integer> neighbours;

        public Point(int x, int y, int value, ArrayList<Integer> neighbours) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.neighbours = neighbours;
        }

        void addNeighbour(int neighbourIndex) {
            neighbours.add(neighbourIndex);
        }

        void setValue(int newValue) {
            value = newValue;
        }
    }

    static void seekNeighbours(ArrayList<Point> field, Point thisPoint) {// поиск соседних ячеек
        if (thisPoint.y - 1 >= 0) { // соседняя ячейка слева
            thisPoint.addNeighbour(field.indexOf(thisPoint) - 1);
            // if (thisPoint.x - 1 >= 0) {
            //     thisPoint.addNeighbour(field.indexOf(thisPoint) - fieldWidth - 1); // ячейка слева сверху
            // }
        }
        if (thisPoint.x - 1 >= 0) { // соседняя ячейка сверху
            thisPoint.addNeighbour(field.indexOf(thisPoint) - fieldWidth);
            // if (thisPoint.y + 1 < fieldWidth) {
            //     thisPoint.addNeighbour(field.indexOf(thisPoint) - fieldWidth + 1); // ячейка справа сверху
            // }
        }
        if (thisPoint.y + 1 < fieldWidth) { // соседняя ячейка справа
            thisPoint.addNeighbour(field.indexOf(thisPoint) + 1);
            // if (thisPoint.x + 1 < fieldHeight) {
            //     thisPoint.addNeighbour(field.indexOf(thisPoint) + fieldWidth + 1); // ячейка справа снизу    
            // }
        }
        
        if (thisPoint.x + 1 < fieldHeight) { // соседняя ячейка снизу
            thisPoint.addNeighbour(field.indexOf(thisPoint) + fieldWidth);
            // if (thisPoint.y - 1 >= 0) {
            //     thisPoint.addNeighbour(field.indexOf(thisPoint) + fieldWidth + 1); // ячейка слева снизу
            // }
        }
    }

    static int[] startFinishGen() {
        Random rand = new Random();
        int iStart = rand.nextInt(fieldHeight * fieldWidth); // генерация стартовой ячейки
        int iFinish = iStart;
        while (iFinish == iStart) { // финишная ячейка не должна совпадать со стартовой
            iFinish = rand.nextInt(fieldHeight * fieldWidth);
        }
        return new int[] { iStart, iFinish };
    }

    static ArrayList<Point> fieldInit(int barrierProb, int iStart, int iFinish) {
        ArrayList<Point> field = new ArrayList<Point>();
        Random rand = new Random();
        int count = 0;
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if (rand.nextInt(100) < barrierProb) {
                    // непроходимая ячейка
                    field.add(new Point(i, j, -2, new ArrayList<Integer>()));
                } else {
                    // проходимая ячейка
                    field.add(new Point(i, j, -3, new ArrayList<Integer>()));
                }
                seekNeighbours(field, field.get(count)); // добавить для текущей ячейки список соседних
                count++;
            }
        }
        field.get(iStart).setValue(0); // добавить стартовую ячейку
        field.get(iFinish).setValue(-1); // добавить финишную ячейку
        return field;
    }

    static void printField(ArrayList<Point> field) {
        int rowNumber = 0;
        for (int i = 0; i < field.size(); i++) {
            if (field.get(i).x > rowNumber) {
                rowNumber++;
                System.out.println();
                i--;
            } else {
                switch (field.get(i).value) {
                    case 0:
                        System.out.print("S\t"); // стартовая ячейка
                        break;
                    case -1:
                        System.out.print("F\t"); // финишная ячейка
                        break;
                    case -2:
                        System.out.print("X\t"); // непроходимая ячейка
                        break;
                    case -3:
                        System.out.print("-\t"); // проходимая ячейка
                        break;
                    case -4:
                        System.out.print("R\t"); // ячейка маршрута
                        break;
                    default:
                        System.out.print(field.get(i).value + "\t"); // помеченная ячейка
                        break;
                }
            }
        }
        System.out.println();
    }

    static boolean propagation(ArrayList<Point> field, int iStart, int iFinish) {
        Queue<Integer> waveSteps = new LinkedList<>();
        waveSteps.add(iStart);
        int stepIndex = 0;
        int neighbourIndex = 0;
        while (!waveSteps.isEmpty()) {
            stepIndex = waveSteps.poll();
            for (int i = 0; i < field.get(stepIndex).neighbours.size(); i++) {
                neighbourIndex = field.get(stepIndex).neighbours.get(i);
                if (neighbourIndex == iFinish) { // если финишная точка, то присвоить ей
                                                 // value = value предыдущей точки + 1
                    field.get(neighbourIndex).setValue(field.get(stepIndex).value + 1);
                    return true; // конец распространения волны
                }
                if (field.get(neighbourIndex).value == -3) { // если точка проходимая, то присвоить ей
                    field.get(neighbourIndex).setValue(field.get(stepIndex).value + 1); // value = value предыдущей
                                                                                        // точки + 1
                    waveSteps.add(neighbourIndex); // и добавить ее индекс в очередь
                }
            }
        }
        return false;
    }

    static Deque<Integer> seekRoute(ArrayList<Point> field, int iStart, int iFinish) {
        Deque<Integer> route = new ArrayDeque<>();
        route.addFirst(iFinish);
        int stepIndex = iFinish;
        int neighbourIndex = 0;
        while (stepIndex != iStart) {
            for (int i = 0; i < field.get(stepIndex).neighbours.size(); i++) {
                neighbourIndex = field.get(stepIndex).neighbours.get(i);
                if (field.get(neighbourIndex).value == field.get(stepIndex).value - 1) {
                    route.addFirst(neighbourIndex);
                    stepIndex = neighbourIndex;
                    continue;
                }
            }
        }
        return route;
    }

    static void setRoute(ArrayList<Point> field, Deque<Integer> route) {
        field.get(route.pollFirst()).setValue(0); // 1я точка пути - стартовая
        field.get(route.pollLast()).setValue(-1); // последняя точка пути - финишная
        while (!route.isEmpty()) {
            field.get(route.pollFirst()).setValue(-4); // точки пути
        }
    }

    static void cleanField(ArrayList<Point> field) {
        for (int i = 0; i < field.size(); i++) {
            if (field.get(i).value > 0) {
                field.get(i).setValue(-3);
            }
        }
    }

    public static int fieldHeight = 10; // высота поля
    public static int fieldWidth = 10; // ширина поля

    public static void main(String[] args) {
        int barrierProbability = 20; // вероятность генерации непроходимой ячейки
        int[] startFinish = startFinishGen(); // генерация стартовой и финишной ячеек
        ArrayList<Point> myField = fieldInit(barrierProbability, startFinish[0], startFinish[1]); // генерация поля
        System.out.println("Инициализация поля (S - старт, F - финиш, X - препятствие):");
        printField(myField);
        System.out.println();
        if (propagation(myField, startFinish[0], startFinish[1])) { // если волна доходит до финиша
            System.out.println("Поле после распространения волны:");
            printField(myField);
            System.out.println();
            Deque<Integer> myRoute = seekRoute(myField, startFinish[0], startFinish[1]); // поиск пути
            System.out.printf("Старт: %d, финиш: %d\n", startFinish[0], startFinish[1]);
            System.out.println("Маршрут: " + myRoute);
            setRoute(myField, myRoute); // интеграция пути в поле
            cleanField(myField); // очистка поля после распространения волны
            System.out.println("Поле с маршрутом (S - старт, F - финиш, X - препятствие, R - путь):");
            printField(myField);
        } else {
            System.out.println("Ортогональный путь не найден.");
        }
    }
}
