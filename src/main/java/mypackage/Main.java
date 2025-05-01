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
//jon: cd /Users/Acer/OOP_Java_Project
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
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import static mypackage.JLineMenu.terminal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Map;

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
    static JLineMenu productMainMenu;
    static OOMenu productCategoryMenu;
//    static JLineMenu productListMenu;
    static JLineMenu adminDb;
    static JLineMenu changeDetails;
    static JLineMenu quitOrContinue;
    static JLineMenu menu1_1;
    static JLineMenu bankSelection;
    static JLineMenu payment;
    static JLineMenu reportSelection;
    static Customer currentCust = null;
    static Admin currentAdmin = null;
    //Here, these 2 is used to store Payment Method with/without Bank Name. 
    private static String lastPaymentMethod;
    private static String lastBankName;
    
    static ProductInventory inventory;
    static {
        // initialize product inventory 
        inventory = new ProductInventory();
        inventory.init();
    }
static JLineMenu saveReceipt;
    public static void main(String[] args) {      
        
        inventory = new ProductInventory();
        inventory.init();
        //deduct stock based on cart and orders
        loadStockFromCart();
        loadStockFromOrders();
        // initialize all menus
        initAllMenus();
        
        // start program
        while (true) {
            int selection = mainMenu.drawMenu();
            switch (selection) {
                case 0 -> {
                    if (currentCust == null) {
                        customer();
                    }
                    if (currentCust != null) {
                        customerDashboard();
                    }
                    continue;
                }
                case 1 -> {
                    if (currentAdmin == null) {
                        admin();
                    }
                    if (currentAdmin != null) {
                        adminDashboard();
                    }
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
        options.add("View Cart");
        options.add("View Orders");
        options.add("Change Account Details");
        options.add("Log Out");
        customerDb = new JLineMenu("Customer Dashboard", options, "Select an action to continue.", true, false);

        options.clear();
        options.add("Browse by Category");
        options.add("All Products");
        productMainMenu = new JLineMenu("View Products", options, "Select an action to continue.", true, false);
        
        // productCategoryMenu = new JLineMenu("Browse by Category", inventory.getAllCategoryNames(), "Select a product category.", true, false);
        List<MenuItem> menuItems = new ArrayList<>(inventory.getAllCategories());
        productCategoryMenu = new OOMenu("Browse by Category", menuItems, "Select a product category.", true, false);
        

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

        options.clear();
        options.add("Print Receipt");
       
        saveReceipt = new JLineMenu("Print Receipt?", options, "Do you want to save your receipt.", true, true);

        
    }
    

        
    

    public static void customer() {

        int Selection = customer.drawMenu();
        switch (Selection) {

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
        switch (Selection) {

            case 0 -> {
                login("admin");
                break;
            }

        }
    }
    
    public static void customerDashboard(){
        while(true){
            int selection = customerDb.drawMenu("Welcome Back, " + JLineMenu.MAGENTA + currentCust.getName() + JLineMenu.RESET + "!");
            
            if(selection == -1) break;
            if(selection == 4){
                currentCust.saveCart();
                currentCust=null;
                break;
            }

            switch (selection) {
                case 0 -> {
                    productMainMenu();
                }
                
                case 1 -> {
                    viewCart();
                }   
                
                //View Order
                case 2 -> {
                    viewOrders();
                }

                case 3 -> {
                    updateInfo("customer");
                    break;
                }

            }
        }

    }

    public static void adminDashboard() {
        while (true) {
            int selection = adminDb.drawMenu("Welcome Back, " + 
                    JLineMenu.MAGENTA + currentAdmin.getName() + JLineMenu.RESET + "!" + 
                            " (" + JLineMenu.CYAN + currentAdmin.getRole().toUpperCase() + JLineMenu.RESET + ")");
            if (selection == -1)break;

            if (selection == 7) {
                currentAdmin = null;
                break;
            }

            switch (selection) {
                case 0 -> {
                    break;
                }

                case 1 -> {
                    //Call to View ALL orders made by customers. So far code is running ok, but further testing needs to be done.
                    adminViewAllOrders();
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

    public static void login(String type) {
        JLineMenu.clearScreen();
        while (true) {
            System.out.print("Enter your UserName (999 to go back): ");
            String username = scanner.next();
            scanner.nextLine(); // for cleaning buffer purposes
            if (username.equals("999")) {
                break;
            }
            
            System.out.print("Enter your Password:" + JLineMenu.GREEN);
            String password = JLineMenu.reader.readLine(" ", '*');
            System.out.print(JLineMenu.RESET);


            //validating
            if (type.equals("customer")) {
                //customer login

                if ((currentCust = AuthServices.custlogin(username, password)) != null) {
                    currentAdmin = null;
                    currentCust.loadCartFromStorage();
                    break;
                } else {
                    JLineMenu.clearScreen();
                    System.out.println("Invalid Credentials!");
                    System.out.println();
                }
            } else {
                //admin login
                if ((currentAdmin = AuthServices.login(username, password)) != null) {
                    currentCust = null;
                    break;
                } else {
                    JLineMenu.clearScreen();
                    System.out.println("Invalid Credentials!");
                    System.out.println();
                }
            }

        }

    }

    public static void register(String type) {
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

        if (type.equals("admin") && !currentAdmin.isMain()) {
            System.out.println(JLineMenu.RED+"Access Denied!"+JLineMenu.RESET);
            System.out.print("Press Enter To Go Back....");
            scanner.nextLine();
            return;
        }

        // Username
        while (true) {
            System.out.print("Enter a username: ");
            username = scanner.next();
            scanner.nextLine(); // flush

            if (!username.isEmpty() && !usernames.contains(username) && !username.matches(".*[^a-zA-Z0-9].*")) {
                break;
            }
            else if(username.matches(".*[^a-zA-Z0-9].*")){
                JLineMenu.clearScreen();
                System.out.println("Username Cannot Have Symbols!");
            }
            else {
                JLineMenu.clearScreen();
                System.out.println("Username Taken!");
            }

            
        }

        JLineMenu.clearScreen();

        // Password
        while (true) {
            System.out.println("Enter a username: "+username);
            
            
            System.out.print("Enter a password:" + JLineMenu.GREEN);
            password = JLineMenu.reader.readLine(" ", '*');
            System.out.print(JLineMenu.RESET);
            
            System.out.print("Re-Enter your password:" + JLineMenu.GREEN);
            String password2 = JLineMenu.reader.readLine(" ", '*');
            System.out.print(JLineMenu.RESET);
            
            if(password.contains(",") || password.contains(" ")){
                   JLineMenu.clearScreen();
                   System.out.println("Password Cannot Have Comma or Space!\n");
            }
            else if(!password.equals(password2)){
                    JLineMenu.clearScreen();
                    System.out.println("Password did not match!\n");
            }
            else {
                break;
            }
            

        }

        JLineMenu.clearScreen();
        // Name
        while (true) {
            System.out.println("Enter a username: " + username);
            System.out.println("Enter a password: " + JLineMenu.GREEN + "*".repeat(password.length()) + JLineMenu.RESET);

            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if (!name.isEmpty() && !name.matches(".*[^a-zA-Z0-9 ].*")) {
                break;
            }
            else if(name.matches(".*[^a-zA-Z0-9 ].*")){
                JLineMenu.clearScreen();
                System.out.println("Name Cannot Have Symbols!\n");
            }
            else{
                JLineMenu.clearScreen();
                System.out.println("Name cannot be empty!\n");
            }
            
        }

        JLineMenu.clearScreen();
        // Email
        while (true) {
            System.out.println("Enter a username: " + username);
            System.out.println("Enter a password: " + JLineMenu.GREEN + "*".repeat(password.length()) + JLineMenu.RESET);
            System.out.println("Enter your name: " + name);

            System.out.print("Enter your email: ");
            email = scanner.next();
            scanner.nextLine();
            if (email.contains("@") && email.contains(".") && !email.contains(",")) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid email format!\n");
        }

        JLineMenu.clearScreen();
        // Phone number
        while (true) {
            System.out.println("Enter a username: " + username);
            System.out.println("Enter a password: " + JLineMenu.GREEN + "*".repeat(password.length()) + JLineMenu.RESET);
            System.out.println("Enter your name: " + name);
            System.out.println("Enter your email: " + email);

            System.out.print("Enter your phone number: +60");
            phoneNumber = scanner.next();
            scanner.nextLine();
            if (phoneNumber.matches("\\d{9,10}") && phoneNumber.charAt(0) == '1') {
                phoneNumber = "60" + phoneNumber;
                break; // accepts 9–11 digits
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid phone number!\n");
        }

        JLineMenu.clearScreen();
        // Address
        while (true) {
            System.out.println("Enter a username: " + username);
            System.out.println("Enter a password: " + JLineMenu.GREEN + "*".repeat(password.length()) + JLineMenu.RESET);
            System.out.println("Enter your name: " + name);
            System.out.println("Enter your email: " + email);
            System.out.println("Enter your phone number: +" + phoneNumber);

            System.out.print("Enter your address: ");
            address = scanner.nextLine();
            if (!address.isEmpty()) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Address cannot be empty!\n");

        }

        JLineMenu.clearScreen();
        // Birth day
        while (true) {
            System.out.println("Enter a username: " + username);
            System.out.println("Enter a password: " + JLineMenu.GREEN + "*".repeat(password.length()) + JLineMenu.RESET);
            System.out.println("Enter your name: " + name);
            System.out.println("Enter your email: " + email);
            System.out.println("Enter your phone number: +" + phoneNumber);
            System.out.println("Enter your address: " + address);

            System.out.print("Enter your birth day (dd-mm-yyyy): ");
            birthdate = scanner.next();
            scanner.nextLine();
            if (birthdate.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$")) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid Date Format!\n");

        }

        JLineMenu.clearScreen();
        // Gender
        while (true) {
            System.out.println("Enter a username: " + username);
            System.out.println("Enter a password: " + JLineMenu.GREEN + "*".repeat(password.length()) + JLineMenu.RESET);
            System.out.println("Enter your name: " + name);
            System.out.println("Enter your email: " + email);
            System.out.println("Enter your phone number: +" + phoneNumber);
            System.out.println("Enter your address: " + address);
            System.out.println("Enter your birth day (dd-mm-yyyy): " + birthdate);

            System.out.print("Enter your gender (Male/Female): ");
            gender = scanner.next();
            scanner.nextLine();
            if (gender.equals("Male") || gender.equals("Female")) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Please enter 'Male' or 'Female'!\n");

        }

        if (type.equals("customer")) {
            AuthServices.register(username, password, name, email, phoneNumber, address, birthdate, gender);
        } else {
            AuthServices.registerAdmin(username, password, name, email, phoneNumber, address, birthdate, gender);
        }

    }
    
    
    public static void suspend(boolean suspended){
        JLineMenu.clearScreen();
        String[] details;
        int UID;
        String action = "suspend";
        if (!suspended) {
            action = "unsuspend";
        }

        while (true) {
            System.out.print("Enter a UID to " + action + " (-999 to exit): ");
            try {
                UID = scanner.nextInt();
                scanner.nextLine();
                if (UID == -999) {
                    break;
                }
            } catch (Exception e) {
                JLineMenu.clearScreen();
                scanner.nextLine();
                System.out.println("Invalid UID! \n");
                continue;
            }

            //checking if the uid exists
            if ((details = AuthServices.getUserDetails(UID)) != null && (!details[9].equals("admin") && !details[9].equals("main"))) {
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

                switch (selection) {
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

            } else {
                JLineMenu.clearScreen();
                System.out.println("User Does not Exist!\n");
            }
        }

    }

    public static void updateInfo(String type) {
        User currentUser = currentAdmin;
        if (type.equals("customer")) {
            currentUser = currentCust;
        }

        JLineMenu.clearScreen();
        int selection = changeDetails.drawMenu();
        switch (selection) {
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

    public static void changePass(User x) {
        JLineMenu.clearScreen();
        String input;

        while (true) {
            System.out.print("Enter Your Current Password (999 to go back): ");
            input = scanner.next();
            scanner.nextLine();

            if (input.equals("999")) {
                return;
            }

            if (input.equals(x.getPassword())) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid Password!\n");

        }
        
        while(true){
            
            System.out.print("Enter Your New Password:" + JLineMenu.GREEN);
            input = JLineMenu.reader.readLine(" ", '*');
            System.out.print(JLineMenu.RESET);
            
            
            System.out.print("Re-Enter Your New Password:" + JLineMenu.GREEN);
            String input2 = JLineMenu.reader.readLine(" ", '*');
            System.out.print(JLineMenu.RESET);
            
            if(input.contains(",") || input.contains(" ")){
                   JLineMenu.clearScreen();
                   System.out.println("Password Cannot Have Comma or Space!\n");
            }
            else if(!input.equals(input2)){
                    JLineMenu.clearScreen();
                    System.out.println("Password did not match!\n");
            }
            else {
                break;
            }
        }

        AuthServices.changePassword(x, input);
    }

    public static void changeName(User x) {
        JLineMenu.clearScreen();
        String input;

        while (true) {
            System.out.println("Your Current Name: " + x.getName());
            System.out.print("Enter Your New Name (999 to go back): ");
            input = scanner.nextLine();

            if (input.equals("999")) {
                return;
            }

            if (!input.isEmpty() && !input.matches(".*[^a-zA-Z0-9 ].*")) {
                break;
            }
            else if(input.matches(".*[^a-zA-Z0-9 ].*")){
                JLineMenu.clearScreen();
                System.out.println("Name Cannot Have Symbols!\n");
            }
            else{
                JLineMenu.clearScreen();
                System.out.println("Name cannot be empty!\n");
            }

        }

        AuthServices.changeName(x, input);
    }

    public static void changeAddress(User x) {
        JLineMenu.clearScreen();
        String input;

        while (true) {
            System.out.println("Your Current Address: " + x.getAddress());
            System.out.print("Enter Your New Address (999 to go back): ");
            input = scanner.nextLine();

            if (input.equals("999")) {
                return;
            }

            if (!input.isEmpty()) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Address Cannot Be Empty!\n");

        }

        AuthServices.changeAddress(x, input);
    }

    public static void changePhone(User x) {
        JLineMenu.clearScreen();
        String input;

        while (true) {
            System.out.println("Your Current Phone: +" + x.getPhone());
            System.out.print("Enter Your New Phone (999 to go back): +60");
            input = scanner.next();
            scanner.nextLine();

            if (input.equals("999")) {
                return;
            }

            if (input.matches("\\d{9,10}") && input.charAt(0) == '1') {
                input = "60" + input;
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid Phone format!\n");

        }

        AuthServices.changePhone(x, input);
    }

    public static void changeEmail(User x) {
        JLineMenu.clearScreen();
        String input;

        while (true) {
            System.out.println("Your Current Email: " + x.getEmail());
            System.out.print("Enter Your New Email (999 to go back): ");
            input = scanner.nextLine();

            if (input.equals("999")) {
                return;
            }

            if (input.contains("@") &&  input.contains(".") && !input.contains(",")) {
                break;
            }
            JLineMenu.clearScreen();
            System.out.println("Invalid email format!\n");

        }

        AuthServices.changeEmail(x, input);
    }

    public static boolean payment(Order order) {
        Order dummyOrder = new Order(currentCust.getUID());
        currentCust.getCartItems().forEach((p,q) -> dummyOrder.addItem(p,q));
        
        boolean valid = false;
        while (!valid) {    //=true then 
            int selection = payment.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return false;
            }
            
            
            // here, depending on what we chose, save the payment method
            switch (selection) {
                case 0 -> {
                    valid = onlineBankingPaymentProcess(dummyOrder);
                    if (valid) lastPaymentMethod = "Online Banking";
                    continue;
                }
                case 1 -> {
                    valid = qrCodePayment(dummyOrder);
                    if (valid) lastPaymentMethod = "Touch and Go";
                    continue;
                }
                case 2 -> {
                    valid = cardPaymentProcess(dummyOrder);
                    if (valid) lastPaymentMethod = "Card Payment";
                    continue;
                }
                default -> {
                    continue;
                }
            }

        }
        return valid;
    }

    //here, we call this method to combine payment method and bank name, and is used to save it into orders.csv
    public static String getFormattedPaymentMethod() {
        if (lastPaymentMethod.equals("Touch and Go")) {
            return "Touch and Go";
        } else {
            return String.format("%s - %s", lastPaymentMethod, lastBankName);
        }
    }
    
    public static boolean onlineBankingPaymentProcess(Order order) {
        Payment paymentO;
        String bankName;
        while (true) {
            int selection = bankSelection.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return false;
            }

            switch (selection) {

                case 0 ->
                    bankName = "Hong Leong Bank";
                case 1 ->
                    bankName = "Alliance Bank";
                case 3 ->
                    bankName = "Public Bank";
                case 4 ->
                    bankName = "CIMB Bank";
                case 5 ->
                    bankName = "Maybank";
                default -> {
                    continue;
                }

            }
            
            //save payment method and bank name
            if (true) {
                lastPaymentMethod = "Online Banking";
                lastBankName = bankName;
            }
            paymentO = new OnlineBankingPayment(order, bankName);
            return processPayment(paymentO, order);
        }

    }

    private static boolean processPayment(Payment paymentO, Order order) {
        boolean shouldExit = false;
        while (!shouldExit) {
            if (paymentO.validation()) {
                JLineMenu.sound();
                System.out.println(JLineMenu.GREEN + "Successful!" + JLineMenu.RESET);
                JLineMenu.waitMsg();
                JLineMenu.clearScreen();
                JLineMenu.printHeader("Receipt", 20);
                paymentO.generateReceipt(order);
                
                
                int selection = saveReceipt.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                 
            }else{
             paymentO.generatePrintableReceipt(order);
              JLineMenu.sound();
             JLineMenu.waitMsg();
            }
                
                shouldExit = true;
                
                
                
                
                
               
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

    public static boolean qrCodePayment(Order order) {
        Payment paymentO = new qrCodePayment(order);
        JLineMenu.printHeader("QR code Payment", 45);
        paymentO.generateQR();
        
        //save payment method, bank name = "" (none)
        if (true) {
            lastPaymentMethod = "Touch and Go";
            lastBankName = ""; // No bank name for TnG
        }
        
        return processPayment(paymentO, order);

    }

    public static boolean cardPaymentProcess(Order order) {

        Payment paymentO;
        String bankName;
        while (true) {
            int selection = bankSelection.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return false;
            }

            switch (selection) {

                case 0 ->
                    bankName = "Hong Leong Bank";
                case 1 ->
                    bankName = "Alliance Bank";
                case 3 ->
                    bankName = "Public Bank";
                case 4 ->
                    bankName = "CIMB Bank";
                case 5 ->
                    bankName = "Maybank";
                default -> {
                    continue;
                }

            }
            
            //save payment method and bank name
            if (true) {
                lastPaymentMethod = "Card Payment";
                lastBankName = bankName;
            }
            paymentO = new CardPayment(order, bankName);
            return processPayment(paymentO, order);
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
 ////check the date is valid format or not
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
    
    public static void productMainMenu() {
        while (true) {
            int selection = productMainMenu.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {
                return;
            }
            switch (selection) {
                case 0 -> { 
                    productCategoryMenu();
                    continue;
                }
                case 1 -> {
                    Product selectedProduct = listProducts(null);
                       if (selectedProduct != null) {
                       addToCartFlow(selectedProduct);
                   }
                    continue;
                }
                default -> {
                    continue;
                }
            }            
        }
    }
    
    //gets View Cart menu, has X(Cart Items) + 2 Choices (Place Order & Back)
    public static void viewCart() {
        while (true) {
            // Build dynamic options
            ArrayList<String> options = new ArrayList<>();

            // 1. Add cart items as selectable options
            currentCust.getCartItems().forEach((product, qty) -> {
                options.add(String.format("%s x%d (RM %.2f)", 
                    product.getName(), qty, product.getPrice() * qty));
            });

            // 2. Add standard buttons
            if (!options.isEmpty()) options.add("Place Order");
            options.add("Back");

            // Display menu
            JLineMenu cartMenu = new JLineMenu("Your Cart", options, 
                options.isEmpty() ? "Your cart is empty" : "", 
                false,  // Back is manually added
                false
            );

            int selection = cartMenu.drawMenu();

            // Handle selection
            if (selection == JLineMenu.BACK_OPTION || 
                selection == options.size() - 1) return; // Back

            if (!options.isEmpty() && selection == options.size() - 2) {
                System.out.print("Placeing Order Placeholder");
                placeOrderFlow(); // NEWLY ADDED TO PLACE ORDER! 
                continue;
            }

            // Item selected - show action menu
            Product selectedProduct = (Product) currentCust.getCartItems().keySet().toArray()[selection];
            editCartItem(selectedProduct);
        }
    }
    
    //Selects an item, gets 3 choices: Edit Quantity, Remove Item, Back
    private static void editCartItem(Product product) {
        int currentQty = currentCust.getCartItems().get(product);

        while (true) {
            ArrayList<String> options = new ArrayList<>();
            options.add("Edit Quantity");
            options.add("Remove Item");
            options.add("Back");

            JLineMenu actionMenu = new JLineMenu(product.getName(), options, 
                String.format("Current quantity: %d", currentQty), 
                false, false);

            int action = actionMenu.drawMenu();
            if (action == 2 || action == JLineMenu.BACK_OPTION) return; // Back

            if (action == 1) { // Remove
                returnToStock(product, currentQty);
                currentCust.getCartItems().remove(product);
                System.out.println("Item removed!");
                JLineMenu.waitMsg();
                return;
            }

            if (action == 0) { // Edit Qty
                editItemQuantity(product, currentQty);
                return;
            }
        }
    }
    
    // Adds back to stock
    private static void returnToStock(Product product, int quantity) {
        product.addStock(quantity); 
    }

    //Option for Edit Item in View Cart, has validations to check for: Type, Range (1-10), Stock is available or not
    private static void editItemQuantity(Product product, int oldQty) {
        while (true) {
            System.out.print("New quantity (1-10, 'b' to cancel): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("b")) return;

            try {
                int newQty = Integer.parseInt(input);
                if (newQty < 1 || newQty > 10) {
                    System.out.println("Invalid quantity (1-10 only)");
                    continue;
                }

                // Calculate stock difference
                int delta = newQty - oldQty;

                // Check stock availability for increases
                if (delta > 0 && !product.minusStock(delta)) {
                    System.out.println(product.getStock() > 0 ?
                        "Only " + product.getStock() + " available to add" :
                        "Out of stock");
                    continue;
                }

                // Update cart and stock
                if (delta < 0) {
                    product.addStock(-delta); // Return excess
                }

                currentCust.getCartItems().put(product, newQty);
                System.out.println("Quantity updated!");
                JLineMenu.waitMsg();
                return;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }
    
    public static void productCategoryMenu() {
        while (true) {
            int selection = productCategoryMenu.drawMenu();
            if (selection == OOMenu.BACK_OPTION_INT) {
                return;
            }

            // If user selected a product, proceed to add to cart
            Product selectedProduct = listProducts(inventory.getCategoryByIndex(selection)); // list products that belong to the selected category

            if (selectedProduct == null) continue; // User selected "back" -- redraw this menu.

            // add to cart method here
            //addToCart(selectedProduct) or something
            //I hath arrived; i shall giveth addToCartFlow(selectedProduct)
            //Oh, how could i, a mere mortal, receive a gift of such generosity without being crushed under the weight of my guilt?
            addToCartFlow(selectedProduct);
        }
    }
    
    /** Prints a selection menu for products.
     * 
     * @param category A ProductCategory, limits the products listed. If this is `null`, ALL products regardless of category are listed.
     * @return A Product object, or null (if user selects "Back" instead of a product).
     */
    public static Product listProducts(ProductCategory category) {
        List<Product> products;
        String header;

        if (category == null) {
            // if null is passed in as argument, that means we gonna list out ALL products regardless of category
            products = inventory.getAllProducts();
            products.sort(Comparator.comparing(Product::getName)); // Sort alphabetically by name
            header = "All Products";
        } else {
            products = inventory.getProductsByCategoryName(category.getName());
            header = "Products: " + category.getName();
        }
        // Cast the products to a list of MenuItems so we can pass it into the OOMenu constructor
        List<MenuItem> menuItems = new ArrayList<>(products);

        // Create the menu
        OOMenu productMenu = new OOMenu(header, menuItems, "Use the UP and DOWN keys to navigate the menu and view product details.\nHit ENTER to ADD TO CART.", true, false);
        
        // Draw menu and get selected index
        int selection = productMenu.drawMenu(); // drawMenu() returns either BACK_OPTION_INT or a positive integer which is an index of products
        if (selection == OOMenu.BACK_OPTION_INT) 
            // return null if the user selects "Back"
            return null;

        // Return selected Product if the user has selected one
        return products.get(selection);
    }

    

    //When Selecting an Item in Products, Call addToCartFlow to validate, if true, call currentCust.addToCart() in Customer.java
    public static void addToCartFlow(Product product) {
        while (true) {
            if (product.getStock() <= 0) {
                System.out.println("Apologies, but we're out of stock.");
                JLineMenu.waitMsg();
                return;
            }
            
            System.out.print("Please enter quantity (Max 10) (Input 'b' to cancel): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("b")) {
                return;
            }

            try {
                // Check Range 1 - 10
                int quantity = Integer.parseInt(input);
                if (quantity < 1 || quantity > 10) {
                    System.out.println("Invalid Range, Please try again.");
                    continue;
                }
                
                //Check Max Limit
                if (!currentCust.canAddToCart(product, quantity)) {
                System.out.println("Already Exceeded Max Limit! (Max 10)");
                continue;
                }
                
                //Check Stock
                if (!product.minusStock(quantity)) {
                    System.out.printf("Not enough stock! Only %d available.\n", product.getStock());
                    continue;
                }
               
                // Add to cart, return quantity if something goes wrong
                try {
                    currentCust.addToCart(product, quantity);
                    System.out.println(product.getName() + " x" + quantity + " added to cart!");
                    JLineMenu.waitMsg();
                    return;
                } catch (Exception e) {
                    product.addStock(quantity); // ROLLBACK
                    System.out.println("Failed to add to cart. Please try again.");
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, Please try again.");
            }
        }
    }
    
    //deduct item stock based on cart
    private static void loadStockFromCart() {
        Map<Integer, Map<Integer, Integer>> allCarts = CartStorage.loadAllCarts();

        allCarts.forEach((userId, cart) -> {
            cart.forEach((productId, quantity) -> {
                Product p = inventory.getProductById(productId);
                if (p != null) {
                    // Ensure stock reflects what's reserved in carts
                    p.minusStock(quantity); 
                }
            });
        });
    }
    
    //deduct item stock based on orders made
    private static void loadStockFromOrders() {
        try {
            // Get all orders (regardless of status)
            List<Order> allOrders = OrderStorage.loadOrdersForAll();

            for (Order order : allOrders) {
                // Only deduct stock for non-cancelled orders
                if (!order.getStatus().equalsIgnoreCase("Cancelled")) {
                    for (OrderItem item : order.getItems()) {
                        Product p = inventory.getProductById(item.getProduct().getId());
                        if (p != null) {
                            p.minusStock(item.getQuantity());
                        }
                    }
                }
                // For cancelled orders, stock remains unchanged
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not update stock from orders - " + e.getMessage());
        }
    }
    
    //Print The Place Order Menu
    public static void placeOrderFlow() {
        if (currentCust.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty!");
            JLineMenu.waitMsg();
            return;
        }

        Order order = new Order(currentCust.getUID());
        currentCust.getCartItems().forEach((product, quantity) -> {
            order.addItem(product, quantity);
        });
        
        ArrayList<String> options = new ArrayList<>();
        options.add("Proceed to Payment");
        String summary = buildOrderSummaryString(order);
        JLineMenu orderMenu = new JLineMenu("Order Summary", options, summary, true, false);
        int selection = orderMenu.drawMenu();
        if (selection == 0) { // Proceed to Payment
            processPayment(order);
        }
    }
    
    //Build Order Summary String to display the calculations and Grand Total of Order
    private static String buildOrderSummaryString(Order order) {
        StringBuilder sb = new StringBuilder();

        // Header
        sb.append(String.format("%-8s %-30s %-10s %-10s%n", "ID", "Product", "Qty", "Subtotal"));
        sb.append("--------------------------------------------------\n");

        // Items
        for (OrderItem item : order.getItems()) {
            sb.append(String.format("%-8d %-30s %-10d RM%-8.2f%n",
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getSubtotal()));
        }

        // Totals
        sb.append("\nSubtotal: \tRM ").append(String.format("%.2f", order.getSubtotal())).append("\n");
        sb.append("Delivery Fee: \tRM 20.00\n");
        sb.append("SST (6%): \tRM ").append(String.format("%.2f", order.getTax())).append("\n");
        sb.append("-------------------------------------\n");
        sb.append("GRAND TOTAL: \tRM ").append(String.format("%.2f", order.getGrandTotal())).append("\n");
        sb.append("-------------------------------------\n");

        return sb.toString();
    }
    
    //if payment() returns true, we :
    //1) set all the thingamagiks into order class
    //2) clear & save cart
    //3) PAYMENT SUCCESSFUL! FRICGGIN FINALLY JESUS CHRIST ALMIGHTY
    private static void processPayment(Order order) {
        if (payment(order)) {
            try {
                order.setPaymentMethod(getFormattedPaymentMethod());
                order.setStatus("Pending");
                OrderStorage.saveOrder(order);
                currentCust.getCartItems().clear();
                currentCust.saveCart();
                System.out.println("Payment Successful! Order placed.");
                JLineMenu.waitMsg();
            } catch (IOException e) {
                System.out.println("Error saving order: " + e.getMessage());
                JLineMenu.waitMsg();
            }
        }
    }
  
    //Customer view their made orders.
    public static void viewOrders() {
        try {
            List<Order> userOrders = OrderStorage.loadOrdersForUser(currentCust.getUID());

            if (userOrders.isEmpty()) {
                JLineMenu.clearScreen();
                System.out.println("No orders made yet.");
                JLineMenu.waitMsg();
                return;
            }

            ArrayList<String> options = new ArrayList<>();
            for (int i = 0; i < userOrders.size(); i++) {
                Order order = userOrders.get(i);
                options.add(String.format("Order #%d - %s (RM %.2f)", 
                    i+1, 
                    order.getFormattedOrderDate(), 
                    order.getGrandTotal()));
            }

            JLineMenu ordersMenu = new JLineMenu("Your Orders", options, 
                "Select an order to view details", true, false);
            int selection = ordersMenu.drawMenu();

            if (selection >= 0 && selection < userOrders.size()) {
                displayOrderDetails(userOrders.get(selection));
            }
        } catch (IOException e) {
            System.out.println("Error loading orders: " + e.getMessage());
            JLineMenu.waitMsg();
        }
    }

    private static void displayOrderDetails(Order order) {
        JLineMenu.clearScreen();
        JLineMenu.printHeader("Order #" + order.getOrderId(), 20);

        System.out.println("Order Date: " + order.getFormattedOrderDate());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Payment Method: " + order.getPaymentMethod());
        System.out.println("-------------------------------------");

        System.out.printf("%-30s %-10s %-10s%n", "Product", "Qty", "Subtotal");
        System.out.println("-------------------------------------");

        for (OrderItem item : order.getItems()) {
            System.out.printf("%-30s %-10d RM%-8.2f%n",
                item.getProduct().getName(),
                item.getQuantity(),
                item.getSubtotal());
        }

        System.out.println("\nGRAND TOTAL: \tRM " + String.format("%.2f", order.getGrandTotal()));
        System.out.println("-------------------------------------");
        JLineMenu.waitMsg();
    }
    
    
    //Section: Admin View Orders Menu
    //String for description
    private static String formatOrderDetails(Order order) {
        String[] userDetails = AuthServices.getUserDetails(order.getUserId());
        StringBuilder sb = new StringBuilder();

        // Header Info
        sb.append("Customer: ").append(userDetails[3]).append("\n");
        sb.append("Date: ").append(order.getFormattedOrderDate()).append("\n");
        sb.append("Status: ").append(order.getStatus()).append("\n");
        sb.append("Payment: ").append(order.getPaymentMethod()).append("\n\n");

        // Items Table
        sb.append(String.format("%-8s %-30s %-10s %-10s%n", "ID", "Product", "Qty", "Subtotal"));
        sb.append("--------------------------------------------------\n");

        order.getItems().forEach(item -> {
            sb.append(String.format("%-8d %-30s %-10d RM%-8.2f%n",
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getSubtotal()));
        });

        // Footer Totals
        sb.append("\n-------------------------------------\n");
        sb.append("GRAND TOTAL: RM").append(String.format("%.2f", order.getGrandTotal()));

        return sb.toString();
    }
    
    public static void adminViewAllOrders() {
        String currentSort = "Date(^)";
        boolean[] sortFlags = {true, true}; // [0] = dateAscending, [1] = nameAscending

        while (true) {
            try {
                // Load and sort orders
                List<Order> orders = OrderStorage.loadOrdersForAll();
                
                //Apply Sorting using final array (idfk what this is honestly)
                if (currentSort.startsWith("Date")) {
                    orders.sort(sortFlags[0] ? 
                        Comparator.comparing(Order::getOrderDate) :
                        Comparator.comparing(Order::getOrderDate).reversed());
                } else {
                    orders.sort((o1, o2) -> {
                        String name1 = AuthServices.getUserDetails(o1.getUserId())[3];
                        String name2 = AuthServices.getUserDetails(o2.getUserId())[3];
                        return sortFlags[1] ? 
                            name1.compareTo(name2) : name2.compareTo(name1);
                    });
                }

                // Build menu
                ArrayList<String> options = new ArrayList<>();
                orders.forEach(order -> {
                    String userName = AuthServices.getUserDetails(order.getUserId())[3];
                    options.add(String.format("Order #%s (%s, %s) - %s", 
                        order.getOrderId().substring(0,6),
                        userName,
                        order.getFormattedOrderDate(),
                        order.getStatus()));
                });

                // Add sort options
                options.add("Sort: Date" + (currentSort.startsWith("Date") ? 
                    (sortFlags[0] ? "(^)" : "(v)") : ""));
                options.add("Sort: Customer" + (currentSort.startsWith("Customer") ? 
                    (sortFlags[1] ? "(^)" : "(v)") : ""));

                JLineMenu menu = new JLineMenu("All Orders", options, 
                    "Total Orders: " + orders.size(), true, false);

                int selection = menu.drawMenu();

                // Handle selection
                if (selection == JLineMenu.BACK_OPTION) return;

                if (selection == options.size() - 2) { // Date sort
                    sortFlags[0] = !sortFlags[0];
                    currentSort = "Date" + (sortFlags[0] ? "(^)" : "(v)");
                } 
                else if (selection == options.size() - 1) { // Name sort
                    sortFlags[1] = !sortFlags[1];
                    currentSort = "Customer" + (sortFlags[1] ? "(^)" : "(v)");
                }
                else {
                    adminManageOrder(orders.get(selection));
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                JLineMenu.waitMsg();
            }
        }
    }
    
    //Selects an order, gives selection: Update Status which calls updateOrderStatus, and the description is the Order Details, Header is order ID.
    private static void adminManageOrder(Order order) throws IOException {
        while (true) {
            // Create menu with order details as description
            ArrayList<String> options = new ArrayList<>();
            options.add("Update Status");

            JLineMenu menu = new JLineMenu("Order " + order.getOrderId(), 
                options, 
                formatOrderDetails(order), 
                true, false);

            int choice = menu.drawMenu();

            if (choice == JLineMenu.BACK_OPTION) return;
            if (choice == 0) updateOrderStatus(order);
        }
    }
    
    
    //Updates Order Status: Selections: Pending, Shipping, Completed, Cancelled. Theres no Validation if you select the same Status.
    private static void updateOrderStatus(Order order) throws IOException {
        String[] statusOptions = {"Pending", "Shipping", "Completed", "Cancelled"};

        while (true) {
            JLineMenu statusMenu = new JLineMenu("Update Status", 
                Arrays.asList(statusOptions), 
                "Current Status: " + order.getStatus(), 
                true, false);

            int selection = statusMenu.drawMenu();

            if (selection == JLineMenu.BACK_OPTION) return;

            String newStatus = statusOptions[selection];
            if (!order.getStatus().equals(newStatus)) {
                order.setStatus(newStatus);
                OrderStorage.updateOrderStatus(order);
                System.out.println("Status updated to: " + newStatus);
                JLineMenu.waitMsg();
                return; // Back to order view
            }
        }
    }
    
    //End of Section: Admin View Orders Menu
}

