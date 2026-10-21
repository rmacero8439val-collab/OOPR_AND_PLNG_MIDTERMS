package com.mycompany.mavenproject1;
import java.util.Scanner;

public class ReceiptSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Customer ID: ");
        String id = sc.nextLine();

        String[] items = new String[3];
        int[] qtns = new int[3];
        double[] prices = new double[3];
        double subtotal = 0;

        for (int i = 0; i < 3; i++) {
            System.out.print("Item " + (i + 1) + " Name: ");
            items[i] = sc.nextLine();
            System.out.print("Quantity: ");
            qtns[i] = sc.nextInt();
            System.out.print("Price: ");
            prices[i] = sc.nextDouble();
            sc.nextLine();
            subtotal += qtns[i] * prices[i];
        }

        System.out.print("Discount (%): ");
        double discPercent = sc.nextDouble();
        System.out.print("Amount Tendered: ");
        double tendered = sc.nextDouble();

        double discAmt = subtotal * (discPercent / 100);
        double due = subtotal - discAmt;
        double change = tendered - due;

        System.out.println("\n******** CUSTOMER RECEIPT ********");
        System.out.printf("Name: %s\nID: %s\n", name, id);
        System.out.printf("%-15s %-5s %-10s %-10s\n", "Item", "Qty", "Price", "Total");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-15s %-5d %-10.2f %-10.2f\n", items[i], qtns[i], prices[i], qtns[i] * prices[i]);
        }
        System.out.printf("Subtotal: %.2f\nDiscount Amount: %.2f\nTotal Due: %.2f\nTendered: %.2f\nChange: %.2f\n", subtotal, discAmt, due, tendered, change);
        sc.close();
    }
}
