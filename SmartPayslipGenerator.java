package com.mycompany.mavenproject1;
import java.util.Scanner;

public class SmartPayslipGenerator {
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
        String name = getString(sc, "Employee Name: ");
        String empId = getString(sc, "Employee ID: ");
        String dept = getString(sc, "Department: ");
        double basicSalary = getDouble(sc, "Basic Monthly Salary: ");
        String start = getString(sc, "Start Date (YYYY-MM-DD): ");
        String end = getString(sc, "End Date (YYYY-MM-DD): ");
        String payout = getString(sc, "Payout Date (YYYY-MM-DD): ");
        double regHours = getDouble(sc, "Regular Hours: ");
        double otHours = getDouble(sc, "Overtime Hours: ");
        double absentDays = getDouble(sc, "Absent Days: ");
        double rice = getDouble(sc, "Rice Allowance: ");
        double trans = getDouble(sc, "Transportation Allowance: ");
        double comm = getDouble(sc, "Communication Allowance: ");
        double sss = getDouble(sc, "SSS: ");
        double phil = getDouble(sc, "PhilHealth: ");
        double pagibig = getDouble(sc, "PAG-IBIG: ");
        double tax = getDouble(sc, "Tax: ");
        double otherDed = getDouble(sc, "Other Deductions: ");

        double hRate = basicSalary / 160.0;
        double dRate = basicSalary / 20.0;
        double regPay = regHours * hRate;
        double otPay = otHours * (hRate * 1.25);
        double absDed = absentDays * dRate;
        double totAll = rice + trans + comm;
        double gross = regPay + otPay + totAll;
        double totDed = sss + phil + pagibig + tax + otherDed + absDed;
        double net = gross - totDed;

        System.out.println("\n*** Smart Employee Payslip ***");
        System.out.printf("Name: %s\nID: %s\nDept: %s\n", name, empId, dept);
        System.out.printf("Period: %s to %s\nPayout: %s\n", start, end, payout);
        System.out.printf("Regular Pay: %.2f\nOvertime Pay: %.2f\nAllowances: %.2f\nGross Pay: %.2f\n", regPay, otPay, totAll, gross);
        System.out.printf("Rice: %.2f\nTrans: %.2f\nComm: %.2f\n", rice, trans, comm);
        System.out.printf("SSS: %.2f\nPhilHealth: %.2f\nPAG-IBIG: %.2f\nTax: %.2f\nAbsence: %.2f\nOther: %.2f\nTotal Deductions: %.2f\nNet Pay: %.2f\n", sss, phil, pagibig, tax, absDed, otherDed, totDed, net);
        sc.close();
    }
}
