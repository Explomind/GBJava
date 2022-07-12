import java.util.ArrayDeque;
import java.util.Deque;

// Написать программу, определяющую правильность расстановки скобок в выражении.
// Пример 1: a+(d*3) - истина
// Пример 2: [a+(1*3) - ложь
// Пример 3: [6+(3*3)] - истина
// Пример 4: {a}[+]{(d*3)} - истина
// Пример 5: <{a}+{(d*3)}> - истина
// Пример 6: {a+]}{(d*3)} - ложь

public class Sem_4_parantheses {
    static boolean rigthPars(String s){
        String openPars = "([{<";
        String closePars = ")]}>";
        Deque<Character> parantheses = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (openPars.indexOf(s.charAt(i)) != -1) {
                parantheses.addLast(s.charAt(i));
            }
            if (closePars.indexOf(s.charAt(i)) != -1 && closePars.indexOf(s.charAt(i)) != openPars.indexOf(parantheses.pollLast())) {
                return false;
            }
        }
        if (parantheses.size() != 0) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String exp = "a+(d*3)";
        System.out.println(exp + " : " + rigthPars(exp));
        exp = "[a+(1*3)";
        System.out.println(exp + " : " + rigthPars(exp));
        exp = "[6+(3*3)]";
        System.out.println(exp + " : " + rigthPars(exp));
        exp = "{a}[+]{(d*3)}";
        System.out.println(exp + " : " + rigthPars(exp));
        exp = "<{a}+{(d*3)}>";
        System.out.println(exp + " : " + rigthPars(exp));
        exp = "{a+]}{(d*3)}";
        System.out.println(exp + " : " + rigthPars(exp));
    }
}
