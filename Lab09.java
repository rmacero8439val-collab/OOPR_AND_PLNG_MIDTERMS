package com.mycompany.mavenproject1;import java.util.Scanner;

public class Lab09 {
    static Scanner input = new Scanner(System.in);
    static int[][] seats = new int[5][8];
    static int nextBookingId = 1;

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

    static int countAvailable() {
        int count = 0;
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 8; col++)
                if (seats[row][col] == 0) count++;
        return count;
    }

    static void displaySeats() {
        System.out.println("\nTHEATER SEATS (0 = Available, positive number = Booking ID");
        for (int row = 0; row < 5; row++) {
            System.out.print("Row " + (row + 1) + ": ");
            for (int col = 0; col < 8; col++)
                System.out.printf("%3d ", seats[row][col]);
            System.out.println();
        }
        System.out.println("Available seats: " + countAvailable());
    }

    static void bookGroup() {
        int groupSize = readInt("Enter group size (1-8): ", 1, 8);
        int foundRow = -1;
        int foundStart = -1;

        for (int row = 0; row < 5 && foundRow == -1; row++) {
            for (int start = 0; start <= 8 - groupSize; start++) {
                boolean blockIsFree = true;
                for (int offset = 0; offset < groupSize; offset++) {
                    if (seats[row][start + offset] != 0) {
                        blockIsFree = false;
                        break;
                    }
                }
                if (blockIsFree) {
                    foundRow = row;
                    foundStart = start;
                    break;
                }
            }
        }

        if (foundRow == -1) {
            System.out.println("No suitable consecutive block is available. No seats were changed.");
            return;
        }

        int bookingId = nextBookingId;
        for (int offset = 0; offset < groupSize; offset++)
            seats[foundRow][foundStart + offset] = bookingId;
        nextBookingId++;

        System.out.println("Booking successful. Booking ID: " + bookingId);
        System.out.println("Seats: Row " + (foundRow + 1) + ", columns "
                + (foundStart + 1) + " to " + (foundStart + groupSize));
    }

    static void cancelBooking() {
        int bookingId = readInt("Enter booking ID to cancel (1-" + Math.max(1, nextBookingId - 1) + "): ",
                1, Math.max(1, nextBookingId - 1));
        boolean found = false;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 8; col++) {
                if (seats[row][col] == bookingId) {
                    seats[row][col] = 0;
                    found = true;
                }
            }
        }
        if (found) System.out.println("Booking " + bookingId + " successfully cancelled.");
        else System.out.println("Unknown or already cancelled booking ID. No seats were changed.");
        System.out.println("Available seats: " + countAvailable());
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displaySeats();
            System.out.println("\n1. Book group");
            System.out.println("2. Cancel by booking ID");
            System.out.println("3. Display seats");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option (1-4): ", 1, 4);
            switch (choice) {
                case 1: bookGroup(); break;
                case 2: cancelBooking(); break;
                case 3: displaySeats(); break;
                case 4: running = false; break;
            }
        }
        System.out.println("Program ended.");
    }
}

