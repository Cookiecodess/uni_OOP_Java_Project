/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

// NOTE:
// To compile and run, first type this in your terminal
// to change your directory to the project folder
// (replace "<path>" with your path to OOP_Java_Project):
//   cd "C:\<path>\OOP_Java_Project"

// Since our project uses a dependency called JLine (for detecting arrow key presses), 
// we must use Maven to compile, as it automatically handles dependencies.
// You'll need to install Maven on your computer, then add it to your PATH.
//
// Then, to compile, type this in your terminal (cannot use the javac command, must use mvn):
//   mvn compile
//
// To run TestMain.java, type:
//   mvn exec:java -D"exec.mainClass"="mypackage.TestMain" -D"exec.classpathScope"=runtime -D"exec.fork"=true
//     (Note: I don't completely understand how or why this command works yet)
//     (I also don't understand why this doesn't work:
//          java -cp target/classes mypackage.JLineMenu
//      it throws this error:
//          Error: Unable to initialize main class mypackage.JLineMenu
//          Caused by: java.lang.NoClassDefFoundError: org/jline/terminal/Terminal
//     )

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class TestMain {
    static Scanner scanner = new Scanner(System.in);
    static JLineMenu mainMenu;
    static JLineMenu menu1;
    static JLineMenu menu2;
    static JLineMenu menu3;
    static JLineMenu menu1_1;
    static JLineMenu menu1_2;
    static JLineMenu menu1_3;
    
    public static void main(String[] args) {
        initAllMenus();
        // start program
        while (true) {
            int selection = mainMenu.drawMenu();
            switch (selection) {
                case 0 -> {
                    menu1();
                    continue;
                }
                case 1 -> {
                    menu2();
                    continue;
                }
                case 2 -> {
                    menu3();
                    continue;
                }
            }
        }
    }
    
    // IMPORTANT NOTE ON menu FUNCTIONS
    // Every menu function should have a while(true) loop wrapping all code,
    // so that user comes back to this menu when they select "Back" in a submenu.
    // Without the while(true) loop, the user may return all the way back to the main menu.
    public static void menu1() {
        while (true) {
            int selection = menu1.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            switch (selection) {
                case 0 -> { 
                    menu1_1();
                    continue;
                }
                case 1 -> {
                    menu1_2();
                    continue;
                }
                case 2 -> {
                    menu1_3();
                    continue;
                }
            }            
        }
    }
    
    
    public static void menu1_1() {
        while (true) {
            int selection = menu1_1.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            System.out.println("You selected: " + selection);
            JLineMenu.waitMsg();          
        }
    }
    
    public static void menu1_2() {
        while (true) {
            int selection = menu1_2.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            System.out.println("You selected: " + selection);
            JLineMenu.waitMsg(); 
        }
    }
    
    public static void menu1_3() {
        while (true) {
            int selection = menu1_3.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            System.out.println("You selected: " + selection);
            JLineMenu.waitMsg(); 
            
        }
    }
    
    
    public static void menu2() {
        while (true) {
            int selection = menu2.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            System.out.println("You selected: " + selection);
            JLineMenu.waitMsg(); 
            
        }
    }
    
    public static void menu3() {
        while (true) {
            int selection = menu3.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            System.out.println("You selected: " + selection);
            JLineMenu.waitMsg(); 
            
        }
    }
    
    public static void initAllMenus() {
        // initialize menus
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");        
        options.add("Option 2");
        options.add("Option 3");                
        mainMenu = new JLineMenu("Main menu", options, "Select an action to continue.", false, true);
        
        options.clear();
        options.add("Option 1-1");        
        options.add("Option 1-2");
        options.add("Option 1-3"); 
        menu1 = new JLineMenu("1", options, "Select an action to continue.", true, true);
        
        options.clear();
        options.add("Option 2-1");        
        options.add("Option 2-2");
        options.add("Option 2-3"); 
        menu2 = new JLineMenu("2", options, "Select an action to continue.", true, true);
        
        options.clear();
        options.add("Option 3-1");        
        options.add("Option 3-2");
        options.add("Option 3-3"); 
        menu3 = new JLineMenu("3", options, "Select an action to continue.", true, true);
        
        options.clear();
        options.add("Option 1-1-1");        
        options.add("Option 1-1-2");
        options.add("Option 1-1-3"); 
        menu1_1 = new JLineMenu("1-1", options, "Select an action to continue.", true, true);
        
        options.clear();
        options.add("Option 1-2-1");        
        options.add("Option 1-2-2");
        options.add("Option 1-2-3"); 
        menu1_2 = new JLineMenu("1-2", options, "Select an action to continue.", true, true);
        
        options.clear();
        options.add("Option 1-3-1");        
        options.add("Option 1-3-2");
        options.add("Option 1-3-3"); 
        menu1_3 = new JLineMenu("1-3", options, "Select an action to continue.", true, true);

    }
}
