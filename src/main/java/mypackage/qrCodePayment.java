/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class qrCodePayment extends Payment{
        
    
    
            public qrCodePayment(double amount, Order order) {
               super("Card", amount, order);
    }
    @Override
    public boolean process(){
        try {
      File myObj = new File("qrcode.txt");
      Scanner qrOutput = new Scanner(myObj);  
      while (qrOutput.hasNextLine()) {
        String data = qrOutput.nextLine();
        System.out.println(data);
      }
      qrOutput.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    

    }
    JLineMenu.waitMsg();
    
    
    
    
    
    return true;
    }
    
}
