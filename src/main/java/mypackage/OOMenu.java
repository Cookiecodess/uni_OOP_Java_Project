/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mypackage;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

//=============== Object-Oriented Menu ======================
public class OOMenu {
    // static final String SAV_CUR = "\033[s"; // save cursor position
    // static final String RST_CUR = "\033[u"; // restore cursor position
    // static final String CLEAR_LINE_CUR_TIL_END = "\033[K"; // clear from cursor
    // until end of line
    public static final String HIDE_CUR = "\033[?25l";
    public static final String SHOW_CUR = "\033[?25h";

    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    // text color
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // background color
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_MAGENTA = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    static Terminal terminal = JLineMenu.terminal;
    private static Scanner scanner;
    public static LineReader reader = JLineMenu.reader;

    // For "Back" and "Exit"
    static class GlobalMenuOption implements MenuItem {
        private String label;
        private String description;

        public GlobalMenuOption(String label, String description) {
            this.label = label;
            this.description = description;
        }

        @Override
        public String getItemLabel() {
            return this.label;
        }
        @Override
        public void printInfo() {
            System.out.println(description);
        }
    }

    static final GlobalMenuOption BACK = new GlobalMenuOption("Back", "Go back to the previous page.");
    static final GlobalMenuOption EXIT = new GlobalMenuOption("Exit", "Exit this program");

    // Global constants for all menus
    public static final int BACK_OPTION_INT = -1;
    public static final int LEFT_RIGHT_PADDING = 10; // the number of ='s at either side of the header
    public static final int MENU_WIDTH = 40; // max width of menu

    // A static block is executed ONCE when this class is loaded.
    // static {
    //     // Initialize terminal
    //     try {
    //         terminal = TerminalBuilder.builder()
    //                 .system(true)
    //                 .jna(true)
    //                 .jansi(false) // Avoid Jansi conflict
    //                 .build();

    //         reader = LineReaderBuilder.builder()
    //                 .terminal(terminal)
    //                 .build();

    //     } catch (IOException e) {
    //         System.err.println("\nFailed to initialize terminal: " + e.getMessage() + "\n");
    //         System.exit(1); // exits with error
    //     }

    //     // Initialize scanner
    //     scanner = new Scanner(System.in);
    // }

    // For testing this class's functionality
    public static void main(String[] args) throws IOException {
        clearScreen();
        printHeader("bello", LEFT_RIGHT_PADDING);

        System.out.println("Hello from OOMenu.main");
        terminal.writer().println("\u001b[7m> " + "yo! testing" + "\u001b[0m");

        ProductInventory inventory = new ProductInventory();
        inventory.init();

        List<Product> products = inventory.getProductsByCategoryName("Keyboards");
        List<MenuItem> menuItems = new ArrayList<>(products);

        // List<String> productNames = PropertyExtractor.extractProperty(products, "name");
        String header = "Products: Keyboards";
        OOMenu productMenu = new OOMenu(header, menuItems, "Select a product for more details.", true, false);
        
        // System.out.println(productMenu.d);
        productMenu.printAdditionalInfo();

        productMenu.currentSelection = 0;
        while (true) {
            clearScreen();
    
            // Print header
            printHeader(header, LEFT_RIGHT_PADDING);
    
            // print message after header
            // if (msg.length() > 0)
            //     System.out.println(msg + "\n");
    
            // Display menu with highlighting
            productMenu.drawOptions();
    
            // Display text prompt
            System.out.println("\n" + "testing...");
    
            // divider (==========)
            productMenu.printDivider("=");
    
            // print info/description related to the currently selected item of the menu
            productMenu.printAdditionalInfo();

            int c = terminal.reader().read();
            switch (c) {
                case 27 -> { // 1st byte of an arrow keypress: ESC
                    terminal.reader().read(); // read 2nd byte of arrow keypress: 91 ([) or 79
                    c = terminal.reader().read();
                    switch (c) {
                        case 65 ->
                            productMenu.moveCursorUp();
                        case 66 ->
                            productMenu.moveCursorDown();
                        // case 67 ->
                        //     System.out.println("right");
                        // case 68 ->
                        //     System.out.println("left");
                    }
                }
            }
        }


        // terminal.close(); // this line may throw an IOException, that's why the main method needs a throws
                          // clause ("throws IOException")
    }

