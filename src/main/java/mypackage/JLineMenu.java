/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package mypackage;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mypackage.TestMain.scanner;

// NOTE:
// This interactive menu only works properly in a terminal
// like CMD or PowerShell. It does NOT work in Netbeans' Output.
// 
// Since this file depends on certain dependencies, 
// use Maven to compile, as it automatically handles dependencies.
// You'll need to install Maven on your computer, then add it to your PATH.
//
// Then, to compile, type this in your terminal (cannot use the javac command, must use mvn):
//   mvn compile
//
// To run JLineMenu.java, (BUT you may actually want to run Main.java instead...? If so, read the note at the top of Main.java)
// type:
//   mvn exec:java -D"exec.mainClass"="mypackage.JLineMenu" -D"exec.classpathScope"=runtime -D"exec.fork"=true
//     (Note: I don't completely understand how or why this command works yet)
//     (I also don't understand why this doesn't work:
//          java -cp target/classes mypackage.JLineMenu
//      it throws this error:
//          Error: Unable to initialize main class mypackage.JLineMenu
//          Caused by: java.lang.NoClassDefFoundError: org/jline/terminal/Terminal
//     )


public class JLineMenu {
//    static final String SAV_CUR = "\033[s"; // save cursor position
//    static final String RST_CUR = "\033[u"; // restore cursor position
//    static final String CLEAR_LINE_CUR_TIL_END = "\033[K"; // clear from cursor until end of line
    public static final String HIDE_CUR = "\033[?25l";
    public static final String SHOW_CUR = "\033[?25h";
    
    
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    

    //text color
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m"; 
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    //background color
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_MAGENTA = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    static Terminal terminal;
    private static Scanner scanner;
    


    
    // Global constants for all menus
    public static final int BACK_OPTION = -1;    
    public static final int LEFT_RIGHT_PADDING = 10; // the number of ='s at either side of the header
    public static final int MENU_WIDTH = 40; // max width of menu
    
    // A static block is executed ONCE when this class is loaded.
    static {
        // Initialize terminal
        try {
            terminal = TerminalBuilder.builder()
                        .system(true)
                        .jna(true)
                        .jansi(false)  // Avoid Jansi conflict
                        .build();
        } catch (IOException e) {
            System.err.println("\nFailed to initialize terminal: " + e.getMessage() + "\n");
            System.exit(1); // exits with error
        }
        
        // Initialize scanner
        scanner = new Scanner(System.in);
    }

    // For testing this class's functionality
    public static void main(String[] args) throws IOException {
        System.out.println("Hello from JLineMenu.main");
        terminal.close(); // this line may throw an IOException, that's why the main method needs a throws clause ("throws IOException")
    }
    
    // Instance variables
    private String textHeader;
    ArrayList<String> options;
    int numOfOptions; // want to access in subclass
    private String textPrompt;
    private boolean hasBackOption;    
    private boolean hasExitOption;
    
    int firstItemIdx = 0;
    int selected = 0; // want to access in subclass
    private boolean running = true;
    
    // Public constructor of a JLineMenu object
    public JLineMenu(String textHeader, ArrayList<String> options, String textPrompt, boolean hasBackOption, boolean hasExitOption) {

        this.textHeader = textHeader;
        this.options = (ArrayList<String>) options.clone(); // Make a copy of the options ArrayList, so we don't modify the original ArrayList 
        this.textPrompt = textPrompt;
        this.hasBackOption = hasBackOption;       
        this.hasExitOption = hasExitOption;
        
        // Append "Back" and/or "Exit" to the menuOptions ArrayList
        if (hasBackOption) {
            this.options.add("Back");
        }        
        if (hasExitOption) {
            this.options.add("Exit");
        }
        
        this.numOfOptions = this.options.size();
    }
    
    public void drawOptions() {
        for (int i = firstItemIdx; i < numOfOptions; i++) {
            if (i == selected) {
                // Highlight selected item
                terminal.writer().println("\u001b[7m> " + this.options.get(i) + "\u001b[0m");
            } else {
                terminal.writer().println("  " + this.options.get(i));
            }
        }
    }
    
