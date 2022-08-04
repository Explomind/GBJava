// Задано уравнение вида q + w = e, q, w, e >= 0. 
// Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69. 
// Требуется восстановить выражение до верного равенства. 
// Предложить хотя бы одно решение или сообщить, что его нет.

package Seminars;

import java.util.ArrayList;
import java.util.Scanner;

public class Equation {
    static class Operand {
        String valueStr;
        int valueInt;
        boolean mutable;

        @Override
        public String toString() {
            return String.format("valueStr: %s valueInt: %d mutable: %b", valueStr, valueInt, mutable);
        }

        public Operand(String valueString, int id) {
            this.valueStr = valueString;
            if (isNumber(valueString)) {
                this.valueInt = Integer.parseInt(valueString);
                this.mutable = false;
            } else {
                this.valueInt = -id;
                this.mutable = true;
            }

        }

        public void setValueInt(int newValue) {
            valueInt = newValue;
        }

        static boolean isNumber(String item) {
            char[] array = item.toCharArray();
            for (char c : array) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    static String inputEquation() {
        Scanner readConsole = new Scanner(System.in);
        System.out.println("Введите уравнение вида q + w = e, q, w, e >= 0.");
        System.out.println("(Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69):");
        String result = readConsole.nextLine();
        readConsole.close();
        return result;
    }

    static ArrayList<Operand> getOperands(String expString) {
        ArrayList<Operand> operands = new ArrayList<>();
        String validChars = "0123456789?";
        String[] exp = expString.split(" ");
        boolean valid = true;
        for (int i = 0; i < exp.length; i++) {
            char[] expChars = exp[i].toCharArray();
            valid = true;
            for (char c : expChars) {
                if (!validChars.contains(Character.toString(c))) {
                    valid = false;
                }
            }
            if (valid) {
                operands.add(new Operand(exp[i], i));
            }
        }
        return operands;
    }

    static boolean seekSolvery(ArrayList<Operand> operands, int start, boolean solved) {
        for (int i = start; i < operands.size(); i++) {
            if (solved) {
                return true;
            }
            if (operands.get(i).mutable) {
                String tmpString = "";
                for (int j = 0; j < 10; j++) {
                    tmpString = operands.get(i).valueStr.replace('?', Character.forDigit(j, 10));
                    operands.get(i).setValueInt(Integer.parseInt(tmpString));
                    solved = seekSolvery(operands, start + 1, solved);
                    if (operands.get(0).valueInt + operands.get(1).valueInt == operands.get(2).valueInt) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String myEquation = inputEquation();
        // String myEquation = "2? + 4? = 69";
        ArrayList<Operand> myList = getOperands(myEquation);
        if (seekSolvery(myList, 0, false)) {
            System.out.printf("Решение найдено:\n%d + %d = %d\n", myList.get(0).valueInt, myList.get(1).valueInt,
                    myList.get(2).valueInt);
        } else {
            System.out.println("Решение не найдено. Проверьте правильность ввода.");
        }
    }
}
