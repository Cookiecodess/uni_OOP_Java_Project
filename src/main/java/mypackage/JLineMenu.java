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
// To run, type:
//   mvn exec:java -D"exec.mainClass"="mypackage.JLineMenu" -D"exec.classpathScope"=runtime -D"exec.fork"=true
//     (Note: I don't completely understand how or why this command works yet)
//     (I also don't understand why this doesn't work:
//          java -cp target/classes mypackage.JLineMenu
//      it throws this error:
//          Error: Unable to initialize main class mypackage.JLineMenu
//          Caused by: java.lang.NoClassDefFoundError: org/jline/terminal/Terminal
//     )

public class JLineMenu {

    public static void main(String[] args) throws Exception {
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder()
                        .system(true)
                        .jna(true)
                        .jansi(false)  // Avoid Jansi conflict
                        .build();
        } catch (IOException e) {
            System.err.println("Failed to initialize terminal: " + e.getMessage());
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> options = new ArrayList<>();
        options.add("Login");        
        options.add("Register");
        options.add("About");

        while (true) {
            int selection = showMenu(terminal, options, false, true);
            if (selection == -1) {
                break;
            }
            System.out.println("You selected: " + selection);
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        }
        
        System.out.println("Finished execution");

        terminal.close(); // must close terminal before exiting the program! to prevent resource leak/lingering threads
    }
    
    // If returns -1, it means to exit
    // If returns -2, it means "Back"
    // Otherwise returns the index of the selected option in options
    private static int showMenu(Terminal terminal, ArrayList<String> options, boolean hasBackOption, boolean hasExitOption) throws IOException {
        
        // Make a copy of the options ArrayList, so we don't modify the original ArrayList 
        ArrayList<String> menuOptions = (ArrayList<String>) options.clone();
        
        // Append "Back" and/or "Exit" to the menuOptions ArrayList
        if (hasBackOption) {
            menuOptions.add("Back");
        }
        
        if (hasExitOption) {
            menuOptions.add("Exit");
        }
        
        int numOfOptions = menuOptions.size();
        int selected = 0;
        boolean running = true;

        // Menu display loop
        while (running) {
            // Clear screen
            terminal.writer().print("\u001b[2J\u001b[H");
            terminal.writer().println("My Application Menu\n");

            // Display menu with highlighting
            for (int i = 0; i < numOfOptions; i++) {
                if (i == selected) {
                    // Highlight selected item
                    terminal.writer().println("\u001b[7m> " + menuOptions.get(i) + "\u001b[0m");
                } else {
                    terminal.writer().println("  " + menuOptions.get(i));
                }
            }

            terminal.writer().println("\nUse arrow keys to navigate, Enter to select");
            terminal.writer().flush();

            // Read key pressed
            int c = terminal.reader().read();
            switch (c) {
                case 65 -> // Up arrow
                    selected = (selected - 1 + numOfOptions) % numOfOptions;
                case 66 -> // Down arrow
                    selected = (selected + 1) % numOfOptions;
                case 13 -> {
                    // Enter
                    if (hasBackOption && hasExitOption) {
                        // Exit
                        if (selected == numOfOptions) {
                            clearScreen(terminal);
                            return -1;
                        }
                        // Back
                        else if (selected == numOfOptions - 1) {
                            clearScreen(terminal);
                            return -2;
                        }
                    } 
                    else if (hasBackOption && selected == numOfOptions) {
                        clearScreen(terminal);
                        // Back
                        return -2;
                    }
                    else if (hasExitOption && selected == numOfOptions) {
                        clearScreen(terminal);
                        // Exit
                        return -1;
                    }
                    
                    clearScreen(terminal);
                    return selected;
                }
            }
        }
        return -3; // if the program reaches here, something has gone terribly wrong
    }
    
    /*
    Clear screen.
    Only works in a terminal (e.g. PowerShell, CMD). 
    Does NOT work in Netbeans' Output.
    */
    public static void clearScreen(Terminal terminal) {  
        terminal.writer().print("\u001b[2J\u001b[H");
        terminal.writer().flush();  
    }  
}

//package mypackage;

//import org.jline.reader.LineReader;
//import org.jline.reader.LineReaderBuilder;

//import org.jline.terminal.Terminal;
//import org.jline.terminal.TerminalBuilder;
//import org.jline.utils.NonBlockingReader;
//
//public class JLineMenu {
//    public static void main(String[] args) throws Exception {
//        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
////        System.out.println("hello from JLineMenu.java");
////        Terminal terminal = TerminalBuilder.builder().system(true).build();
//        Terminal terminal = TerminalBuilder.builder()
//            .name("ansi")
//            .system(true)
//            .jansi(true)
//            .build();
////        NonBlockingReader reader = terminal.reader();
////
////        System.out.println("Press arrow keys to navigate. Press 'q' to quit.");
////
////        while (true) {
////            int key = reader.read(); // Reads a single character instantly
////            
////            if (key == 'q') break;  // Quit on 'q'
////
////            switch (key) {
////                case 27: // Escape sequence (Arrow keys start with this)
////                    int next = reader.read();
////                    if (next == 91) { // '[' key (part of arrow key sequence)
////                        int arrowKey = reader.read();
////                        switch (arrowKey) {
////                            case 65 -> System.out.println("Up Arrow");
////                            case 66 -> System.out.println("Down Arrow");
////                            case 67 -> System.out.println("Right Arrow");
////                            case 68 -> System.out.println("Left Arrow");
////                        }
////                    }
////                    break;
////                default:
////                    System.out.println("You pressed: " + (char) key);
////            }
////        }
////        reader.close();
//    }
//
////    private static void printMenu() {
////        System.out.println("\033[H\033[2J"); // Clear screen (ANSI)
////        System.out.flush();
////        System.out.println("Use Arrow Keys to move, Enter to select:");
////        for (int i = 0; i < options.length; i++) {
////            if (i == selectedIndex) {
////                System.out.println(" > " + options[i]); // Highlight selected option
////            } else {
////                System.out.println("   " + options[i]);
////            }
////        }
////    }
////
////    private static void waitForUser(LineReader reader) {
////        System.out.println("Press any key to continue...");
////        reader.readCharacter();
////    }
//}
