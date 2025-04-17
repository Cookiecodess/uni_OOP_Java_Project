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
public class CardPayment extends Payment{
    private static final String CARD_NUMBER = "1234 5678 1234 5678";
    private static final String EXPIRE_DATE = "03/29";
    private static final String CVV = "123";
        public CardPayment(double amount, Order order) {
        super("Card", amount, order);
    }
     @Override
    public boolean process(){
    
    
    
    
    
    
    
    return true;
    }
}
