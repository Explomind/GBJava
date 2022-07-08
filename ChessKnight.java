// Шахматную доску размером NxN обойти конём так, чтобы фигура в каждой клетке была строго один раз.

import java.util.ArrayList;
import java.util.Scanner;

public class ChessKnight {
    static void printArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        // Scanner iScanner = new Scanner(System.in);
        // iScanner.nextLine();
        // iScanner.close();
    }

    static Integer[] NewTurn(int number, Integer[] point) {   // подбирает возможный следующий ход коня
        Integer[] result = point;
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

    static boolean CheckTurn(ArrayList<Integer[]> route, Integer[] point, int boardSize) {   
        System.out.println(point[0] + " " + point[1]);                                      
        if ( point[0] >= 0 && point[0] < boardSize && point[1] >= 0 && point[1] < boardSize) {  // проверка хода на выход за пределы доски
            System.out.println("Point is on board");
            for (Integer[] item : route) {
                System.out.printf("route[0]: %d route[1]: %d\n", item[0], item[1]);
                if (item[0] != point[0] && item[1] != point[1]) {   // проверка на уникальность хода
                    return true;
                }
            }
        }
        return false;
    }

    static int SeekRoute(ArrayList<Integer[]> route, Integer[] point, int boardSize, int qTurns) {
        for (int i = 0; i < 8; i++) {   // 8 возможных ходов конем из центра доски
            if (qTurns < boardSize * boardSize - 1) {  // общее кол-во ходов на 1 меньше, чем клеток на доске
                if (CheckTurn(route, NewTurn(i, point), boardSize)) {
                    point = NewTurn(i, point);
                    route.add(point);
                    for (Integer[] item : route) {   // для отладки
                            printArray(item);
                        }
                    qTurns = SeekRoute(route, point, boardSize, qTurns + 1);
                }
            } else {
                return qTurns;
            }
            
        }
        return qTurns;
    }

    public static void main(String[] args) {
        int boardSize = 5;
        Integer[] startPoint = new Integer[] { boardSize / 2, boardSize / 2 };  // стартовая позиция коня в центра доски
        ArrayList<Integer[]> route = new ArrayList<>(boardSize * boardSize);
        route.add(startPoint);
        // SeekRoute(route, startPoint, boardSize, 0);
        System.out.print("Start point: ");
        for (Integer[] item : route) {
            printArray(item);
        }
        System.out.println();
        // printArray(startPoint);
        int i = 0;
        // System.out.printf("NewTurn(%d, startPoint): ", i);
        // printArray(NewTurn(i, startPoint));
        // System.out.println(CheckTurn(route, NewTurn(0, startPoint), boardSize));
        System.out.printf("CheckTurn(route, NewTurn(%d, startPoint), boardSize: ", i);
        CheckTurn(route, NewTurn(i, startPoint), boardSize);
    }
}
