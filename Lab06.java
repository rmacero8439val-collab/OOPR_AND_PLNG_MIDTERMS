package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab06 {
    static Scanner input = new Scanner(System.in);
    static final int PRODUCT_COUNT = 4;
    static String[] names = {"USB", "Notebook", "Pen", "Folder"};
    static int[] prices = {350, 200, 20, 15};
    static int[] stock = {10, 10, 20, 20};
    static int[] cart = new int[PRODUCT_COUNT];

    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= min && value <= max) return value;
                System.out.println("Invalid input. Enter a value from " + min + " to " + max + ".");
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
                input.next();
            }
        }
    }

    static int subtotalPesos() {
        int subtotal = 0;
        for (int i = 0; i < PRODUCT_COUNT; i++) subtotal += cart[i] * prices[i];
        return subtotal;
    }

    static void viewCart() {
        System.out.println("\nSHOPPING CART");
        for (int i = 0; i < PRODUCT_COUNT; i++) {
            System.out.printf("%-10s Qty: %d  Unit price: PHP %d  Amount: PHP %d%n",
                    names[i], cart[i], prices[i], cart[i] * prices[i]);
        }
        System.out.println("Subtotal: PHP " + subtotalPesos());
    }

    static void addItem() {
        for (int i = 0; i < PRODUCT_COUNT; i++) {
            System.out.println((i + 1) + ". " + names[i] + " - PHP " + prices[i]
                    + " (Available stock: " + stock[i] + ", In cart: " + cart[i] + ")");
        }
        int product = readInt("Choose product (1-4): ", 1, 4) - 1;
        if (stock[product] == 0) {
            System.out.println("That product is out of stock.");
            return;
        }
        int quantity = readInt("Quantity to add (1-" + stock[product] + "): ", 1, stock[product]);
        if (cart[product] + quantity > stock[product]) {
            System.out.println("Rejected: total cart quantity would exceed available stock.");
        } else {
            cart[product] += quantity;
            System.out.println("Item added. Cart quantity updated.");
        }
    }

    static void removeItem() {
        for (int i = 0; i < PRODUCT_COUNT; i++)
            System.out.println((i + 1) + ". " + names[i] + " (In cart: " + cart[i] + ")");
        int product = readInt("Choose product (1-4): ", 1, 4) - 1;
        if (cart[product] == 0) {
            System.out.println("That product is not in the cart.");
            return;
        }
        int quantity = readInt("Quantity to remove (1-" + cart[product] + "): ", 1, cart[product]);
        cart[product] -= quantity;
        System.out.println("Quantity removed.");
    }

    static boolean isCartEmpty() {
        for (int quantity : cart) if (quantity > 0) return false;
        return true;
    }

    static void checkout() {
        if (isCartEmpty()) {
            System.out.println("Checkout rejected: the cart is empty.");
            return;
        }

        int subtotalCentavos = subtotalPesos() * 100;
        int discountCentavos = 0;
        if (subtotalPesos() >= 1000) {
            discountCentavos = (subtotalCentavos * 10 + 50) / 100;
        }
        int finalCentavos = subtotalCentavos - discountCentavos;
        System.out.printf("Subtotal: PHP %.2f%n", subtotalCentavos / 100.0);
        System.out.printf("Discount: PHP %.2f%n", discountCentavos / 100.0);
        System.out.printf("Total: PHP %.2f%n", finalCentavos / 100.0);

        int cashPesos = readInt("Enter cash payment in whole pesos: ", 0, Integer.MAX_VALUE / 100);
        int cashCentavos = cashPesos * 100;
        if (cashCentavos < finalCentavos) {
            System.out.println("Insufficient payment. Cart and stock remain unchanged.");
            return;
        }

        for (int i = 0; i < PRODUCT_COUNT; i++) stock[i] -= cart[i];

        System.out.println("\nRECEIPT");
        for (int i = 0; i < PRODUCT_COUNT; i++) {
            if (cart[i] > 0) {
                System.out.printf("%-10s x %d = PHP %.2f%n", names[i], cart[i],
                        (cart[i] * prices[i]) / 1.0);
            }
        }
        System.out.printf("Subtotal: PHP %.2f%n", subtotalCentavos / 100.0);
        System.out.printf("Discount: PHP %.2f%n", discountCentavos / 100.0);
        System.out.printf("Amount paid: PHP %.2f%n", cashCentavos / 100.0);
        System.out.printf("Change: PHP %.2f%n", (cashCentavos - finalCentavos) / 100.0);
        System.out.printf("Total: PHP %.2f%n", finalCentavos / 100.0);
        System.out.println("Sale completed. Thank you!");

        for (int i = 0; i < PRODUCT_COUNT; i++) cart[i] = 0;
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nSHOPPING CART MENU");
            System.out.println("1. Add item");
            System.out.println("2. Remove quantity");
            System.out.println("3. View cart");
            System.out.println("4. Pay / Checkout");
            System.out.println("5. Cancel sale");
            System.out.println("6. Exit");
            int choice = readInt("Choose an option (1-6): ", 1, 6);

            switch (choice) {
                case 1: addItem(); break;
                case 2: removeItem(); break;
                case 3: viewCart(); break;
                case 4: checkout(); break;
                case 5:
                    for (int i = 0; i < PRODUCT_COUNT; i++) cart[i] = 0;
                    System.out.println("Sale cancelled. Stock was not changed.");
                    break;
                case 6: running = false; break;
            }
        }
        System.out.println("Program ended.");
    }
}
