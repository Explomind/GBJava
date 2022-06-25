package Seminars;

import java.util.Scanner;
public class TriangleNumber {
    public static void main(String[] args) {
        System.out.printf("Input integer n: ");
        Scanner iScanner = new Scanner(System.in);
        boolean flag = iScanner.hasNextInt();
        if (flag) {
            int n = iScanner.nextInt();
            System.out.printf("\nTriangle number = %d\n", n*(n+1)/2);    
        } else {
            System.out.println("Incorrect input!");
        }
        
    }
}
