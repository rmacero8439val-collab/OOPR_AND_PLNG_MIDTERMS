package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab10 {
    static Scanner input = new Scanner(System.in);
    static final int PRODUCTS = 3;
    static final int DAYS = 7;

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
        String[] names = {"Product 1", "Product 2", "Product 3"};
        int[] openingStock = {10, 15, 20};
        int[] stock = {10, 15, 20};
        int[] reorder = {5, 5, 5};
        int[] price = {100, 50, 25};

        int[][] received = new int[PRODUCTS][DAYS];
        int[][] demand = new int[PRODUCTS][DAYS];
        int[][] sold = new int[PRODUCTS][DAYS];
        int[][] closing = new int[PRODUCTS][DAYS];
        int[][] lost = new int[PRODUCTS][DAYS];
        int[] totalSold = new int[PRODUCTS];
        int[] totalLost = new int[PRODUCTS];
        int[] totalRevenue = new int[PRODUCTS];
        int[] dailyRevenue = new int[DAYS];

        System.out.println("WEEKLY INVENTORY AND LOST SALES");
        for (int day = 0; day < DAYS; day++) {
            System.out.println("\n--- DAY " + (day + 1) + " ---");
            for (int product = 0; product < PRODUCTS; product++) {
                received[product][day] = readInt("Delivery for " + names[product] + " (0-1000): ", 0, 1000);
                demand[product][day] = readInt("Customer demand for " + names[product] + " (0-1000): ", 0, 1000);

                int available = stock[product] + received[product][day];
                sold[product][day] = Math.min(demand[product][day], available);
                lost[product][day] = demand[product][day] - sold[product][day];
                closing[product][day] = available - sold[product][day];
                stock[product] = closing[product][day];

                totalSold[product] += sold[product][day];
                totalLost[product] += lost[product][day];
                int revenue = sold[product][day] * price[product];
                totalRevenue[product] += revenue;
                dailyRevenue[day] += revenue;
            }
        }

        System.out.println("\nPRODUCT SUMMARY");
        for (int product = 0; product < PRODUCTS; product++) {
            System.out.println(names[product] + ":");
            System.out.println("  Units sold: " + totalSold[product]);
            System.out.println("  Lost units: " + totalLost[product]);
            System.out.println("  Revenue: PHP " + totalRevenue[product]);
            System.out.println("  Final stock: " + stock[product]);
            if (stock[product] <= reorder[product])
                System.out.println("  LOW-STOCK ALERT (reorder level: " + reorder[product] + ")");
            else
                System.out.println("  Stock level is sufficient.");
        }

        System.out.println("\n===== DAILY REVENUE =====");
        int highestDailyRevenue = dailyRevenue[0];
        for (int day = 0; day < DAYS; day++) {
            System.out.println("Day " + (day + 1) + ": PHP " + dailyRevenue[day]);
            if (dailyRevenue[day] > highestDailyRevenue) highestDailyRevenue = dailyRevenue[day];
        }
        System.out.println("Highest daily revenue: PHP " + highestDailyRevenue);
        System.out.print("Day(s) tied for highest revenue: ");
        for (int day = 0; day < DAYS; day++)
            if (dailyRevenue[day] == highestDailyRevenue) System.out.print("Day " + (day + 1) + " ");
        System.out.println();

        System.out.println("\nINVENTORY BALANCE VERIFICATION");
        for (int product = 0; product < PRODUCTS; product++) {
            int totalReceived = 0;
            int totalSoldForProduct = 0;
            for (int day = 0; day < DAYS; day++) {
                totalReceived += received[product][day];
                totalSoldForProduct += sold[product][day];
            }
            int calculatedClosing = openingStock[product] + totalReceived - totalSoldForProduct;
            System.out.println(names[product] + ": opening stock + deliveries - sales = "
                    + calculatedClosing + "; recorded closing = " + stock[product]
                    + " -> " + (calculatedClosing == stock[product] ? "VERIFIED" : "ERROR"));
        }

    }
}

