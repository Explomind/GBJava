/**
 * Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых действий к минимуму.
Пример 1: а = 3, b = 2, ответ: 9
Пример 2: а = 2, b = -2, ответ: 0.25
Пример 3: а = 3, b = 0, ответ: 1
Пример 4: а = 0, b = 0, ответ: не определено
Пример 5
входные данные находятся в файле input.txt в виде
b 3
a 10
Результат нужно сохранить в файле output.txt
1000
 */

package Seminars;

import java.io.*;

public class seminar1 {
    static double power(int base, int pow) {
        if (base == 0 && pow == 0) {
            System.out.printf("%d ^ %d\tUndefined\n", base, pow);
            return 0;
        }
        if (base == 0) {
            System.out.printf("%d ^ %d = 0\n", base, pow);
            return 0;
        }
        double result = 1;
        if (pow > 0) {
            for (int i = 0; i < pow; i++) {
                result *= base;
            }
        }
        if (pow < 0) {
            for (int i = 0; i < -pow; i++) {
                result /= base;
            }
        }
        System.out.printf("%d ^ %d = %.2f\n", base, pow, result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        Integer a = 3;
        Integer b = 2;
        power(a, b);
        a = 2;
        b = -2;
        power(a, b);
        a = 3;
        b = 0;
        power(a, b);
        a = 0;
        b = 0;
        power(a, b);
        BufferedReader br = new BufferedReader(
                new FileReader("/home/egor/Документы/Geekbrains/java_projects/Seminars/input.txt"));
        String[] strArr = new String[3];
        int i = 0;
        String[] abStr = { "", "" };
        while ((strArr[i] = br.readLine()) != null) {
            for (int j = 0; j < strArr[i].length(); j++) {
                if (Character.isDigit(strArr[i].charAt(j))) {
                    abStr[i] += strArr[i].charAt(j);
                }
            }
            i++;
        }
        br.close();
        b = Integer.parseInt(abStr[0]);
        a = Integer.parseInt(abStr[1]);
        String resultStr = Double.toString(power(a, b));
        try (FileWriter fw = new FileWriter("Seminars/output.txt", false)){
            fw.write(resultStr);
            fw.append('\n');
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
