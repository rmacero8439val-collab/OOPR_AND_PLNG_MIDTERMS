package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab05 {
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

    public static void main(String[] args) {
        int[][] temperatures = new int[3][4];
        int maximum = -20;

        System.out.println("TEMPERATURE MONITORING");
        for (int sensor = 0; sensor < 3; sensor++) {
            System.out.println("\nSensor " + (sensor + 1));
            for (int period = 0; period < 4; period++) {
                temperatures[sensor][period] =
                        readInt("Enter temperature for period " + (period + 1) + " (-20 to 60 C): ", -20, 60);
                if (temperatures[sensor][period] > maximum) maximum = temperatures[sensor][period];
            }
        }

        System.out.println("\nAverage temperature per sensor:");
        for (int sensor = 0; sensor < 3; sensor++) {
            int total = 0;
            for (int period = 0; period < 4; period++) total += temperatures[sensor][period];
            System.out.printf("Sensor %d: %.2f C%n", sensor + 1, total / 4.0);
        }

        System.out.println("\nTemperature alerts (greater than 35 C):");
        boolean hasAlert = false;
        for (int sensor = 0; sensor < 3; sensor++) {
            for (int period = 0; period < 4; period++) {
                if (temperatures[sensor][period] > 35) {
                    System.out.println("Sensor " + (sensor + 1) + ", Period " + (period + 1)
                            + ": " + temperatures[sensor][period] + " C");
                    hasAlert = true;
                }
            }
        }
        if (!hasAlert) System.out.println("No alerts.");

        System.out.println("\nMaximum temperature: " + maximum + " C");
        System.out.print("Location(s) of maximum: ");
        for (int sensor = 0; sensor < 3; sensor++) {
            for (int period = 0; period < 4; period++) {
                if (temperatures[sensor][period] == maximum) {
                    System.out.print("[Sensor " + (sensor + 1) + ", Period " + (period + 1) + "] ");
                }
            }
        }
        System.out.println();
    }
}

