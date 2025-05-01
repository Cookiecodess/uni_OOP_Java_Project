/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author songl
 */

public abstract class Payment implements Payable{    //since payment is an "idea" dont have a specific method to implement
//public abstract class Payment{  
       private String paymentMethod; 
        
     //   private double amount;
        private Order order;
        private LocalDateTime dateTime=LocalDateTime.now();

    public Payment(String paymentMethod, Order order){
        this.order=order;
     //   this.amount=amount;
       this.paymentMethod=paymentMethod;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

//    public double getAmount() {
//        return amount;
//    }

//    public void setAmount(double amount) {
//        if (amount < 0) {
//            throw new IllegalArgumentException("Amount cannot be negative.");
//        }
//        this.amount = amount;
//    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
        public LocalDateTime getDateTime() {
        return dateTime;
    }
      
         public abstract void generateQR();
        
         
         
        @Override
      public abstract boolean validation();  
       
     
       @Override
      public abstract String failMessage();  
      
          
    @Override
    public abstract void generateReceipt(Order order);

@Override
   public abstract void generatePrintableReceipt(Order order);


}


