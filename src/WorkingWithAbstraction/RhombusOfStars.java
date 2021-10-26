package WorkingWithAbstraction;

import java.util.Scanner;

public class RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i < n; i++) {
            printLineSpace(n - i);
            printLineStars(i);
            System.out.println();
        }
        printLineStars(n);
        System.out.println();
        for (int r = 1; r < n; r++) {
            printLineSpace(r);
            printLineStars(n - r);
            System.out.println();
        }
    }

    private static void printLineSpace(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    public static void printLineStars(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("* ");
        }
    }
}
