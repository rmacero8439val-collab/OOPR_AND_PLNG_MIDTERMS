package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab03 {
    static Scanner input = new Scanner(System.in);

    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= min && value <= max) return value;
                System.out.println("Invalid input. Enter a value from " + min + " to " + max + ".");
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
                input.next();
            }
        }
    }

    public static void main(String[] args) {
        int[][] sales = new int[3][5];
        int[] branchTotals = new int[3];
        int[] dayTotals = new int[5];
        int grandTotal = 0;

        System.out.println("BRANCH SALES MATRIX");
        for (int branch = 0; branch < 3; branch++) {
            System.out.println("\nBranch " + (branch + 1));
            for (int day = 0; day < 5; day++) {
                sales[branch][day] = readInt("Enter sales for day " + (day + 1) + " (PHP 0-1000000): ",
                        0, 1000000);
                branchTotals[branch] += sales[branch][day];
                dayTotals[day] += sales[branch][day];
                grandTotal += sales[branch][day];
            }
        }

        int highestBranchTotal = branchTotals[0];
        for (int branch = 1; branch < 3; branch++) {
            if (branchTotals[branch] > highestBranchTotal) highestBranchTotal = branchTotals[branch];
        }

        System.out.println("\nBranch totals:");
        for (int branch = 0; branch < 3; branch++) {
            System.out.println("Branch " + (branch + 1) + ": PHP " + branchTotals[branch]);
        }

        System.out.println("\nDay totals:");
        for (int day = 0; day < 5; day++) {
            System.out.println("Day " + (day + 1) + ": PHP " + dayTotals[day]);
        }

        System.out.println("\nHighest branch total: PHP " + highestBranchTotal);
        System.out.print("Branch(es) tied for highest: ");
        for (int branch = 0; branch < 3; branch++) {
            if (branchTotals[branch] == highestBranchTotal) System.out.print("Branch " + (branch + 1) + " ");
        }
        System.out.println();
        System.out.println("Grand total sales: PHP " + grandTotal);
    }
}

