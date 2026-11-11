package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab07 {
    static Scanner input = new Scanner(System.in);
    static final int WAREHOUSES = 3;
    static final int PRODUCTS = 4;
    static final int HISTORY_LIMIT = 50;

    static int[][] stock = {
        {20, 30, 40, 50},
        {15, 25, 35, 45},
        {10, 20, 30, 40}
    };
    static int[][] history = new int[HISTORY_LIMIT][4];
    static int historyCount = 0;

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

    static int[] productTotals() {
        int[] totals = new int[PRODUCTS];
        for (int warehouse = 0; warehouse < WAREHOUSES; warehouse++)
            for (int product = 0; product < PRODUCTS; product++)
                totals[product] += stock[warehouse][product];
        return totals;
    }

    static void displayStock() {
        System.out.println("\nWAREHOUSE STOCK");
        for (int warehouse = 0; warehouse < WAREHOUSES; warehouse++) {
            System.out.print("Warehouse " + (warehouse + 1) + ": ");
            for (int product = 0; product < PRODUCTS; product++)
                System.out.print("P" + (product + 1) + "=" + stock[warehouse][product] + " ");
            System.out.println();
        }
        int[] totals = productTotals();
        System.out.print("Product totals: ");
        for (int product = 0; product < PRODUCTS; product++)
            System.out.print("P" + (product + 1) + "=" + totals[product] + " ");
        System.out.println();
    }

    static void transfer() {
        if (historyCount >= HISTORY_LIMIT) {
            System.out.println("Transfer rejected: audit history is full.");
            return;
        }

        int source = readInt("Source warehouse (1-3): ", 1, 3) - 1;
        int destination = readInt("Destination warehouse (1-3): ", 1, 3) - 1;
        int product = readInt("Product number (1-4): ", 1, 4) - 1;
        int quantity = readInt("Quantity to transfer (1-1000): ", 1, 1000);

        if (source == destination) {
            System.out.println("Transfer rejected: source and destination must be different.");
            return;
        }
        if (quantity > stock[source][product]) {
            System.out.println("Transfer rejected: insufficient source stock.");
            return;
        }
        if (stock[destination][product] + quantity > 1000) {
            System.out.println("Transfer rejected: destination capacity would exceed 1000.");
            return;
        }
        if (historyCount >= HISTORY_LIMIT) {
            System.out.println("Transfer rejected: audit history is full.");
            return;
        }

        stock[source][product] -= quantity;
        stock[destination][product] += quantity;
        history[historyCount][0] = source + 1;
        history[historyCount][1] = destination + 1;
        history[historyCount][2] = product + 1;
        history[historyCount][3] = quantity;
        historyCount++;

        System.out.println("Transfer completed. Total stock remains unchanged.");
    }

    static void showHistory() {
        System.out.println("\nTRANSFER HISTORY");
        if (historyCount == 0) {
            System.out.println("No successful transfers recorded.");
            return;
        }
        for (int i = 0; i < historyCount; i++) {
            System.out.println((i + 1) + ". From warehouse " + history[i][0]
                    + " to warehouse " + history[i][1] + ", product " + history[i][2]
                    + ", quantity " + history[i][3]);
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        displayStock();
        while (running) {
            System.out.println("\n1. Transfer stock");
            System.out.println("2. Display stock");
            System.out.println("3. Show history");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option (1-4): ", 1, 4);
            switch (choice) {
                case 1: transfer(); break;
                case 2: displayStock(); break;
                case 3: showHistory(); break;
                case 4: running = false; break;
            }
        }
        System.out.println("Program ended.");
    }
}
