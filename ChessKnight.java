// Шахматную доску размером NxN обойти конём так, чтобы фигура в каждой клетке была строго один раз.

public class ChessKnight {
    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    static int[] newTurn(int number, int[] point) { // подбирает возможный следующий ход коня
        int[] result = new int[2];
        switch (number) {
            case 0:
                result[0] = point[0] + 1;
                result[1] = point[1] + 2;
                return result;
            case 1:
                result[0] = point[0] + 2;
                result[1] = point[1] + 1;
                return result;
            case 2:
                result[0] = point[0] + 2;
                result[1] = point[1] - 1;
                return result;
            case 3:
                result[0] = point[0] + 1;
                result[1] = point[1] - 2;
                return result;
            case 4:
                result[0] = point[0] - 1;
                result[1] = point[1] - 2;
                return result;
            case 5:
                result[0] = point[0] - 2;
                result[1] = point[1] - 1;
                return result;
            case 6:
                result[0] = point[0] - 2;
                result[1] = point[1] + 1;
                return result;
            case 7:
                result[0] = point[0] - 1;
                result[1] = point[1] + 2;
                return result;
            default:
                return result;
        }
    }

    static boolean checkTurn(int[][] route, int[] point, int boardSize, int qTurns) {
        if (point[0] >= 0 && point[0] < boardSize && point[1] >= 0 && point[1] < boardSize) { // проверка хода на выход
                                                                                              // за пределы доски
            for (int i = qTurns - 1; i >= 0; i--) {
                if (route[i][0] == point[0] && route[i][1] == point[1]) {  // проверка хода на уникальность
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static boolean seekRoute(int[][] route, int[] point, int boardSize, int qTurns, boolean solved) {
        for (int i = 0; i < 8; i++) { // 8 возможных ходов конем из центра доски
            if (!solved) { 
                int[] newPoint = newTurn(i, point);
                if (checkTurn(route, newPoint, boardSize, qTurns)) {
                    route[qTurns] = newPoint;
                    if (qTurns < route.length - 1) {  // если номер текущего хода меньше длины маршрута, ищем дальше 
                        solved = seekRoute(route, newPoint, boardSize, qTurns + 1, solved);    
                    } else {
                        return true;
                    }
                }
            } else {
                return true;
            }
        }
        return solved;
    }

    public static void main(String[] args) {
        int boardSize = 5; // размер доски (5х5)
        int[] startPoint = new int[] { boardSize / 2, boardSize / 2 }; // стартовая позиция коня в центре доски
        int[][] route = new int[boardSize * boardSize][2]; // маршрут коня строится в виде последовательности координат клеток
        route[0] = startPoint;
        if (seekRoute(route, startPoint, boardSize, 1, false)) {
            System.out.println("Маршрут построен!");
            for (int i = 0; i < route.length; i++) {
                System.out.printf("Ход №" + i + ":  ");
                printArray(route[i]);
            }    
        } else {
            System.out.println("Маршрут не найден.");
        }
    }
}
