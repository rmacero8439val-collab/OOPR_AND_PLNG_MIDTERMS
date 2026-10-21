package com.mycompany.mavenproject1;
import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Type: "); String type = sc.nextLine();
        System.out.print("Duration: "); double dur = sc.nextDouble();
        System.out.print("Rate: "); double rate = sc.nextDouble();
        System.out.print("Food: "); double food = sc.nextDouble();
        System.out.print("Service: "); double serv = sc.nextDouble();
        System.out.print("Other: "); double other = sc.nextDouble();
        System.out.print("Discount (%): "); double disc = sc.nextDouble();
        System.out.print("Down Payment: "); double down = sc.nextDouble();

        double cost = dur * rate;
        double add = food + serv + other;
        double sub = cost + add;
        double discAmt = sub * (disc / 100);
        double bill = sub - discAmt;
        double bal = bill - down;

        System.out.println("\n******** RESERVATION BILLING ********");
        System.out.printf("Name: %s\nID: %s\nType: %s\nDuration: %.0f\nCost: %.2f\n", name, id, type, dur, cost);
        System.out.printf("Food: %.2f\nService: %.2f\nOther: %.2f\nSubtotal: %.2f\n", food, serv, other, sub);
        System.out.printf("Discount Amount: %.2f\nTotal Bill: %.2f\nDown Payment: %.2f\nBalance: %.2f\n", discAmt, bill, down, bal);
        sc.close();
    }
}
