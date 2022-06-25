/**
 * program
 */
import java.util.Scanner;
public class program {
    public static void main(String[] args) {
        // String s = "Hello, underworld!";
        // System.out.println(s);
        // float f = 123.45f;
        // System.out.println(f);
        // double d = 3.1415;
        // System.out.println(d);
        // double d2 = 3.25D;
        // System.out.println(d2);
        // char ch = '{';
        // System.out.println(ch);
        // char ch2 = 122;
        // System.out.println(ch2);
        // Scanner iScanner = new Scanner(System.in);
        // // System.out.printf("name: ");
        // // String name = iScanner.nextLine();
        // // System.out.printf("Howdy, %s!\n", name);
        // // System.out.printf("int x: ");
        // // int x = iScanner.nextInt();
        // // System.out.printf("double y: ");
        // // double y = iScanner.nextDouble();
        // // System.out.printf("%d + %f = %f\n", x, y, x + y);
        // System.out.printf("int a: ");
        // boolean flag = iScanner.hasNextInt();
        // System.out.println(flag);
        // int i = iScanner.nextInt();
        // System.out.println(i);
        // iScanner.close();
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < 1_000_000; i++) {
        //     sb.append("+");
        // }
        int[] arrayToSort = new int[]{6, 1, 5, 2};
        int[] leftArray = new int[arrayToSort.length / 2];
        System.arraycopy(arrayToSort, 0, leftArray, 0, leftArray.length);
        for (int i = 0; i < leftArray.length; i++) {
            System.out.print(leftArray[i]);
        }
        System.out.println();
        int[] rightArray = new int[arrayToSort.length - arrayToSort.length / 2];
        System.arraycopy(arrayToSort, arrayToSort.length / 2, rightArray, 0, rightArray.length);
        for (int i = 0; i < rightArray.length; i++) {
            System.out.print(rightArray[i]);
        }
        System.out.println();
    }
    
}