    public void moveCursorUp() {
        selected = (selected - 1 + this.numOfOptions) % this.numOfOptions;
    }
    public void moveCursorDown() {
        selected = (selected + 1) % this.numOfOptions;
    }
    public void onLeft() {}; // default do nothing
    public void onRight() {}; // default do nothing
    
    /**
     * Draws a menu that's navigable with arrow keys!
     *     Returns JLineMenu.BACK_OPTION (an int) if "Back" is selected.
     *     Selecting "Exit" is handled by another static method in this class: confirmExit().
     *     When an options that's neither "Back" nor "Exit", is selected, returns the index of selected option.
     */
    public int drawMenu() {
//        int selected = 0;
//        boolean running = true;
        
        // Hide cursor
        System.out.println(HIDE_CUR);

        // Menu display loop
        while (running) {
            // Clear screen
            clearScreen();
            
            // Print header
            printHeader(textHeader, LEFT_RIGHT_PADDING);

            // Display menu with highlighting
            drawOptions();
            
            // Display text prompt
            System.out.println("\n" + textPrompt);

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
                        
                        // "Back" or "Exit" selected?
                        if (this.hasBackOption && this.hasExitOption) {
                            // Exit
                            if (selected == this.numOfOptions - 1) {
                                confirmExit();
                                continue; // If user returns here, it means they chose to cancel exiting the program.
                            }
                            // Back
                            else if (selected == this.numOfOptions - 2) {
                                System.out.println(SHOW_CUR); // Show cursor
                                return BACK_OPTION;
                            }
                        } 
                        else if (this.hasBackOption && selected == this.numOfOptions - 1) { // Back
                            System.out.println(SHOW_CUR); // Show cursor
                            return BACK_OPTION;
                        }
                        else if (this.hasExitOption && selected == this.numOfOptions - 1) { // Exit
                            confirmExit();
                            continue; // If user returns here, it means they chose to cancel exiting the program.
                        }
                        
                        // An option other than "Back" or "Exit" is selected, so return the index of selected option.
                        System.out.println(SHOW_CUR); // Show cursor
                        return selected;
                    }
                    default -> {
                        // Clear input buffer
                        while (terminal.reader().ready()) {
                            terminal.reader().read();
                        }
                    }
                        
                }
            } catch (IOException ex) { // Catch potential IOException thrown by Terminal.reader().read()
//                Logger.getLogger(JLineMenu.class.getName()).log(Level.SEVERE, null, ex);
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
    Clear screen.
    Only works in a terminal (e.g. PowerShell, CMD). 
    Does NOT work in Netbeans' Output.
    */
    public static void clearScreen() {  
        terminal.writer().print("\u001b[2J\u001b[H");
        terminal.writer().flush();  
    }  
    
    public static void printHeader(String header, int leftRightPadding) {
        int headerLength = header.length();
        
        for (int i=0; i<(2*leftRightPadding + headerLength); i++) {
            System.out.print("=");
        }        
        System.out.println();
        
        for (int i=0; i<(leftRightPadding); i++) {
            System.out.print(" ");
        }        
        System.out.println(header);
        
        for (int i=0; i<(2*leftRightPadding + headerLength); i++) {
            System.out.print("=");
        }        
        System.out.print("\n\n");
    }
    public static void errorMsg(String msg){
    System.out.println(RED+msg+RESET);
    }
    
    
    public static void confirmExit() {
        while (true) {
            clearScreen();
            System.out.print("Are you sure you want to exit this program? (y)es or (n)o: ");
            String input = scanner.nextLine();
            if ( input.equals("y") || input.equals("Y") ) {
                System.out.println("Exiting the program...");
                System.exit(0); // exit normally
            } else if ( input.equals("n") || input.equals("N") ) {
                return; // Do not exit and return to where confirmExit() was called 
            }            
        }        
    }
    
    // Getters and setters
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
    public static void sound(){
    
    try{
             Runtime.getRuntime().exec("powershell -c [console]::beep(1000,500)");//HZ& SECOND
             
             }catch(IOException e){
             e.printStackTrace();
             }
    
    }
}
