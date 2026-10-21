package com.mycompany.mavenproject1;
import java.util.Scanner;

public class TuitionBillingSystem {
    private static String getString(Scanner sc, String p) {
        while (true) {
            System.out.print(p);
            String in = sc.nextLine().trim();
            if (in.matches("^[a-zA-Z0-9\\s-]+$")) return in;
            System.out.println("Invalid input!");
        }
    }
    private static double getDouble(Scanner sc, String p) {
        while (true) {
            System.out.print(p);
            if (sc.hasNextDouble()) {
                double val = sc.nextDouble();
                if (val >= 0) { sc.nextLine(); return val; }
                System.out.println("No negative values!");
            } else {
                System.out.println("Must be a number!");
                sc.next();
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = getString(sc, "Student Name: ");
        String id = getString(sc, "Student ID: ");
        String course = getString(sc, "Course: ");
        String year = getString(sc, "Year Level: ");
        double units = getDouble(sc, "Units Enrolled: ");
        double feePerUnit = getDouble(sc, "Fee per Unit: ");
        double misc = getDouble(sc, "Misc Fee: ");
        double lab = getDouble(sc, "Lab Fee: ");
        double lib = getDouble(sc, "Library Fee: ");
        double other = getDouble(sc, "Other Fees: ");
        double downPay = getDouble(sc, "Down Payment: ");
        double discount = getDouble(sc, "Scholarship Discount: ");

        double tuitionTotal = units * feePerUnit;
        double addFees = lab + lib + other;
        double gross = tuitionTotal + misc + addFees;
        double net = gross - discount;
        double balance = net - downPay;

        System.out.println("\n*** STUDENT TUITION BILLING SYSTEM ***");
        System.out.printf("Name: %s\nID: %s\nCourse: %s\nYear: %s\n", name, id, course, year);
        System.out.printf("Units: %.0f\nPer Unit: %.2f\nTuition Total: %.2f\n", units, feePerUnit, tuitionTotal);
        System.out.printf("Misc: %.2f\nLab: %.2f\nLibrary: %.2f\nOther: %.2f\nGross Total: %.2f\n", misc, lab, lib, other, gross);
        System.out.printf("Discount: %.2f\nNet Total: %.2f\nDown Payment: %.2f\nBalance: %.2f\n", discount, net, downPay, balance);
        sc.close();
    }
}
