/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package mypackage;


// NOTE:
// To compile and run, first type this in your terminal
// to change your directory to the project folder
// (replace "<path>" with your path to OOP_Java_Project):
//   cd "C:\<path>\OOP_Java_Project"
//songlynn punya : cd "C:\Users\songl\source\repos\OOP_Java_Project"
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
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import static mypackage.JLineMenu.terminal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    static JLineMenu changeDetails;
    static JLineMenu quitOrContinue;
    static JLineMenu menu1_1;
    static JLineMenu bankSelection;
    static JLineMenu payment;
    static JLineMenu reportSelection;
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
        options.add("Change Account Details");
        options.add("Log Out");
        customerDb = new JLineMenu("Customer Dashboard", options, "Select an action to continue.", true, false);
        
        
        options.clear();
        options.add("Add New Products");
        options.add("View Pending Orders");
        options.add("Change Account Details");
        options.add("Add Other Admin");
        options.add("Suspend Customer");
        options.add("Un-Suspend Customer");
         options.add("View Report");
        options.add("Log Out");
        adminDb = new JLineMenu("Admin Dashboard", options, "Select an action to continue.", true, false);
        
        
        
        options.clear();
        options.add("Change Name");
        options.add("Change Address");
        options.add("Change Password");
        options.add("Change Phone Number");
        options.add("Change Email");
        changeDetails = new JLineMenu("Update Particulars", options, "Select an action to continue.", true, false);
        
        
        
        options.clear();
        options.add("Daily Report");
        options.add("Monthly Report");
        options.add("Yearly Report");
        options.add("Customize Report");
        reportSelection = new JLineMenu("Report", options, "Select a report type.", true, true);

        options.clear();
        options.add("Try Again");
        quitOrContinue = new JLineMenu("Continue?", options, "Select an action to continue.", true, true);

        options.clear();
        options.add("Hong Leong Bank");
        options.add("Alliance Bank");
        options.add("Public Bank");
        options.add("CIMB Bank");
        options.add("Maybank");
        
        bankSelection = new JLineMenu("Bank", options, "Select an action to continue.", true, true);

        options.clear();
        options.add("Online Banking");
        options.add("Touch and Go");
        options.add("Card Payment");
        payment = new JLineMenu("PaymentMethod", options, "Select an action to continue.", true, true);

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
        while(true){
            int selection = customerDb.drawMenu();
            
            if(selection == -1) break;
            if(selection == 3){
                currentCust=null;
                break;
            }
            
            switch(selection){
                case 0 -> {
                    break;
                }

                case 1 -> {
                    break;
                }

                case 2 -> {
                    updateInfo("customer");
                    break;
                }

            }
        }
        
    }
    
    public static void adminDashboard(){
        while(true){
            int selection = adminDb.drawMenu();
            if(selection == -1) break;
            if(selection == 6){
                currentAdmin=null;
                break;
            }
            
            
            switch(selection){
                case 0 -> {
                    break;
                }

                case 1 -> {
                    break;
                }

                case 2 -> {
                    updateInfo("admin");
                    break;
                }

                case 3 -> {
                    //register admin
                    register("admin");
                    break;
                }

                case 4 -> {
                    //suspend customer
                    suspend(true);
                    break;
                }

                case 5 -> {
                    //unsuspend customer
                    suspend(false);
                    break;
                }
                                case 6 -> {
                    //report();
                    break;
                }

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
        
        if(type.equals("admin") && !currentAdmin.isMain()){
            System.out.println("Access Denied!");
            System.out.print("Press Enter To Go Back....");
            scanner.nextLine();
            return;
        }
        
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
    
    public static void suspend(boolean suspended){
        JLineMenu.clearScreen();
        String[] details;
        int UID;
        String action = "suspend";
        if(!suspended) action = "unsuspend";
        
        while(true){
                System.out.print("Enter a UID to " + action + " (-999 to exit): ");
                try{
                    UID = scanner.nextInt();
                    scanner.nextLine();
                    if(UID == -999) break;
                }
                catch (Exception e){
                    JLineMenu.clearScreen();
                    scanner.nextLine();
                    System.out.println("Invalid UID! \n");
                    continue;
                }
                

                //checking if the uid exists
                if((details = AuthServices.getUserDetails(UID)) != null && (!details[9].equals("admin") && !details[9].equals("main"))){
                    JLineMenu.clearScreen();
                    System.out.println("-----------USER INFORMATION-----------");
                    System.out.println("Username: " + details[0]);
                    System.out.println("Name: " + details[3]);
                    System.out.println("UID: " + details[2]);
                    System.out.println("Role: " + details[9]);
                    System.out.println("Status: " + details[10]);
                    System.out.print("Are you sure you want to " + action + "? Y(es) N(o): ");
                    char selection = scanner.next().charAt(0);

                    scanner.nextLine();

                    switch(selection){
                        case 'y':
                        case 'Y':
                            AuthServices.suspend(UID, suspended);
                            JLineMenu.clearScreen();
                            System.out.println("Status Updated!\n");
                            break;
                        default:
                            JLineMenu.clearScreen();
                            break;
                    }

                }
                else{
                    JLineMenu.clearScreen();
                    System.out.println("User Does not Exist!\n");
                }
        }
        
    }
    
    public static void updateInfo(String type){
        User currentUser = currentAdmin;
        if(type.equals("customer")) currentUser = currentCust;
        
        JLineMenu.clearScreen();
        int selection = changeDetails.drawMenu();
        switch(selection){
            case 0 -> {
                //change name
                changeName(currentUser);
                break;
            }
            case 1 -> {
                //change address
                changeAddress(currentUser);
                break;
            }
            case 2 -> {
                //change password
                changePass(currentUser);
                break;
            }
            case 3 -> {
                //change phone
                changePhone(currentUser);
                break;
            }
            
            case 4 -> {
                //change emial
                changeEmail(currentUser);
                break;
            }
            
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
    }
    
    public static void changeName(User x){
        JLineMenu.clearScreen();
        String input;
        
        while(true){
            System.out.print("Enter Your New Name (999 to go back): ");
            input = scanner.nextLine();
            
            if(input.equals("999")){
                return;
            }
            
            if(!input.isEmpty()) break;
            JLineMenu.clearScreen();
            System.out.println("Name Cannot Be Empty!\n");
            
        }
        
        
        AuthServices.changeName(x,input);
    }
    
    public static void changeAddress(User x){
        JLineMenu.clearScreen();
        String input;
        
        while(true){
            System.out.print("Enter Your New Address (999 to go back): ");
            input = scanner.nextLine();
            
            if(input.equals("999")){
                return;
            }
            
            if(!input.isEmpty()) break;
            JLineMenu.clearScreen();
            System.out.println("Address Cannot Be Empty!\n");
            
        }
        
        
        AuthServices.changeAddress(x,input);
    }
    
    public static void changePhone(User x){
        JLineMenu.clearScreen();
        String input;
        
        while(true){
            System.out.print("Enter Your New Phone (999 to go back): +60");
            input = scanner.next();
            scanner.nextLine();
            
            if(input.equals("999")){
                return;
            }
            
            if(input.matches("\\d{9,10}") && input.charAt(0) == '1'){
                input = "60" + input;
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid Phone format!\n");
            
        }
        
        
        AuthServices.changePhone(x,input);
    }
    
    public static void changeEmail(User x){
        JLineMenu.clearScreen();
        String input;
        
        while(true){
            System.out.print("Enter Your New Email (999 to go back): ");
            input = scanner.nextLine();
            
            if(input.equals("999")){
                return;
            }
            
            if(input.contains("@")) break;
            JLineMenu.clearScreen();
            System.out.println("Invalid email format!\n");
            
        }
        
        
        AuthServices.changeEmail(x,input);
    }
    
    public static void payment() {
        Order a=new Order();

        boolean valid=true;
        while (valid) {
            int selection = payment.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }

            switch (selection) {
                case 0 -> {
                    valid=onlineBankingPaymentProcess(a);
                    continue;
                }
                case 1 -> {
                    valid=qrCodePayment(a);
                     
                    continue;
                }
                case 2 -> {
                   valid=cardPaymentProcess(a);
                    continue;
                }
                default -> {
                    continue;
                }
            }
            
        }
    }
    
    
    
      public static boolean onlineBankingPaymentProcess(Order a){
Payment paymentO;
    String bankName;
    while (true) {
            int selection = bankSelection.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return false;
            }

               switch (selection) {
                
                 case 0 -> bankName = "Hong Leong Bank";
            case 1 -> bankName = "Alliance Bank";
            case 3 -> bankName = "Public Bank";
            case 4 -> bankName = "CIMB Bank";
            case 5 -> bankName = "Maybank";
            default -> {
                continue;
            }
                
                
            }
               
        paymentO = new OnlineBankingPayment(10.00,a,bankName);
            return processPayment(paymentO,a);
    }

} 
    
    
     private static boolean processPayment(Payment paymentO,Order a) {
        boolean shouldExit = false;
        while (!shouldExit) {
            if (paymentO.validation()) {
                System.out.println(JLineMenu.GREEN + "Successful!" + JLineMenu.RESET);
                JLineMenu.waitMsg();
                JLineMenu.clearScreen();
                JLineMenu.printHeader("Receipt", 20);
                paymentO.generateReceipt(a);
                shouldExit = true;
            } else {
                System.out.println(JLineMenu.RED + paymentO.failMessage() + JLineMenu.RESET);
                JLineMenu.waitMsg();
                int continueOrNot = quitOrContinue.drawMenu();
                if (continueOrNot == JLineMenu.BACK_OPTION) {
                    return false;
                }
            }
        }
        return true;
    
}
    
    
    
 public static boolean qrCodePayment(Order a){
        Payment paymentO=new qrCodePayment(10.00,a);
               JLineMenu.printHeader("QR code Payment",45);
          paymentO.generateQR();      
         
        return processPayment(paymentO,a);
   
} 
 
 
 public static boolean cardPaymentProcess(Order a){

    Payment paymentO;
    String bankName;
    while (true) {
            int selection = bankSelection.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return false;
            }

               switch (selection) {
                
                 case 0 -> bankName = "Hong Leong Bank";
            case 1 -> bankName = "Alliance Bank";
            case 3 -> bankName = "Public Bank";
            case 4 -> bankName = "CIMB Bank";
            case 5 -> bankName = "Maybank";
            default -> {
                continue;
            }
                
                
            }
               
        paymentO = new CardPayment(10.00,a,bankName);
            return processPayment(paymentO,a);
    }
    
    
} 
    
 
// 
//  public static void reportPage(List<Order> orderList, List<Product> productList){
//   
//        DateChecker dateChecker = new DateChecker();
//      
//    while(true){
//        int selection = payment.drawMenu();
//            if (selection == JLineMenu.BACK_OPTION) {
//                return;
//            }
//         
//        LocalDate userDate;
//        switch (selection) {
//                case 0 -> {
//            // Daily Report
//             userDate = getUserDateInput("Please enter the date (YYYY-MM-DD):");
//             dateChecker.setDailyReport(userDate);
//        }  case 1 -> {
//            // Monthly Report
//           userDate = getUserDateInput("Please enter the month (YYYY-MM-DD):");
//           
//            dateChecker.setMonthlyReport(userDate);
//        } case 2 -> {
//            // Yearly Report
//           userDate = getUserDateInput("Please enter the year (YYYY):");
//            
//            dateChecker.setYearlyReport(userDate);
//        }  case 3 -> {
//            // Customize Report
//
//            LocalDate startDate = getUserDateInput("Please enter the START date (YYYY-MM-DD):");
//            LocalDate endDate = getUserDateInput("Please enter the END date (YYYY-MM-DD):");
//            dateChecker.setCustomizeReport(startDate, endDate);
//        }
//         default -> {
//                continue;
//            }
//        }
//        
//        // get the date domain
//        LocalDate startDate = dateChecker.getStartDate();
//        LocalDate endDate = dateChecker.getEndDate();
//
//        // 生成销售报告
//        Report report = new Report();
//        report.generateSalesReport(orderList, productList, startDate, endDate);
//
//                int continueOrNot = quitOrContinue.drawMenu();
//                if (continueOrNot == JLineMenu.BACK_OPTION) {
//                    return;
//                }
//        scanner.close();
//        }
// 
//  }
// public static LocalDate getUserDateInput(String prompt) {
//        LocalDate userDate = null;
//        boolean validInput = false;
//        Scanner scan = new Scanner(System.in);
//        // continue loop until valid format
//        while (!validInput) {
//            System.out.println(prompt);
//            String input = scan.next();
//
//            try {
//                userDate = LocalDate.parse(input); //get the date
//                validInput = true; // if no exception means is valid format
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid Format！Please try again");
//            }
//        }
//        
//        return userDate; // return valid date
//    }
}