package com.mycompany.mavenproject1;
import java.util.Scanner;

class Professor {
    String name;
    double salary;

    Professor(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

class Dean extends Professor {
    String department;

    Dean(String name, String department, double salary) {
        super(name, salary);
        this.department = department;
    }

    public String toString() {
        return "\nStatus:\nRank: Dean\nName: " + name + "\nDepartment: " + department + "\nSalary: " + (int)salary;
    }
}

class Executive extends Dean {
    Executive(String name, String department, double salary) {
        super(name, department, salary);
    }

    public String toString() {
        return "\nStatus:\nRank: Executive\nName: " + name + "\nDepartment: " + department + "\nSalary: " + (int)salary;
    }
}

public class ProfSalary {
    private static String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^[a-zA-Z\\s]+$")) {
                return input;
            }
            System.out.println("Invalid input");
        }
    }

    private static double getValidSalary(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                double value = sc.nextDouble();
                if (value >= 0) {
                    sc.nextLine(); 
                    return value;
                }
                System.out.println("Invalid input");
            } else {
                System.out.println("Invalid input");
                sc.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Enter Dean Details ---");
        String dName = getValidString(sc, "Name: ");
        String dDept = getValidString(sc, "Department: ");
        double dSalary = getValidSalary(sc, "Salary: ");

        System.out.println("\n--- Enter Executive Details ---");
        String eName = getValidString(sc, "Name: ");
        String eDept = getValidString(sc, "Department: ");
        double eSalary = getValidSalary(sc, "Salary: ");

        Dean d = new Dean(dName, dDept, dSalary);
        Executive e = new Executive(eName, eDept, eSalary);

        System.out.println(d);
        System.out.println(e);

        sc.close();
    }
}
