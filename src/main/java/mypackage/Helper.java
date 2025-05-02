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
    public static final String CLR_LINE = "\r" + " ".repeat(terminal.getWidth()) + "\r";

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
            // int input = scanner.nextInt();
            // scanner.nextLine(); // clear input buffer
            // if (onlyPositive) {
            // if (input >= 0) return input;
            // } else {
            // return input;
            // }
            // } else {
            // scanner.nextLine();
            // }

            // Clear input buffer
            // while (scanner.hasNextLine()) {
            // scanner.nextLine();
            // }
        }
    }

    public static int getNextIntInput(Scanner scanner, String prompt) {
        return getNextIntInput(scanner, prompt, false);
    }

    public static int getNextIntInputInterruptable(Scanner scanner, String prompt, boolean onlyZeroOrPositive,
            String interruptStr) {
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

    public static double getNextDoubleInputInterruptable(Scanner scanner, String prompt, boolean onlyZeroOrPositive,
            String interruptStr) {
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

    public static String getNonEmptyStringInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + prompt);
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                break;
            }
            System.out.print("Invalid input. Please try again.");
            System.out.print(JLineMenu.RST_CUR + CLR_LINE);
        }
        System.out.print(CLR_LINE); // clear error message below prompt line
        return input;
    }

    /**
     * 
     * @param scanner
     * @param prompt
     * @param interruptStr If user inputs this, that means they want to do certain
     *                     action like "go back" instead of entering data
     * @return a non-empty string from user input. Returns null if interruptStr is
     *         received from input
     */
    public static String getNonEmptyStringInputInterruptable(Scanner scanner, String prompt, String interruptStr) {
        String input;
        while (true) {
            System.out.print(JLineMenu.SAV_CUR + prompt);
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(interruptStr)) {
                return null;
            }
            if (!input.isEmpty()) {
                break;
            }
            System.out.print("Invalid input. Please try again.");
            System.out.print(JLineMenu.RST_CUR + CLR_LINE);
        }
        System.out.print(CLR_LINE); // clear error message below "Name: ..."
        return input;
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
        for (int i = 0; i < n; i++) {
            System.out.print(CUR_DOWN + CLR_LINE);
        }
        System.out.println(JLineMenu.RST_CUR);
    }

    /**
     * Escapes a string for safe inclusion in a CSV file
     * - Doubles any double quotes inside the string.
     * - Wraps the whole field in double quotes if it contains:
     * commas, quotes, or newlines
     */
    public static String escapeForCSV(String input) {
        if (input == null)
            return "";

        boolean needsQuoting = input.contains(",") || input.contains("\"") || input.contains("\n")
                || input.contains("\r");
        String escaped = input.replace("\"", "\"\""); // escape internal quotes

        return needsQuoting ? "\"" + escaped + "\"" : escaped;
    }

    /**
     * Parses a single line of CSV and returns the list of fields.
     * Handles quoted fields, escaped quotes, commas, and newlines.
     */
    public static List<String> parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        if (line == null || line.isEmpty()) return result;

        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        current.append('"'); // escaped quote
                        i++;
                    } else {
                        inQuotes = false; // end of quoted field
                    }
                } else {
                    current.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    result.add(current.toString());
                    current.setLength(0); // clear the builder
                } else {
                    current.append(c);
                }
            }
        }

        result.add(current.toString()); // add the last field
        return result;
    }

    public static boolean doesFileExist(String filename) {
        File file = new File(filename);
        return file.exists();
    }
}