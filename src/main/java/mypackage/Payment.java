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
        private String paymentMethod; 
        
        private double amount;
        private Order order;
        private LocalDateTime dateTime=LocalDateTime.now();

    public Payment(String paymentMethod, double amount, Order order){
        this.order=order;
        this.amount=amount;
        this.paymentMethod=paymentMethod;
    }
    
    
    public void generateReceipt(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//to set the format of the date and time
        System.out.println("=========== Receipt ============");
         System.out.println("Order ID\t: "+order.getOrderId);
         System.out.println("Payment Method\t: "+paymentMethod);
        System.out.println("Amount\t: RM "+ String.format("%.2f", amount));
         System.out.println("Date\t: "+dateTime.format(formatter));
         System.out.println("Order ID\t: "+order.getOrderId);
         System.out.println("===============================");
         JLineMenu.waitMsg();
   
    };

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
        public LocalDateTime getDateTime() {
        return dateTime;
    }
}


