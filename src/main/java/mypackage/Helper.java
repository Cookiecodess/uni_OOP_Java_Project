package mypackage;

import java.util.*;
import java.io.*;

public class Helper {
    public static final String CUR_UP = "\u001B[1A";
    public static final String CLR_LINE = "\r"+" ".repeat(50)+"\r";

    public static int getNextIntInput(Scanner scanner, String prompt, boolean onlyPositive) {
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(CLR_LINE + prompt);

            String inputStr = scanner.nextLine();
            try {
                int input = Integer.valueOf(inputStr);
                if (onlyPositive && input < 0) {
                    System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
            }

            // If user inputs an int, return that int
            // if (scanner.hasNextInt()) {
            //     int input = scanner.nextInt();
            //     scanner.nextLine(); // clear input buffer
            //     if (onlyPositive) {
            //         if (input >= 0) return input;
            //     } else {
            //         return input;
            //     }
            // } else {
            //     scanner.nextLine();
            // }

            
            // Clear input buffer
            // while (scanner.hasNextLine()) {
            //     scanner.nextLine();
            // }
        }
    }

    public static int getNextIntInput(Scanner scanner, String prompt) {
        return getNextIntInput(scanner, prompt, false);
    }

    public static double getNextDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(CLR_LINE + prompt);

            String inputStr = scanner.nextLine();
            try {
                double input = Double.valueOf(inputStr);
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositiveInt(String str) {
        try {
            int integer = Integer.parseInt(str);
            if (integer > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}