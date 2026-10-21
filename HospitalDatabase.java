package com.mycompany.mavenproject1;
import java.util.Scanner;
import java.util.ArrayList;

class Date {
    int year, month, day;
    Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}

class Patient {
    String name, disease;
    Date admission, discharge;

    private String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^[a-zA-Z\\s]+$")) {
                return input;
            }
            System.out.println("Invalid input");
        }
    }

    private int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                if (value >= 0) {
                    return value;
                }
                System.out.println("Invalid input");
            } else {
                System.out.println("Invalid input");
                sc.next();
            }
        }
    }

    void enterInfo(Scanner sc) {
        name = getValidString(sc, "Enter name: ");
        disease = getValidString(sc, "Enter disease: ");
        
        System.out.println("Enter admission date:");
        int am = getValidInt(sc, "  Month (mm): ");
        int ad = getValidInt(sc, "  Day (dd): ");
        int ay = getValidInt(sc, "  Year (yyyy): ");
        admission = new Date(am, ad, ay);
        
        System.out.println("Enter discharge date:");
        int dm = getValidInt(sc, "  Month (mm): ");
        int dd = getValidInt(sc, "  Day (dd): ");
        int dy = getValidInt(sc, "  Year (yyyy): ");
        discharge = new Date(dm, dd, dy);
        sc.nextLine(); 
    }

    void display() {
        System.out.println("Name: " + name + " | Disease: " + disease + 
                           " | Admitted: " + admission + " | Discharged: " + discharge);
    }
}

class PediatricPatient extends Patient {
    int age;

    @Override
    void enterInfo(Scanner sc) {
        super.enterInfo(sc);
        while (true) {
            System.out.print("Enter age: ");
            if (sc.hasNextInt()) {
                int inputAge = sc.nextInt();
                if (inputAge >= 0) {
                    age = inputAge;
                    break;
                }
                System.out.println("Invalid input. Age cannot be negative.");
            } else {
                System.out.println("Invalid input");
                sc.next();
            }
        }
        sc.nextLine(); 
    }

    @Override
    void display() {
        super.display();
        System.out.println("Age: " + age);
    }
}

public class HospitalDatabase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<PediatricPatient> database = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Insert New Patient\n2. Display All Patients\n3. Display pediatric patients\n4. Exit");
            System.out.print("Choice: ");
            
            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice");
                sc.next();
                continue;
            }
            
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                PediatricPatient p = new PediatricPatient();
                p.enterInfo(sc);
                database.add(p);
            } else if (choice == 2) {
                for (PediatricPatient p : database) {
                    p.display();
                }
            } else if (choice == 3) {
                for (PediatricPatient p : database) {
                    if (p.age < 12) {
                        p.display();
                    }
                }
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid selection");
            }
        }
        sc.close();
    }
}
