import java.util.ArrayDeque;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    static boolean isNumber(String item) {
        // char[] array = item.toCharArray();
        // for (char c : array) {
        //     if (!Character.isDigit(c)) {
        //         return false;
        //     }
        // }
        // return true;
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
        System.out.println(expInfix);
        ArrayList<String> expPostfix = new ArrayList<>();
        Deque<String> operations = new ArrayDeque<>();
        // System.out.println("operations.size = " + operations.size());

        // System.out.println(operatorsMap.keySet());
        // System.out.println(operatorsMap.values());
        // ArrayList<String> functionsList = new ArrayList<>(Arrays.asList("Sin, Cos,
        // Tg, Ctg".split(", ")));
        String tmp = "";
        String inSymbol = "";
        for (int i = 0; i < expInfix.length(); i++) {
            inSymbol = Character.toString(expInfix.charAt(i));
            // System.out.println("i: " + i);
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
                // System.out.println("operations.size = " + operations.size());
                // System.out.println(operations);
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
            // System.out.println("i: " + i + " expPostfix: " + expPostfix);
        }
        while (operations.size() != 0) {
            expPostfix.add(operations.pollLast());
        }
        // System.out.println(operations);
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
                double a = operands.pollLast();
                switch (item) {
                    case "+":
                        result = a + operands.pollLast();
                        operands.addLast(result);
                        break;
                    case "-":
                        result = -a + operands.pollLast();
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

    public static void main(String[] args) {
        // String exp = "(2^3 * (10 / (5 - 3)))^(Sin(Pi))";
        String exp = "2 + (3 - 4)";
        System.out.println(exp);
        Map<String, Integer> operatorsMap = operators();
        ArrayList<String> expPostFix = new ArrayList<>(infixToPostfix(exp, operatorsMap));
        printArrayList(expPostFix);
        System.out.println("Result = " + calculation(expPostFix, operatorsMap));
        // System.out.println();
        // ArrayList<String> operationsList = new ArrayList<>(Arrays.asList("Sin, Cos,
        // Tan, Cotan, ^, *, /, +, -".split(", ")));
        // System.out.println(operationsList);
    }
}
