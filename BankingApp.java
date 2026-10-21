package com.mycompany.mavenproject1;
import java.util.Scanner;

abstract class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be positive.");
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
            return false;
        }
        double fee = calculateWithdrawalFee();
        if (balance < (amount + fee)) {
            System.out.println("Error: Insufficient funds! Amount + Fee exceeds balance.");
            return false;
        }
        balance -= (amount + fee);
        System.out.printf("Withdrawal fee: P%.2f | ", fee);
        return true;
    }

    public abstract double calculateWithdrawalFee();
    public abstract String getAccountType();
}

class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateWithdrawalFee() {
        return 0.0;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}

class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateWithdrawalFee() {
        return 10.0;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}

public class BankingApp {
    private static double readNonNegativeBalance(Scanner scanner) {
        while (true) {
            System.out.print("Opening balance: ");
            if (scanner.hasNextDouble()) {
                double val = scanner.nextDouble();
                if (val >= 0) {
                    scanner.nextLine();
                    return val;
                }
                System.out.println("Invalid input. Balance cannot be negative.");
            } else {
                System.out.println("Invalid input. Please enter a numerical value.");
                scanner.next();
            }
        }
    }

    private static double readPositiveAmount(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double val = scanner.nextDouble();
                if (val > 0) {
                    scanner.nextLine();
                    return val;
                }
                System.out.println("Invalid input. Amount must be greater than zero.");
            } else {
                System.out.println("Invalid input. Please enter a numerical amount.");
                scanner.next();
            }
        }
    }

    private static void printAccountSummary(Account account) {
        System.out.printf("Current account: %s | Balance: P%.2f\n", account.getAccountType(), account.getBalance());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the OOP Banking System");

        double initialBalance = readNonNegativeBalance(scanner);

        SavingsAccount savingsAccount = new SavingsAccount("SAV-001", initialBalance);
        CheckingAccount checkingAccount = new CheckingAccount("CHK-001", initialBalance);

        Account selectedAccount = savingsAccount;
        printAccountSummary(selectedAccount);

        while (true) {
            System.out.println("\n1 Withdraw | 2 Deposit | 3 Show balance | 4 Switch account | 5 Exit");
            System.out.print("Choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice! Choose a number between 1 and 5.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                double amount = readPositiveAmount(scanner, "Amount to withdraw: ");
                if (selectedAccount.withdraw(amount)) {
                    System.out.printf("New %s balance: P%.2f\n", selectedAccount.getAccountType(), selectedAccount.getBalance());
                }
            } else if (choice == 2) {
                double amount = readPositiveAmount(scanner, "Amount to deposit: ");
                if (selectedAccount.deposit(amount)) {
                    System.out.printf("New %s balance: P%.2f\n", selectedAccount.getAccountType(), selectedAccount.getBalance());
                }
            } else if (choice == 3) {
                printAccountSummary(selectedAccount);
            } else if (choice == 4) {
                if (selectedAccount == savingsAccount) {
                    selectedAccount = checkingAccount;
                } else {
                    selectedAccount = savingsAccount;
                }
                printAccountSummary(selectedAccount);
            } else if (choice == 5) {
                System.out.println("Thank you for using the system!");
                break;
            } else {
                System.out.println("Invalid selection! Please choose 1, 2, 3, 4, or 5.");
            }
        }
        scanner.close();
    }
}
