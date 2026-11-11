package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab04 {
    static Scanner input = new Scanner(System.in);

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

    static void displaySeats(int[][] seats) {
        System.out.println("\nCLASSROOM SEATS (0 = Available, 1 = Booked)");
        System.out.print("       ");
        for (int col = 0; col < 5; col++) System.out.print((col + 1) + " ");
        System.out.println();
        for (int row = 0; row < 4; row++) {
            System.out.print("Row " + (row + 1) + "  ");
            for (int col = 0; col < 5; col++) System.out.print(seats[row][col] + " ");
            System.out.println();
        }
    }

    static int countAvailable(int[][] seats) {
        int available = 0;
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 5; col++)
                if (seats[row][col] == 0) available++;
        return available;
    }

    public static void main(String[] args) {
        int[][] seats = new int[4][5];
        boolean running = true;

        while (running) {
            displaySeats(seats);
            System.out.println("\n1. Book a seat");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Display seats");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option (1-4): ", 1, 4);

            if (choice == 1 || choice == 2) {
                int row = readInt("Enter row number (1-4): ", 1, 4) - 1;
                int col = readInt("Enter column number (1-5): ", 1, 5) - 1;

                if (choice == 1) {
                    if (seats[row][col] == 1) {
                        System.out.println("That seat is already booked. No change was made.");
                    } else {
                        seats[row][col] = 1;
                        System.out.println("Seat successfully booked.");
                    }
                } else {
                    if (seats[row][col] == 0) {
                        System.out.println("That seat is empty. No change was made.");
                    } else {
                        seats[row][col] = 0;
                        System.out.println("Booking successfully cancelled.");
                    }
                }
                System.out.println("Available seats: " + countAvailable(seats));
            } else if (choice == 3) {
                displaySeats(seats);
                System.out.println("Available seats: " + countAvailable(seats));
            } else {
                running = false;
            }
        }
        System.out.println("Program ended.");
    }
}

