package com.mycompany.mavenproject1;
import java.util.Scanner;

public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("License: "); String lic = sc.nextLine();
        System.out.print("Type: "); String type = sc.nextLine();
        System.out.print("Model: "); String model = sc.nextLine();
        System.out.print("Rate: "); double rate = sc.nextDouble();
        System.out.print("Days: "); int days = sc.nextInt(); sc.nextLine();
        System.out.print("Pickup: "); String pick = sc.nextLine();
        System.out.print("Return: "); String ret = sc.nextLine();
        System.out.print("Insurance: "); double ins = sc.nextDouble();
        System.out.print("Fuel: "); double fuel = sc.nextDouble();
        System.out.print("Other: "); double other = sc.nextDouble();
        System.out.print("Discount (%): "); double disc = sc.nextDouble();
        System.out.print("Paid: "); double paid = sc.nextDouble();

        double cost = rate * days;
        double add = ins + fuel + other;
        double sub = cost + add;
        double discAmt = sub * (disc / 100);
        double due = sub - discAmt;

        System.out.println("\n******** CAR RENTAL RECEIPT ********");
        System.out.printf("Name: %s\nID: %s\nLicense: %s\n", name, id, lic);
        System.out.printf("Type: %s\nModel: %s\nRate: %.2f\n", type, model, rate);
        System.out.printf("Pickup: %s\nReturn: %s\nDays: %d\nCost: %.2f\n", pick, ret, days, cost);
        System.out.printf("Insurance: %.2f\nFuel: %.2f\nOther: %.2f\nSubtotal: %.2f\n", ins, fuel, other, sub);
        System.out.printf("Discount Amount: %.2f\nDue: %.2f\nPaid: %.2f\n", discAmt, due, paid);
        if (paid >= due) System.out.printf("Change: %.2f\n", paid - due);
        else System.out.printf("Remaining Balance: %.2f\n", due - paid);
        sc.close();
    }
}
