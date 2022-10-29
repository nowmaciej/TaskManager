package org.coderslab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {

    public static final String DATABASE_FILE = "database.csv";

    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String RESET = "\033[0m";  // Text Reset

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("Please select an option:");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

        Scanner scan = new Scanner(System.in);
        boolean inMenu = true;

        while (inMenu) {
            switch (scan.next()) {
                case "add":
                    addMenu();
                    inMenu = false;
                    break;
                case "remove":
                    removeMenu();
                    inMenu = false;
                    break;
                case "list":
                    listMenu();
                    inMenu = false;
                    break;
                case "exit":
                    exitOption();
                    inMenu = false;
                    break;
                default:
                    System.out.println("You need to choose an action!");
            }
        }
    }

    private static void exitOption() {
        System.out.println("Thank you!\nSee you next time");
    }

    private static void listMenu() {
        File dbFile = new File(DATABASE_FILE);

        try (Scanner scan = new Scanner(dbFile)) {
            int i = 0;
            while (scan.hasNextLine()) {
                System.out.println(i + ": " + scan.nextLine());
                i++;
            }
        } catch (IOException e) {
            System.out.println("Can't load file " + DATABASE_FILE);
        }
    }

    private static void removeMenu() {
        System.out.println("you are in remove");

    }

    private static void addMenu() {
        try (FileWriter fw = new FileWriter(DATABASE_FILE, true)) {

            Scanner scan = new Scanner(System.in);

            System.out.println("Now adding task to database");
            System.out.print("Enter task name: ");
            String taskName = scan.nextLine();
            System.out.print("Enter task date [yyyy-mm-dd]: ");
            String taskDate = scan.nextLine();
            System.out.print("Enter task status [true/false]: ");
            boolean taskStatus = scan.nextBoolean();


            String newLine = taskName + ", " + taskDate + ", " + taskStatus;
            fw.append(newLine + "\n");

            System.out.println("Added new task: " + newLine);

        } catch (IOException e) {
            System.out.println("File " + DATABASE_FILE + " not found!");
        }

        mainMenu();

    }
}