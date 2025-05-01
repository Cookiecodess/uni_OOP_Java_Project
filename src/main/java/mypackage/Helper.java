package mypackage;

import java.util.*;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import static mypackage.JLineMenu.SAV_CUR;

import java.io.*;

public class Helper {
    static Terminal terminal = JLineMenu.terminal;
    
    public static final String CUR_UP = "\u001B[1A";
    public static final String CUR_DOWN = "\u001B[1B";
    public static final String CLR_LINE = "\r"+" ".repeat(terminal.getWidth())+"\r";

    static final int INTRP = -1; // the getInput methods return this if a designated interruptStr is input

    public static int getNextIntInput(Scanner scanner, String prompt, boolean onlyZeroOrPositive) {
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(CLR_LINE + prompt);

            String inputStr = scanner.nextLine();
            try {
                int input = Integer.valueOf(inputStr);
                if (onlyZeroOrPositive && input < 0) {
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

    public static int getNextIntInputInterruptable(Scanner scanner, String prompt, boolean onlyZeroOrPositive, String interruptStr) {
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(CLR_LINE + prompt);

            String inputStr = scanner.nextLine();

            if (inputStr.equalsIgnoreCase(interruptStr)) {
                return INTRP;
            }

            try {
                int input = Integer.valueOf(inputStr);
                if (onlyZeroOrPositive && input < 0) {
                    System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
            }
        }
    }

    public static double getNextDoubleInput(Scanner scanner, String prompt, boolean onlyZeroOrPositive) {
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(CLR_LINE + prompt);

            String inputStr = scanner.nextLine();
            try {
                double input = Double.valueOf(inputStr);
                if (onlyZeroOrPositive && input < 0) {
                    System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
            }
        }
    }

    public static double getNextDoubleInput(Scanner scanner, String prompt) {
        return getNextDoubleInput(scanner, prompt, false);
    }

    public static double getNextDoubleInputInterruptable(Scanner scanner, String prompt, boolean onlyZeroOrPositive, String interruptStr) {
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + Helper.CLR_LINE);
            System.out.print(CLR_LINE + prompt);

            String inputStr = scanner.nextLine();

            if (inputStr.equalsIgnoreCase(interruptStr)) {
                return INTRP;
            }

            try {
                double input = Double.valueOf(inputStr);
                if (onlyZeroOrPositive && input < 0) {
                    System.out.print("Invalid input. Try again." + JLineMenu.RST_CUR);
                    continue;
                }
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

    public static void clearLinesBelow(int n) {
        System.out.print(JLineMenu.SAV_CUR);
        for (int i=0; i<n; i++) {
            System.out.print(CUR_DOWN + CLR_LINE);
        }
        System.out.println(JLineMenu.RST_CUR);
    }
}