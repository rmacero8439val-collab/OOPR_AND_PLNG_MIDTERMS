package com.mycompany.mavenproject1;
import java.util.Scanner;

public class Lab08 {
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
        int studentCount = readInt("Enter number of students (2-10): ", 2, 10);
        int[][] marks = new int[studentCount][4];
        double[] average = new double[studentCount];
        int[] order = new int[studentCount];
        double[] subjectAverage = new double[4];
        boolean[] qualified = new boolean[studentCount];

        for (int student = 0; student < studentCount; student++) {
            System.out.println("\nStudent " + (student + 1));
            int total = 0;
            boolean allSubjectsAtLeast60 = true;
            for (int subject = 0; subject < 4; subject++) {
                marks[student][subject] =
                        readInt("Enter score for subject " + (subject + 1) + " (0-100): ", 0, 100);
                total += marks[student][subject];
                if (marks[student][subject] < 60) allSubjectsAtLeast60 = false;
            }
            average[student] = total / 4.0;
            qualified[student] = average[student] >= 75.0 && allSubjectsAtLeast60;
            order[student] = student;
        }

        for (int subject = 0; subject < 4; subject++) {
            int total = 0;
            for (int student = 0; student < studentCount; student++)
                total += marks[student][subject];
            subjectAverage[subject] = total / (double) studentCount;
        }

        double lowestSubjectAverage = subjectAverage[0];
        for (int subject = 1; subject < 4; subject++)
            if (subjectAverage[subject] < lowestSubjectAverage)
                lowestSubjectAverage = subjectAverage[subject];

        for (int i = 0; i < studentCount - 1; i++) {
            int bestPosition = i;
            for (int j = i + 1; j < studentCount; j++) {
                if (average[order[j]] > average[order[bestPosition]]) bestPosition = j;
            }
            int temp = order[i];
            order[i] = order[bestPosition];
            order[bestPosition] = temp;
        }

        System.out.println("\nSTUDENT PERFORMANCE");
        for (int student = 0; student < studentCount; student++) {
            System.out.printf("Student %d - Average: %.2f - %s%n", student + 1, average[student],
                    qualified[student] ? "Qualified" : "Not Qualified");
        }

        System.out.println("\nSubject averages:");
        for (int subject = 0; subject < 4; subject++)
            System.out.printf("Subject %d: %.2f%n", subject + 1, subjectAverage[subject]);

        System.out.print("Subject(s) tied for lowest average: ");
        for (int subject = 0; subject < 4; subject++)
            if (Double.compare(subjectAverage[subject], lowestSubjectAverage) == 0)
                System.out.print("Subject " + (subject + 1) + " ");
        System.out.println();

        System.out.println("\nRANKING (COMPETITION RANKS)");
        int previousRank = 0;
        for (int position = 0; position < studentCount; position++) {
            int studentIndex = order[position];
            int rank;
            if (position == 0) {
                rank = 1;
            } else if (Double.compare(average[studentIndex], average[order[position - 1]]) == 0) {
                rank = previousRank;
            } else {
                rank = position + 1;
            }
            previousRank = rank;
            System.out.printf("Rank %d - Student %d - Average: %.2f%n",
                    rank, studentIndex + 1, average[studentIndex]);
        }
    }
}

