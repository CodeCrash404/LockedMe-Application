package main_5;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class class_5 {
    private static final String DIRECTORY = "C:\\Users\\paolo\\eclipse-workspace\\Project_5\\main_5";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int userChoice;
            do {
                displayWelcomeScreen();
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                userChoice = scanner.nextInt();
                handleUserChoice(scanner, userChoice);
            } while (userChoice != 3);
        }

        exitApplication();
    }

    private static void displayWelcomeScreen() {
        System.out.println("Welcome to LockedMe.com!");
        System.out.println("Developed by Paolo Salameh");
        System.out.println("-----------------------");
        System.out.println("Main menu options:");
        System.out.println("1. List files in ascending order");
        System.out.println("2. Perform file operations");
        System.out.println("3. Exit the application");
        System.out.println("Please enter your choice(1, 2, or 3):");
    }

    private static void handleUserChoice(Scanner scanner, int userChoice) {
        switch (userChoice) {
            case 1:
                listFiles();
                break;
            case 2:
                performFileOperations(scanner);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void listFiles() {
        File directory = new File(DIRECTORY);
        File[] files = directory.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName));
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    private static void performFileOperations(Scanner scanner) {
        System.out.println("Enter the operation (add, delete, search) and file name:");
        String operation = scanner.next();
        String fileName = scanner.next();

        switch (operation.toLowerCase()) {
            case "add":
                addFile(fileName);
                break;
            case "delete":
                deleteFile(fileName);
                break;
            case "search":
                searchFile(fileName);
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
        }
    }

    private static void addFile(String fileName) {
        File newFile = new File(DIRECTORY, fileName);
        try {
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    private static void deleteFile(String fileName) {
        File fileToDelete = new File(DIRECTORY, fileName);
        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println("File deleted: " + fileName);
            } else {
                System.out.println("An error occurred while deleting the file.");
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    private static void searchFile(String fileName) {
        File directory = new File(DIRECTORY);
        File[] files = directory.listFiles();
        boolean fileFound = false;

        for (File file : files) {
            if (file.getName().equals(fileName)) {
                System.out.println("File found: " + fileName);
                fileFound = true;
                break;
            }
        }

        if (!fileFound) {
            System.out.println("File not found: " + fileName);
        }
    }

    private static void exitApplication() {
        System.out.println("Thank you for using LockedMe.com. Goodbye!");
        System.exit(0);
    }
}
