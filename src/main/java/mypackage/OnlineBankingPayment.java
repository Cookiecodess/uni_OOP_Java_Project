/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 *
 * @author songl
 */

public class OnlineBankingPayment extends Payment{
     private static final String BANKINGUSERNAME = "aaa";
     private static final String BANKINGPASSWORD = "bbb";
     private final String bankName;
     public  OnlineBankingPayment(double amount, Order order,String bankName) {
       super("Online Banking", amount, order);
       this.bankName=bankName;
    }

     
     
     
     @Override
    public boolean validation(){
        Scanner scanner = new Scanner(System.in);
JLineMenu.printHeader("Online Banking",20);
          
        System.out.print("Enter your bank username : ");
    String username=scanner.nextLine();
    
    System.out.print("Enter your bank password : ");
    String password=scanner.nextLine();
    
    
    return username.compareTo(BANKINGUSERNAME)==0 && password.compareTo(BANKINGPASSWORD)==0;
    }
    
     @Override
     public void generateQR(){
     
     throw new UnsupportedOperationException("Not supported for card payment");
     }
     
     @Override
     public String failMessage(){
     return "Sorry your bank username or password are invalid";
     }
     
     
     @Override
         public void generateReceipt(Order order){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//to set the format of the date and time
        
         System.out.println("Order ID\t: "+order.getOrderId());
         System.out.println("Payment Method\t: "+getPaymentMethod());
          System.out.println("Bank\t\t: "+bankName);
        System.out.println("Amount\t\t: RM "+JLineMenu.GREEN+ String.format("%.2f", getAmount())+JLineMenu.RESET);
         System.out.println("Date\t\t: "+JLineMenu.GREEN+getDateTime().format(formatter)+JLineMenu.RESET);
         System.out.println("=============================================");
         JLineMenu.waitMsg();
   
    };

}