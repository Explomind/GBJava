
public class Queens {
    static boolean checkPos(int[] array, int row, int column) {
        if (column > 0) {
            for (int i = column - 1; i >= 0; i--) {
                if (array[i] == row || array[i] - i == row - column || array[i] + i == row + column) {
                    return true;
                }
            }
        }
        return false;
    }

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    static boolean setQueens(int[] array, int k, boolean solved) {
        for (int m = 0; m < array.length; m++) {
            if (solved) {
                return true;
            } else {
                if (!checkPos(array, m, k)) {
                    array[k] = m;
                    if (k < array.length - 1) {
                        solved = setQueens(array, k + 1, solved);
                    } else {
                        return true;
                    }
                }
            }
        }
        return solved;
    }

    public static void main(String[] args) {
        int[] chessBoard = new int[8];
        if (setQueens(chessBoard, 0, false)) {
            System.out.println("Solution found!");
            printArray(chessBoard);
        } else {
            System.out.println("No solution found.");
        }
    }
}
