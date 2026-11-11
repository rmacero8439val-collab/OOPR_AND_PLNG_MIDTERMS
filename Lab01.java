package com.mycompany.mavenproject1;

import java.util.Scanner;

public class Lab01 {
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
    static int readInt(String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= min) return value;
                System.out.println("Invalid input. Enter a value that is at least " + min + ".");
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
                input.next();
            }
        }
    }
    static void runProgram() {
        int count = readInt("Enter number of students (minimum 1): ", 1);
        int[] scores = new int[count];
        for (int i = 0; i < count; i++) {
            scores[i] = readInt("Enter score of student " + (i + 1) + " (0-100): ", 0, 100);
        }
        int total = 0;
        int highest = scores[0];
        int lowest = scores[0];
        int passes = 0;
        int fails = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
            if (scores[i] > highest) highest = scores[i];
            if (scores[i] < lowest) lowest = scores[i];
            if (scores[i] >= 75) passes++;
            else fails++;
        }
        double average = (double) total / count;
        System.out.println("\nEXAM SCORE STATISTICS");
        System.out.printf("Average score: %.2f%n", average);
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);
        System.out.println("Passing scores: " + passes);
        System.out.println("Failing scores: " + fails);
        System.out.print("Student number(s) with the highest score: ");
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == highest) System.out.print((i + 1) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runProgram();
    }
}
