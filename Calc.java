import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calc {
    static boolean isNumber(String item) {
        try {
            Double.parseDouble(item);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static void printArrayList(ArrayList<String> arrayList) {
        for (String string : arrayList) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    static boolean rigthPars(String s) {
        String openPars = "([{<";
        String closePars = ")]}>";
        Deque<Character> parantheses = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (openPars.indexOf(s.charAt(i)) != -1) {
                parantheses.addLast(s.charAt(i));
            }
            if (closePars.indexOf(s.charAt(i)) != -1
                    && closePars.indexOf(s.charAt(i)) != openPars.indexOf(parantheses.pollLast())) {
                return false;
            }
        }
        if (parantheses.size() != 0) {
            return false;
        }
        return true;
    }

    static Map<String, Integer> operators() {
        Map<String, Integer> operatorsMap = new HashMap<>(10, 1.0f);
        operatorsMap.put("(", 5);
        operatorsMap.put("+", 1);
        operatorsMap.put("-", 1);
        operatorsMap.put("*", 2);
        operatorsMap.put("/", 2);
        operatorsMap.put("^", 3);
        operatorsMap.put("Sin", 4);
        operatorsMap.put("Cos", 4);
        operatorsMap.put("Tg", 4);
        operatorsMap.put("Ctg", 4);
        return operatorsMap;
    }

    static ArrayList<String> infixToPostfix(String expInfix, Map<String, Integer> operatorsMap) {
        expInfix = "(" + expInfix + ")";
        ArrayList<String> expPostfix = new ArrayList<>();
        Deque<String> operations = new ArrayDeque<>();
        String tmp = "";
        String inSymbol = "";
        for (int i = 0; i < expInfix.length(); i++) {
            inSymbol = Character.toString(expInfix.charAt(i));
            while (i < expInfix.length() && Character.isDigit(expInfix.charAt(i))) {
                tmp += expInfix.charAt(i);
                i++;
            }
            if (tmp != "") {
                expPostfix.add(tmp);
                tmp = "";
                i--;
            }
            while (i < expInfix.length() && Character.isLetter(expInfix.charAt(i))) {
                tmp += expInfix.charAt(i);
                i++;
            }
            if (operatorsMap.containsKey(tmp)) {
                operations.addLast(tmp);
                tmp = "";
                i--;
            } else if (tmp.equals("Pi")) {
                expPostfix.add(tmp);
                tmp = "";
                i--;
            } else if (tmp != "") {
                System.out.printf("$s - unknown operation", tmp);
                tmp = "";
                i--;
            }
            if (operatorsMap.containsKey(inSymbol)) {
                while (operations.size() != 0 && !operations.peekLast().equals("(")
                        && operatorsMap.get(operations.peekLast()) >= operatorsMap.get(inSymbol)) {
                    expPostfix.add(operations.pollLast());
                }
                operations.addLast(inSymbol);
            }
            if (inSymbol.equals(")")) {
                while (!operations.peekLast().equals("(")) {
                    expPostfix.add(operations.pollLast());
                }
                operations.removeLast();
                if (operations.size() != 0 && operatorsMap.get(operations.peekLast()) == 4) {
                    expPostfix.add(operations.pollLast());
                }
            }
        }
        while (operations.size() != 0) {
            expPostfix.add(operations.pollLast());
        }
        return expPostfix;
    }

    static Double calculation(ArrayList<String> expPostFix, Map<String, Integer> operatorsMap) {
        Double result = 0.0;
        Deque<Double> operands = new ArrayDeque<>();
        for (String item : expPostFix) {
            if (isNumber(item)) {
                operands.addLast(Double.parseDouble(item));
            }
            if (item.equals("Pi")) {
                operands.addLast(Math.PI);
            }
            if (operatorsMap.containsKey(item)) {
                switch (item) {
                    case "+":
                        result = operands.pollLast() + operands.pollLast();
                        operands.addLast(result);
                        break;
                    case "-":
                        result = -operands.pollLast() + operands.pollLast();
                        operands.addLast(result);
                        break;
                    case "*":
                        result = operands.pollLast() * operands.pollLast();
                        operands.addLast(result);
                        break;
                    case "/":
                        result = 1 / operands.pollLast() * operands.pollLast();
                        operands.addLast(result);
                        break;
                    case "^":
                        result = operands.pollLast();
                        result = Math.pow(operands.pollLast(), result);
                        operands.addLast(result);
                        break;
                    case "Sin":
                        result = Math.sin(operands.pollLast());
                        operands.addLast(result);
                        break;
                    case "Cos":
                        result = Math.cos(operands.pollLast());
                        operands.addLast(result);
                        break;
                    case "Tg":
                        result = Math.tan(operands.pollLast());
                        operands.addLast(result);
                        break;
                    case "Ctg":
                        result = 1 / Math.tan(operands.pollLast());
                        operands.addLast(result);
                        break;
                    default:
                        break;
                }
            }
        }
        result = operands.pollLast();
        return result;
    }

    static String inputExp() {
        System.out.println("Инженерный калькулятор");
        System.out.println("Операции: +, -, *, /, ^, (, )");
        System.out.println("Функции: Sin(), Cos(), Tg(), Ctg()");
        System.out.println("Число Pi: Pi");
        Scanner readConsole = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        String result = readConsole.nextLine();
        readConsole.close();
        return result;
    }

    public static void main(String[] args) {
        // String exp = "(2^3 * (10 / (5 - 3)))^(Sin(Pi))";
        // String exp = "Cos(Pi)";
        String exp = inputExp();
        if (rigthPars(exp)) {
            Map<String, Integer> operatorsMap = operators();
            ArrayList<String> expPostFix = new ArrayList<>(infixToPostfix(exp, operatorsMap));
            System.out.println("Выражение в постфиксной нотации:");
            printArrayList(expPostFix);
            System.out.printf("Результат: %.3f\n", calculation(expPostFix, operatorsMap));
        } else {
            System.out.println("Выражение введено неверно. Пропущена скобка.");
        }
    }
}