    // Instance variables
    private String textHeader;
    List<MenuItem> options;
    // List<String> optionLabels;
    int numOfOptions; // want to access in subclass
    private String textPrompt;
    // private MenuBottomContent bottomContent;
    private boolean hasBackOption;
    private boolean hasExitOption;

    int firstItemIdx = 0;
    int currentSelection = 0; // want to access in subclass
    private boolean running = true;

    // Public constructor of a JLineMenu object
    public OOMenu(String textHeader, List<MenuItem> options, String textPrompt, boolean hasBackOption,
            boolean hasExitOption) {

        this.textHeader = textHeader;
        this.options = options;
        // this.optionLabels = new ArrayList<>();
        this.textPrompt = textPrompt;
        // this.bottomContent = bottomContent;
        this.hasBackOption = hasBackOption;
        this.hasExitOption = hasExitOption;

        // for (MenuItem option : options) {
        //     this.optionLabels.add(option.getItemLabel());
        // }

        // Append "Back" and/or "Exit" to the menuOptions ArrayList
        if (hasBackOption) {
            this.options.add(BACK);
        }
        if (hasExitOption) {
            this.options.add(EXIT);
        }

        this.numOfOptions = this.options.size();
    }

    public void drawOptions() {
        for (int i = firstItemIdx; i < numOfOptions; i++) {
            if (i == currentSelection) {
                // Highlight currentSelection item
                terminal.writer().println("\u001b[7m> " + this.options.get(i).getItemLabel() + "\u001b[0m");
            } else {
                terminal.writer().println("  " + this.options.get(i).getItemLabel());
            }
        }
    }

    public void printAdditionalInfo() {
        this.options.get(currentSelection).printInfo();
    }

