package mypackage;

import java.util.*;
import java.io.*;

public class Helper {
    public static final String CUR_UP = "\u001B[1A";

    public static int getNextIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print("\r"+" ".repeat(50)+"\r"+prompt);

            // If user inputs an int, return that int
            if (scanner.hasNextInt()) 
                return scanner.nextInt();

            // If user didn't input an int, display error
            System.out.print("Invalid input: not an integer. Try again."+CUR_UP);

            // Clear input buffer
            scanner.nextLine();
        }
    }
}