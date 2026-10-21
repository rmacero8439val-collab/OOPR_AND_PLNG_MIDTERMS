package com.mycompany.mavenproject1;
import java.util.Scanner;

public class TicketingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Event: "); String event = sc.nextLine();
        System.out.print("Type: "); String type = sc.nextLine();
        System.out.print("Price: "); double price = sc.nextDouble();
        System.out.print("Quantity: "); int qty = sc.nextInt();
        System.out.print("Discount (%): "); double disc = sc.nextDouble();
        System.out.print("Tendered: "); double tendered = sc.nextDouble();

        double cost = price * qty;
        double discAmt = cost * (disc / 100);
        double due = cost - discAmt;
        double change = tendered - due;

        System.out.println("\n******** EVENT TICKET ********");
        System.out.printf("Name: %s\nID: %s\nEvent: %s\nType: %s\n", name, id, event, type);
        System.out.printf("Price: %.2f\nQty: %d\nCost: %.2f\n", price, qty, cost);
        System.out.printf("Discount Amount: %.2f\nDue: %.2f\nTendered: %.2f\nChange: %.2f\n", discAmt, due, tendered, change);
        sc.close();
    }
}
