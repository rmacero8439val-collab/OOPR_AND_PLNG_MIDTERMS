package com.mycompany.mavenproject1;
import java.util.Scanner;

public class FlowerReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Contact: "); String contact = sc.nextLine();
        System.out.print("Flower Type: "); String flower = sc.nextLine();
        System.out.print("Arrangement: "); String arr = sc.nextLine();
        System.out.print("Price: "); double price = sc.nextDouble();
        System.out.print("Qty: "); int qty = sc.nextInt(); sc.nextLine();
        System.out.print("Res Date: "); String resDate = sc.nextLine();
        System.out.print("Delivery Date: "); String delDate = sc.nextLine();
        System.out.print("Delivery Fee: "); double delFee = sc.nextDouble();
        System.out.print("Customization Fee: "); double custFee = sc.nextDouble();
        System.out.print("Other: "); double other = sc.nextDouble();
        System.out.print("Discount (%): "); double disc = sc.nextDouble();
        System.out.print("Down Payment: "); double down = sc.nextDouble();

        double cost = price * qty;
        double sub = cost + delFee + custFee + other;
        double discAmt = sub * (disc / 100);
        double bill = sub - discAmt;

        System.out.println("\n******** FLOWER RESERVATION RECEIPT ********");
        System.out.printf("Name: %s\nID: %s\nContact: %s\n", name, id, contact);
        System.out.printf("Flower: %s\nArrangement: %s\nQty: %d\nCost: %.2f\n", flower, arr, qty, cost);
        System.out.printf("Dates: %s to %s\nSubtotal: %.2f\n", resDate, delDate, sub);
        System.out.printf("Discount Amount: %.2f\nTotal Bill: %.2f\nDown Payment: %.2f\nBalance: %.2f\n", discAmt, bill, down, bill - down);
        sc.close();
    }
}
