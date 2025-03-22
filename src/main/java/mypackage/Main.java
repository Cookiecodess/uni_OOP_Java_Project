/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mypackage;

import java.util.Scanner;

// NOTE:
// clearScreen() won't work in Netbeans's Output.
// You have to compile and run this file in a TERMINAL
// (e.g. PowerShell, CMD) to see the effect.
//
// To compile and run, first type this in your terminal
// to change your directory to the project folder
// (replace "<path>" with your path to OOP_Java_Project):
//   cd "C:\<path>\OOP_Java_Project"
//
// To compile, type:
//   javac -d target/classes -cp target/classes  src/main/java/mypackage/*.java
//
// To run, type:
//   java -cp target/classes mypackage.Main

/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String[] options = new String[]{"Option 1", "Option 2", "Option 3"};
        String header = "Main Menu";
        
        int selectionMain = generateMenu(header, options, scanner);
        System.out.println("You selected "+selectionMain);

        int selectionSub = 0;
        switch (selectionMain) {
            case 1:
                selectionSub = generateMenu("Menu for option 1", options, scanner);
                break;
            case 2:
                selectionSub = generateMenu("Menu for option 2", options, scanner);
                break;
            case 3:
                selectionSub = generateMenu("Menu for option 3", options, scanner);
                break;
        }
        
        System.out.println("You selected: " + selectionMain + " > " + selectionSub );
        System.out.println("Exiting...");
    }
    
    /*
    Generate a menu with a header and a list of options.
    Returns an int: the list number of the selected option.
    */
    public static int generateMenu(String header, String[] options, Scanner scanner) {
        final int LEFT_RIGHT_PADDING = 10;
        
        boolean invalid = false;
        while (true) {
            clearScreen();
            
            printHeader(header, LEFT_RIGHT_PADDING);
            
            for (int i=0; i<options.length; i++) {
                System.out.print((i+1) + ". ");
                System.out.println(options[i]);
            }
            
            if (invalid) {
                System.out.println();
                System.out.println("Invalid option. Please try again.");
                System.out.println();
            }
            
            System.out.print("Please select an option [1.." + options.length + "] > ");
        
            int selection = Integer.parseInt(scanner.nextLine());
            
            if (selection >= 1 && selection <= options.length) {
                return selection;
            }
            
            invalid = true;
        }
    }
    
    /*
    Clear screen.
    Only works in a terminal (e.g. PowerShell, CMD). 
    Does NOT work in Netbeans' Output.
    */
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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
}
