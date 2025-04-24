/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;


/**
 *
 * @author songl
 */

public class OnlineBankingPayment extends Payment{
     private static final String BANKINGUSERNAME = "aaa";
     private static final String BANKINGPASSWORD = "bbb";

     public  OnlineBankingPayment(double amount, Order order) {
       super("Online Banking", amount, order);
       
    }
     
    public boolean validateOBPayment(String username,String password){

    return username.compareTo(BANKINGUSERNAME)==0 && password.compareTo(BANKINGPASSWORD)==0;
    }
    
    public String getBName(){
    return BANKINGUSERNAME;
    }
         public String getBPassword(){
    return BANKINGPASSWORD;
    }
     
}