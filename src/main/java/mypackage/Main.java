/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package mypackage;

import java.util.Scanner;
import java.util.ArrayList;
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
// To run Main.java, type:
//   mvn exec:java -D"exec.mainClass"="mypackage.Main" -D"exec.classpathScope"=runtime -D"exec.fork"=true
//     (Note: I don't completely understand how or why this command works yet)
//     (I also don't understand why this doesn't work:
//          java -cp target/classes mypackage.JLineMenu
//      it throws this error:
//          Error: Unable to initialize main class mypackage.JLineMenu
//          Caused by: java.lang.NoClassDefFoundError: org/jline/terminal/Terminal
//     )
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static JLineMenu mainMenu;
    static JLineMenu customer;
    static JLineMenu admin;
    static JLineMenu menu3;
    static JLineMenu menu1_1;
    static JLineMenu bankSelection;
    static JLineMenu payment;

    public static void main(String[] args) {
        initAllMenus();
        // start program
        while (true) {
            int selection = mainMenu.drawMenu();
            switch (selection) {
                case 0 -> {
                    customer();
                    continue;
                }
                case 1 -> {
                    admin();
                    continue;
                }
                case 2 -> {
                    payment();
                    continue;
                }
                default -> {
                    continue;
                }
            }
        }

    }

    public static void initAllMenus() {
        // initialize menus
        ArrayList<String> options = new ArrayList<>();
        options.add("Customer");
        options.add("Admin");
        options.add("Payment");
        mainMenu = new JLineMenu("Main menu", options, "Select an action to continue.", false, true);

        options.clear();
        options.add("Option 1-1");
        options.add("Option 1-2");
        options.add("Option 1-3");
        customer = new JLineMenu("1", options, "Select an action to continue.", true, false);

        options.clear();
        options.add("Option 2-1");
        options.add("Option 2-2");
        options.add("Option 2-3");
        admin = new JLineMenu("2", options, "Select an action to continue.", true, false);

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
        bankSelection = new JLineMenu("1-2", options, "Select an action to continue.", true, true);

        options.clear();
        options.add("Online Banking");
        options.add("Touch and Go");
        options.add("Card Payment");
        payment = new JLineMenu("1-3", options, "Select an action to continue.", true, true);

    }
    

    public static void customer() {

    }

    public static void admin() {

    }

    public static void payment() {
        while (true) {
            int selection = payment.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }

            switch (selection) {
                case 0 -> {
                    onlineBanking();
                    continue;
                }
                case 1 -> {
                    cardPayment();
                    continue;
                }
                case 2 -> {
                    touchNGo();
                    continue;
                }
                default -> {
                    continue;
                }
            }
            
        }
    }

    public static void onlineBanking() {

    }

    public static void cardPayment() {

    }

    public static void touchNGo() {

    }

}
