/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.util.Scanner;


/**
 *
 * @author songl
 */

public class OnlineBankingPayment extends Payment implements Payable{
     private static final String USERNAME = "OnlineBanking";
     private static final String PASSWORD = "OnlineBankuserPassword1";

     public  OnlineBankingPayment(double amount, Order order) {
       super("Online Banking", amount, order);
       
    }
     
     @Override
           public boolean process(){
               Scanner scanner=new Scanner(System.in);
                      
               while (true) {
            int selection = Main.bankSelection.drawMenu();
            if (selection == JLineMenu.BACK_OPTION) {

                //System.out.print("You are using "+bankList[selection-1]);
                System.out.print("Enter User ID: ");
                String inputUsername = scanner.nextLine();  //declare and get the userid
                 System.out.print("Enter Password: ");
                String inputPassword = scanner.nextLine();
                
                 if (!inputUsername.equals(USERNAME) || !inputPassword.equals(PASSWORD)) {
                 JLineMenu.errorMsg("Login failed. Please try again.");

                    return false;
                    }
                
            }

            
            JLineMenu.waitMsg();
               
               
               
           return true;
           }
           }
     
     
}