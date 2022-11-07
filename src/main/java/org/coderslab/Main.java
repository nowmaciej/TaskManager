package org.coderslab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final String DATABASE_FILE = "tasks.csv";
    public static String[][] databaseArray = getDatabase(DATABASE_FILE);

    public static void main(String[] args) {
        mainMenu();
    }

    public static String[][] getDatabase(String databaseFileLocation) {
        String[][] arrayMadeFromDatabaseFile = new String[0][3];
        File file = new File(databaseFileLocation);

        try(Scanner fs = new Scanner(file)) {
            while (fs.hasNextLine()) {
                arrayMadeFromDatabaseFile = Arrays.copyOf(arrayMadeFromDatabaseFile, arrayMadeFromDatabaseFile.length + 1);
                arrayMadeFromDatabaseFile[arrayMadeFromDatabaseFile.length - 1] = fs.nextLine().split(",");
            }
        } catch (Exception e) {
            System.out.println("Can't read database file");
        }

        return arrayMadeFromDatabaseFile;
    }


    public static void mainMenu() {
        System.out.print(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        System.out.println(pl.coderslab.ConsoleColors.RESET);
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

        for(int i = 0; i<databaseArray.length; i++) {
            System.out.print(i+" : ");
            System.out.println(Arrays.toString(databaseArray[i]));
        }

        mainMenu();
    }

    private static void removeMenu() {

    }

    private static void addMenu() {
        Scanner scan = new Scanner(System.in);
        StringBuilder newTask = new StringBuilder();

        System.out.println("Now adding task to database");
        System.out.print("Enter task name: ");
        newTask.append(scan.nextLine() + ", ");
        System.out.print("Enter task date [yyyy-mm-dd]: ");
        newTask.append(scan.nextLine() + ", ");
        System.out.print("Enter task status [true/false]: ");
        newTask.append(scan.nextLine());

        databaseArray = Arrays.copyOf(databaseArray, databaseArray.length + 1);
        databaseArray[databaseArray.length - 1] = newTask.toString().split(",");

        mainMenu();

    }
}