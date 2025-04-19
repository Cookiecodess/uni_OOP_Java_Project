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
                    if(currentCust!=null){
                        customerDashboard();
                    }
                    continue;
                }
                case 1 -> {
                    if(currentAdmin==null){
                        admin();
                    }
                    if(currentAdmin!=null){
                        adminDashboard();
                    }
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
        customerDb = new JLineMenu("Customer Dashboard", options, "Select an action to continue.", true, false);
        
        
        options.clear();
        options.add("Add New Products");
        options.add("View Pending Orders");
        options.add("Change Password");
        options.add("Add Other Admin");
        options.add("Log Out");
        adminDb = new JLineMenu("Admin Dashboard", options, "Select an action to continue.", true, false);
        
        
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
                    register("customer");
                    break;
                }                
                     
            }
            
         
        
        
    }

    public static void admin() {
        int Selection = admin.drawMenu();
            switch(Selection) {
                
                case 0 -> {
                    login("admin");
                    break;
                }           
                     
            }
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
                changePass(currentCust);
                break;
            }
            
            case 3 -> {
                currentCust=null;
                break;
            }
            
            

        }
    }
    
    public static void adminDashboard(){
        int selection = adminDb.drawMenu();
        switch(selection){
            case 0 -> {
                break;
            }
            
            case 1 -> {
                break;
            }
            
            case 2 -> {
                changePass(currentAdmin);
                break;
            }
            
            case 3 -> {
                //register admin
                register("admin");
                break;
            }
            
            case 4 -> {
                currentAdmin=null;
                break;
            }
            
        }
        
    }
    
    public static void login(String type){
        JLineMenu.clearScreen();
        while(true){       
            System.out.print("Enter your UserName (999 to go back): ");
            String username = scanner.next();
            scanner.nextLine(); // for cleaning buffer purposes
            if(username.equals("999")){
                break;
            }
            
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
                    System.out.println();
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
                    System.out.println();
                }
            }
            
        }
        
    }
    
    public static void register(String type){
        JLineMenu.clearScreen();
        
        ArrayList<String> usernames = AuthServices.getUsernames();
        String username;
        String password;
        String name;
        String email;
        String phoneNumber;
        String address;
        String birthdate;
        String gender;
        
       // Username
        while (true) {
            System.out.print("Enter a username: ");
            username = scanner.next();
            scanner.nextLine(); // flush
            
            if (!username.isEmpty() && !usernames.contains(username)) break;
            
            JLineMenu.clearScreen();
            System.out.println("Username Taken!");
        }
        
         JLineMenu.clearScreen();
        
        // Password
        while (true) {
            System.out.println("Enter a username: "+username);
            
            System.out.print("Enter a password: ");
            password = scanner.next();
            scanner.nextLine();
            
            System.out.print("Re-Enter your password: ");
            String password2 = scanner.next();
            scanner.nextLine();
            
            if (password.equals(password2)) break;
            JLineMenu.clearScreen();
            System.out.println("Password did not match!\n");
            
        }
        
        
        JLineMenu.clearScreen();
        // Name
        while (true) {
            System.out.println("Enter a username: "+username);
            System.out.println("Enter a password: "+password);
            
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if (!name.isEmpty()) break;
            JLineMenu.clearScreen();
            System.out.println("Name cannot be empty!\n");
        }
        
        
        JLineMenu.clearScreen();
        // Email
        while (true) {
            System.out.println("Enter a username: "+username);
            System.out.println("Enter a password: "+password);
            System.out.println("Enter your name: "+name);
            
            System.out.print("Enter your email: ");
            email = scanner.next();
            scanner.nextLine();
            if (email.contains("@")) break;
            JLineMenu.clearScreen();
            System.out.println("Invalid email format!\n");
        }
        
        
        JLineMenu.clearScreen();
        // Phone number
        while (true) {
            System.out.println("Enter a username: "+username);
            System.out.println("Enter a password: "+password);
            System.out.println("Enter your name: "+name);
            System.out.println("Enter your email: "+email);
            
            System.out.print("Enter your phone number: +60");
            phoneNumber = scanner.next();
            scanner.nextLine();
            if (phoneNumber.matches("\\d{9,10}") && phoneNumber.charAt(0) == '1'){
                phoneNumber = "60"+phoneNumber;
                break; // accepts 9–11 digits
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid phone number!\n");
        }
        
        
        JLineMenu.clearScreen();
        // Address
        while (true) {
            System.out.println("Enter a username: "+username);
            System.out.println("Enter a password: "+password);
            System.out.println("Enter your name: "+name);
            System.out.println("Enter your email: "+email);
            System.out.println("Enter your phone number: +"+phoneNumber);
            
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
            if (!address.isEmpty()) break;
            JLineMenu.clearScreen();
            System.out.println("Address cannot be empty!\n");
            
        }
        
        JLineMenu.clearScreen();    
        // Birth day
        while (true) {
            System.out.println("Enter a username: "+username);
            System.out.println("Enter a password: "+password);
            System.out.println("Enter your name: "+name);
            System.out.println("Enter your email: "+email);
            System.out.println("Enter your phone number: +"+phoneNumber);
            System.out.println("Enter your address: "+address);
            
            System.out.print("Enter your birth day (dd-mm-yyyy): ");
            birthdate = scanner.next();
            scanner.nextLine();
            if (birthdate.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$")) break;
            JLineMenu.clearScreen();
            System.out.println("Invalid Date Format!\n");
            
        }
        
        JLineMenu.clearScreen();    
        // Gender
        while (true) {
            System.out.println("Enter a username: "+username);
            System.out.println("Enter a password: "+password);
            System.out.println("Enter your name: "+name);
            System.out.println("Enter your email: "+email);
            System.out.println("Enter your phone number: +"+phoneNumber);
            System.out.println("Enter your address: "+address);
            System.out.println("Enter your birth day (dd-mm-yyyy): "+birthdate);
            
            System.out.print("Enter your gender (Male/Female): ");
            gender = scanner.next();
            scanner.nextLine();
            if (gender.equals("Male") || gender.equals("Female")) break;
            JLineMenu.clearScreen();
            System.out.println("Please enter 'Male' or 'Female'!\n");
            
        }
        
        if(type.equals("customer")){
            AuthServices.register(username,password,name,email,phoneNumber,address,birthdate,gender);
        }
        else{
            AuthServices.registerAdmin(username,password,name,email,phoneNumber,address,birthdate,gender);
        }
           
    }
    
    public static void changePass(User x){
        JLineMenu.clearScreen();
        String input;
        
        while(true){
            System.out.print("Enter Your Current Password (999 to go back): ");
            input = scanner.next();
            scanner.nextLine();
            
            if(input.equals("999")){
                return;
            }
            
            if(input.equals(x.getPassword())) break;
            JLineMenu.clearScreen();
            System.out.println("Invalid Password!\n");
            
        }
        
        while(true){
            System.out.print("Enter Your New Password: ");
            input = scanner.next();
            scanner.nextLine();
            
            System.out.print("Re-Enter Your New Password: ");
            String input2 = scanner.next();
            scanner.nextLine();
            
            if(input.equals(input2)) break;
            JLineMenu.clearScreen();
            System.out.println("Password does not match!\n");
        }
        
        AuthServices.changePassword(x,input);
        currentCust= null;
        currentAdmin = null;
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
