/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package mypackage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
    static JLineMenu customerDb;
    static JLineMenu adminDb;
    static JLineMenu menu3;
    static JLineMenu menu1_1;
    static JLineMenu bankSelection;
    static JLineMenu payment;
    static Customer currentCust = null;
    static Admin currentAdmin  = null;


    public static void main(String[] args) {
        initAllMenus();
        // start program
        while (true) {
            int selection = mainMenu.drawMenu();
            switch (selection) {
                case 0 -> {
                    if(currentCust==null){
                        customer();
                    }
                    customerDashboard();
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
        options.add("Log In");
        options.add("Sign Up");
        customer = new JLineMenu("Customer", options, "Select an action to continue.", true, false);

        options.clear();
        options.add("Log In");
        admin = new JLineMenu("Admin", options, "Select an action to continue.", true, false);
        
        
        options.clear();
        options.add("View Products");
        options.add("View Orders");
        options.add("Change Password");
        options.add("Log Out");
        customerDb = new JLineMenu("Customer Dashboard", options, "Select an action to continue.", false, false);
        
        
        options.clear();
        options.add("Add New Products");
        options.add("View Pending Orders");
        options.add("Change Password");
        options.add("Log Out");
        adminDb = new JLineMenu("Admin Dashboard", options, "Select an action to continue.", false, false);
        
        
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
        
            
            int Selection = customer.drawMenu();
            switch(Selection) {
                
                case 0 -> {
                    login("customer");
                    break;
                }
                
                case 1 -> {
                    //Sign up is selected
                    break;
                }                
                     
            }
            
         
        
        
    }

    public static void admin() {
        admin.drawMenu();
    }
    
    public static void customerDashboard(){
        int selection = customerDb.drawMenu();
        
        switch(selection){
            case 0 -> {
                break;
            }
            
            case 1 -> {
                break;
            }
            
            case 2 -> {
                break;
            }
            
            case 3 -> {
                currentCust=null;
                break;
            }
            
            

        }
    }
    
    public static void login(String type){
        JLineMenu.clearScreen();
        while(true){       
            System.out.print("Enter your UserName: ");
            String username = scanner.next();
            scanner.nextLine(); // for cleaning buffer purposes

            System.out.print("Enter your Password: ");
            String password = scanner.next();
            scanner.nextLine(); // for cleaning buffer purposes

            //validating
            if(type.equals("customer")){
                //customer login
                
                if((currentCust = AuthServices.custlogin(username, password))!=null){
                    currentAdmin = null;
                    break;
                }
                else {
                    JLineMenu.clearScreen();
                    System.out.println("Invalid Credentials!");
                }
            }
            else{
                //admin login
                if((currentAdmin = AuthServices.login(username, password))!=null){
                    currentCust = null;
                    break;
                }
                else {
                    JLineMenu.clearScreen();
                    System.out.println("Invalid Credentials!");
                }
            }
            
        }
        
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
