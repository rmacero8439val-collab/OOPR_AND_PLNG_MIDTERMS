package helloworld1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

class AppStudent {
    private String studentNo;
    private String studentName;
    private Date dateOfBirth;
    private Integer tariffPoints;
    private static int noOfStudents = 0;

    public AppStudent() {
        this.studentNo = "not known";
        this.studentName = "not known";
        try {
            this.dateOfBirth = new SimpleDateFormat("d MMMM yyyy").parse("1st January 1995".replace("st", ""));
        } catch (Exception e) {
            this.dateOfBirth = new Date();
        }
        this.tariffPoints = 20;
        noOfStudents++;
    }

    public AppStudent(String studentNo, String studentName, Date dateOfBirth) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        setTariffPoints(calculateAutomaticTariffPoints(studentName));
        noOfStudents++;
    }

    private int calculateAutomaticTariffPoints(String name) {
        if (name == null || name.trim().isEmpty()) {
            return 20;
        }
        int basePoints = 20 + (name.replaceAll("\\s+", "").length() * 15);
        if (basePoints > 280) return 280;
        if (basePoints < 20) return 20;
        return basePoints;
    }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Integer getTariffPoints() { return tariffPoints; }
    public void setTariffPoints(Integer tariffPoints) {
        if (tariffPoints != null && tariffPoints >= 20 && tariffPoints <= 280) {
            this.tariffPoints = tariffPoints;
        } else {
            this.tariffPoints = 20;
        }
    }

    public static int getNoOfStudents() { return noOfStudents; }
    public String getFormattedDOB() { return new SimpleDateFormat("dd/MM/yyyy").format(this.dateOfBirth); }
}

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nChoose the program you want to run");
            System.out.println("Number 1: Positive/Negative Double Calculations");
            System.out.println("Number 2: Array Extrema & Distinct Elements");
            System.out.println("Number 3: Element Deletion by Position");
            System.out.println("Number 4: Separate Even and Odd Elements");
            System.out.println("Number 5: Star and Alphabet Matrix Pattern");
            System.out.println("Number 6: Student Management System Logs");
            System.out.println("Number 7: Desktop File Content Reader");
            System.out.print("\nEnter choice (1-7): ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine(); 

            System.out.println("\nExecuting Program...\n");

            switch (choice) {
                case 1: runProject1(scanner); break;
                case 2: runProject2(scanner); break;
                case 3: runProject3(scanner); break;
                case 4: runProject4(scanner); break;
                case 5: runProject5(); break;
                case 6: runProject6(scanner); break; 
                case 7: runProject7(); break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    continue;
            }

            System.out.print("Do you want to continue ? Y/N: ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("N")) {
                keepRunning = false;
                System.out.println("Exiting runner. Goodbye!");
            }
        }
        scanner.close();
    }

    private static void runProject1(Scanner sc) {
        double[] arr = new double[10];
        System.out.println("Enter 10 double values:");
        for (int i = 0; i < 10; i++) {
            if (sc.hasNextDouble()) { arr[i] = sc.nextDouble(); }
        }
        sc.nextLine();
        double sum = 0; int posCount = 0; int negCount = 0; double min = arr[0];
        for (int i = 0; i < 10; i++) {
            if (arr[i] > 0) { sum += arr[i]; posCount++; }
            if (arr[i] < 0) { negCount++; }
            if (arr[i] < min) { min = arr[i]; }
        }
        double avg = (posCount > 0) ? sum / posCount : 0;
        System.out.println("Sum of positive: " + sum);
        System.out.println("Average of positive: " + avg);
        System.out.println("Count of negative: " + negCount);
        System.out.println("Minimum value: " + min);
    }

    private static void runProject2(Scanner sc) {
        int[] arr = new int[8];
        System.out.println("Enter 8 integer values:");
        for (int i = 0; i < 8; i++) {
            if (sc.hasNextInt()) { arr[i] = sc.nextInt(); }
        }
        sc.nextLine();
        int[] distinct = Arrays.stream(arr).distinct().sorted().toArray();
        System.out.println("Unique array: " + Arrays.toString(distinct));
        if (distinct.length >= 2) {
            System.out.println("Second smallest: " + distinct[1]);
            System.out.println("Second largest: " + distinct[distinct.length - 2]);
        } else {
            System.out.println("Not enough unique elements.");
        }
    }

    private static void runProject3(Scanner sc) {
        int[] arr = new int[5];
        System.out.print("Enter Data in Array: ");
        for (int i = 0; i < 5; i++) {
            if (sc.hasNextInt()) { arr[i] = sc.nextInt(); }
        }
        System.out.print("Stored Data in Array: ");
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
        System.out.print("Enter poss. of Element to Delete: ");
        int pos = sc.nextInt();
        sc.nextLine();
        System.out.print("New data in Array: ");
        for (int i = 0; i < arr.length; i++) {
            if (i != pos) { System.out.print(arr[i] + " "); }
        }
        System.out.println();
    }

    private static void runProject4(Scanner sc) {
        System.out.print("Enter Size of Array : ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        ArrayList<Integer> evens = new ArrayList<>();
        ArrayList<Integer> odds = new ArrayList<>();
        System.out.print("Enter any " + size + " elements in Array: ");
        for (int i = 0; i < size; i++) {
            if (sc.hasNextInt()) {
                arr[i] = sc.nextInt();
                if (arr[i] % 2 == 0) { evens.add(arr[i]); } 
                else { odds.add(arr[i]); }
            }
        }
        sc.nextLine();
        System.out.print("Even Elements: ");
        for (int e : evens) System.out.print(e + " ");
        System.out.println();
        System.out.print("Odd Elements: ");
        for (int o : odds) System.out.print(o + " ");
        System.out.println();
    }

    private static void runProject5() {
        for (int i = 1; i <= 4; i++) {
            System.out.print("* ");
            for (int j = 1; j < i; j++) { System.out.print("A"); }
            if (i > 1) { System.out.print("*"); }
            System.out.println();
        }
    }

    private static void runProject6(Scanner sc) {
        System.out.println("Enter Student 1 Details");
        System.out.print("Enter Student No: ");
        String id1 = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name1 = sc.nextLine();
        System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
        String dobInput1 = sc.nextLine();
        Date dob1 = parseDate(dobInput1);
        AppStudent student1 = new AppStudent(id1, name1, dob1);

        System.out.println("\nEnter Student 2 Details");
        System.out.print("Enter Student No: ");
        String id2 = sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name2 = sc.nextLine();
        System.out.print("Enter Date of Birth (dd/MM/yyyy): ");
        String dobInput2 = sc.nextLine();
        Date dob2 = parseDate(dobInput2);
        AppStudent student2 = new AppStudent(id2, name2, dob2);

        System.out.println("\nRegistered Student Details");
        printStudentDetails(student1);
        System.out.println();
        printStudentDetails(student2);
        System.out.println("\nTotal Students Registered overall: " + AppStudent.getNoOfStudents());
    }

    private static Date parseDate(String input) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(input);
        } catch (Exception e) {
            System.out.println("Invalid date format. Defaulting to today's date.");
            return new Date();
        }
    }

    private static void printStudentDetails(AppStudent student) {
        System.out.println("StudentNo: " + student.getStudentNo());
        System.out.println("Name: " + student.getStudentName());
        System.out.println("DOB: " + student.getFormattedDOB());
        System.out.println("Points: " + student.getTariffPoints());
    }

    private static void runProject7() {
        String filepath = "C:\\Users\\SBH-CL3-WS01\\Desktop\\data.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
        while ((line = reader.readLine()) != null) { System.out.println(line); }
            } catch (FileNotFoundException e) {
                System.out.println("Could not locate file at: " + filepath);
                } catch (IOException e) {
                System.out.println("An error occurred while reading the file");
            }
        }
    }
