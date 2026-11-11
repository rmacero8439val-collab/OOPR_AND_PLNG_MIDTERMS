package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab02 {
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
        String[] names = {"Notebook", "Pen", "Folder", "USB"};
        int[] stock = new int[4];
        int[] reorder = {5, 10, 5, 2};
        int[] target = {20, 30, 20, 10};

        int totalStock = 0;
        int totalSuggestedOrder = 0;

        System.out.println("STORE STOCK REORDER REPORT");
        for (int i = 0; i < names.length; i++) {
            stock[i] = readInt("Enter stock quantity for " + names[i] + " (0-10000): ", 0, 10000);
            totalStock += stock[i];
        }

        System.out.println("\nProduct\t\tStock\tReorder Level\tTarget\tSuggested Order\tStatus");
        for (int i = 0; i < names.length; i++) {
            int suggestedOrder = 0;
            String status = "Sufficient";
            if (stock[i] <= reorder[i]) {
                suggestedOrder = target[i] - stock[i];
                totalSuggestedOrder += suggestedOrder;
                status = "REORDER";
            }
            System.out.printf("%-12s\t%d\t%d\t\t%d\t%d\t\t%s%n",
                    names[i], stock[i], reorder[i], target[i], suggestedOrder, status);
        }

        System.out.println("\nTotal units in stock: " + totalStock);
        System.out.println("Total suggested order: " + totalSuggestedOrder);
    }
}

