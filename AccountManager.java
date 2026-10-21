package com.mycompany.mavenproject1;
import java.util.Scanner;

public class AccountManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome");
        double balance = 0;
        
        while (true) {
            System.out.print("Enter your balance: ");
            if (scanner.hasNextDouble()) {
                balance = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input");
                scanner.next(); 
            }
        }

        while (true) {
            System.out.println("Select an option:\n1. Withdraw Amount\n2. Deposit Amount\n3. Exit");
            System.out.print("Enter option: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid option\n");
                scanner.next(); 
                continue;
            }
            
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("How much amount you want to withdraw from your account?");
                System.out.print("Amount: ");
                if (scanner.hasNextDouble()) {
                    balance -= scanner.nextDouble();
                    System.out.println("Your new balance is: " + (int)balance + "\n");
                } else {
                    System.out.println("Invalid input\n");
                    scanner.next();
                }
            } else if (choice == 2) {
                System.out.println("How much amount you want to deposit in your account?");
                System.out.print("Amount: ");
                if (scanner.hasNextDouble()) {
                    balance += scanner.nextDouble();
                    System.out.println("Your new balance is: " + (int)balance + "\n");
                } else {
                    System.out.println("Invalid input\n");
                    scanner.next();
                }
            } else if (choice == 3) {
                System.out.println("Thank you for using the system!");
                break;
            } else {
                System.out.println("Invalid selection\n");
            }
        }
        scanner.close();
    }
}
