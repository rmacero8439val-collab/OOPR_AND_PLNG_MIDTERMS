package com.mycompany.mavenproject1;
import java.util.Scanner;

public class RestaurantOrderingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Type (Dine-In/Takeout): "); String type = sc.nextLine();

        String[] items = new String[3];
        int[] qtns = new int[3];
        double[] prices = new double[3];
        double subtotal = 0;

        for (int i = 0; i < 3; i++) {
            System.out.print("Item " + (i + 1) + ": "); items[i] = sc.nextLine();
            System.out.print("Qty: "); qtns[i] = sc.nextInt();
            System.out.print("Price: "); prices[i] = sc.nextDouble(); sc.nextLine();
            subtotal += qtns[i] * prices[i];
        }

        System.out.print("Service Charge: "); double serv = sc.nextDouble();
        System.out.print("Packaging Fee: "); double pack = sc.nextDouble();
        System.out.print("Discount (%): "); double disc = sc.nextDouble();
        System.out.print("Tendered: "); double tendered = sc.nextDouble();

        double extra = type.equalsIgnoreCase("Dine-In") ? serv : pack;
        double preDisc = subtotal + extra;
        double discAmt = preDisc * (disc / 100);
        double due = preDisc - discAmt;

        System.out.println("\n******** RESTAURANT RECEIPT ********");
        System.out.printf("Name: %s\nType: %s\n", name, type);
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s x%d - %.2f\n", items[i], qtns[i], qtns[i] * prices[i]);
        }
        System.out.printf("Subtotal: %.2f\nFee: %.2f\nTotal Before Discount: %.2f\n", subtotal, extra, preDisc);
        System.out.printf("Discount Amount: %.2f\nDue: %.2f\nTendered: %.2f\nChange: %.2f\n", discAmt, due, tendered, tendered - due);
        sc.close();
    }
}