    public void printDivider(String character) {
        // System.out.println();
        for (int i = 0; i < MENU_WIDTH; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    public void moveCursorUp() {
        currentSelection = (currentSelection - 1 + this.numOfOptions) % this.numOfOptions;
    }

    public void moveCursorDown() {
        currentSelection = (currentSelection + 1) % this.numOfOptions;
    }

    public void onLeft() {
    }; // default do nothing

    public void onRight() {
    }; // default do nothing

    /**
     * Draws a menu that's navigable with arrow keys!
     * Returns JLineMenu.BACK_OPTION (an int) if "Back" is selected.
     * Selection of "Exit" is handled by another static method in this class:
     * confirmExit().
     * When an options that's neither "Back" nor "Exit", is selected, returns the
     * index of selected option.
     */
    public int drawMenu() {
        return drawMenu("");
    }

    public int drawMenu(String msg) {
        // int selected = 0;
        // boolean running = true;

        // Hide cursor
        System.out.println(HIDE_CUR);

        // Menu display loop
        while (running) {
            // Clear screen
            clearScreen();

            // Print header
            printHeader(textHeader, LEFT_RIGHT_PADDING);

            // print message after header
            if (msg.length() > 0)
                System.out.println(msg + "\n");

            // Display menu with highlighting
            drawOptions();

            // Display text prompt
            System.out.println("\n" + textPrompt);

            // divider (==========)
            System.out.println();
            printDivider("=");

            // print info/description related to the currently selected item of the menu
            printAdditionalInfo();

            // // Print bottom content if available
            // if (this.bottomContent != null) {
            // System.out.println("==========================");
            // this.bottomContent.display();
            // }

            // Read key pressed
            try {
                int c = terminal.reader().read();
                switch (c) {
                    case 27 -> { // 1st byte of an arrow keypress: ESC
                        terminal.reader().read(); // read 2nd byte of arrow keypress: 91 ([) or 79
                        c = terminal.reader().read();
                        switch (c) {
                            case 65 ->
                                moveCursorUp();
                            case 66 ->
                                moveCursorDown();
                            case 67 ->
                                onRight();
                            case 68 ->
                                onLeft();
                        }
                    }
                    case 13 -> { // Enter key pressed
                        clearScreen();

                        // "Back" or "Exit" currentSelection?
                        if (this.hasBackOption && this.hasExitOption) {
                            // Exit
                            if (currentSelection == this.numOfOptions - 1) {
                                confirmExit();
                                continue; // If user returns here, it means they chose to cancel exiting the program.
                            }
                            // Back
                            else if (currentSelection == this.numOfOptions - 2) {
                                System.out.println(SHOW_CUR); // Show cursor
                                return BACK_OPTION_INT;
                            }
                        } else if (this.hasBackOption && currentSelection == this.numOfOptions - 1) { // Back
                            System.out.println(SHOW_CUR); // Show cursor
                            return BACK_OPTION_INT;
                        } else if (this.hasExitOption && currentSelection == this.numOfOptions - 1) { // Exit
                            confirmExit();
                            continue; // If user returns here, it means they chose to cancel exiting the program.
                        }

                        // An option other than "Back" or "Exit" is currentSelection, so return the
                        // index of currentSelection option.
                        System.out.println(SHOW_CUR); // Show cursor
                        return currentSelection;
                    }
                    default -> {
                        // Clear input buffer
                        while (terminal.reader().ready()) {
                            terminal.reader().read();
                        }
                    }

                }
            } catch (IOException ex) { // Catch potential IOException thrown by Terminal.reader().read()
                // Logger.getLogger(JLineMenu.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("IOException caught: " + ex.getMessage());
                System.exit(1);
            }

        }
        return -3; // if the program reaches here, something has gone terribly wrong
    }

    // Waits for user to press any key before continuing
    public static void waitMsg() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    /*
     * Clear screen.
     * Only works in a terminal (e.g. PowerShell, CMD).
     * Does NOT work in Netbeans' Output.
     */
    public static void clearScreen() {
        terminal.writer().print("\u001b[2J\u001b[H");
        terminal.writer().flush();
    }

    public static void printHeader(String header, int leftRightPadding) {
        int headerLength = header.length();

        for (int i = 0; i < (2 * leftRightPadding + headerLength); i++) {
            System.out.print("=");
        }
        System.out.println();

        for (int i = 0; i < (leftRightPadding); i++) {
            System.out.print(" ");
        }
        System.out.println(header);

        for (int i = 0; i < (2 * leftRightPadding + headerLength); i++) {
            System.out.print("=");
        }
        System.out.print("\n\n");
    }

    public static void errorMsg(String msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void confirmExit() {
        while (true) {
            clearScreen();
            System.out.print("Are you sure you want to exit this program? (y)es or (n)o: ");
            String input = scanner.nextLine();
            if (input.equals("y") || input.equals("Y")) {
                System.out.println("Exiting the program...");
                System.exit(0); // exit normally
            } else if (input.equals("n") || input.equals("N")) {
                return; // Do not exit and return to where confirmExit() was called
            }
        }
    }

    // Getters and setters
    public void setOptions(List<MenuItem> options) {
        this.options = options;
    }

    public int getCurrentSelection() {
        return this.currentSelection;
    }

    public void setCurrentSelection(int currSel) {
        this.currentSelection = currSel;
    }

    public int getNumOfOptions() {
        return this.numOfOptions;
    }

    public void setNumOfOptions(int numOfOptions) {
        this.numOfOptions = numOfOptions;
    }

    public static void sound() {

        try {
            Runtime.getRuntime().exec("powershell -c [console]::beep(1000,500)");// HZ& SECOND

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
