import java.util.ArrayList;
import java.util.Random;

public class Wave {

    static class Point{
        public int x;
        public int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<ArrayList<Integer>> fieldInit(int height, int width, int barrierProb) {
        ArrayList<ArrayList<Integer>> field = new ArrayList<ArrayList<Integer>>();
        Random rand = new Random();
        int iStart = rand.nextInt(width);
        int jStart = rand.nextInt(height);
        int iFinish = iStart;
        int jFinish = jStart;
        while (iFinish == iStart && jFinish == jStart) { // финишная ячейка не должна совпадать со стартовой
            iFinish = rand.nextInt(width);
            jFinish = rand.nextInt(height);
        }
        System.out.printf("Start: %d %d\nFinish: %d %d\n", iStart, jStart, iFinish, jFinish);
        for (int i = 0; i < width; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                if (i == iStart && j == jStart) {
                    row.add(0); // стартовая ячейка
                    continue;
                }
                if (i == iFinish && j == jFinish) {
                    row.add(-1); // финишная ячейка
                    continue;
                }
                if (rand.nextInt(100) < barrierProb) {
                    row.add(-2); // непроходимая ячейка
                } else {
                    row.add(-3); // проходимая ячейка
                }
            }
            field.add(row);
        }
        return field;
    }

    static void printField(ArrayList<ArrayList<Integer>> field) {
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                if (field.get(i).get(j) == 0) {
                    System.out.print("S\t"); // стартовая ячейка
                }
                if (field.get(i).get(j) == -1) {
                    System.out.print("F\t"); // финишная ячейка
                }
                if (field.get(i).get(j) == -2) {
                    System.out.print("X\t"); // непроходимая ячейка
                }
                if (field.get(i).get(j) == -3) {
                    System.out.print("-\t"); // проходимая ячейка
                }
            }
            System.out.println();
        }
    }

    static void propagation(ArrayList<ArrayList<Integer>> field) {
        int iStart = 0;
        int jStart = 0;
        int iFinish = 0;
        int jFinish = 0;
        for (int i = 0; i < field.size(); i++) { // поиск стартовой и финишной ячеек
            if (field.get(i).contains(0)) {
                iStart = i;
                jStart = field.get(i).indexOf(0);
            }
            if (field.get(i).contains(-1)) {
                iFinish = i;
                jFinish = field.get(i).indexOf(-1);
            }
        }
        System.out.printf("Start: %d %d\nFinish: %d %d\n", iStart, jStart, iFinish, jFinish);
        for (int i = 0; i < array.length; i++) {
            
        }
    }

    public static void main(String[] args) {
        int fieldHeight = 15;
        int fieldWidth = 10;
        int barrierProbability = 15;
        ArrayList<ArrayList<Integer>> myField = fieldInit(fieldHeight, fieldWidth, barrierProbability);
        printField(myField);
        propagation(myField);
    }
}
