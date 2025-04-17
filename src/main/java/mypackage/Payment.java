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

public abstract class Payment{    //since payment is an "idea" dont have a specific method to implement
        private int orderId;
        private String paymentMethod;
        private double total;
        private LocalDateTime dateTime=LocalDateTime.now();
        // protected Order order; //since its is one to one relationship
            //and the protected means this class ,parent class and same package class can use

    public Payment(int orderId,double total,String paymentMethod){
        this.orderId=orderId;
        this.total=total;
        this.paymentMethod=paymentMethod;
    }
    public abstract boolean validatePayment();
    public String Receipt(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "\n========= Receipt ==========" +
               "\nOrder ID       : " + orderId +
               "\nPayment Method : " + paymentMethod +
               "\nAmount         : RM " + String.format("%.2f", total) +
               "\nDate           : " + dateTime.format(formatter) +
               "\n============================\n";
    };

    public int getOrderId(){
    return orderId;
    }
     public double getAmount() {
        return total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
        public LocalDateTime getDateTime() {
        return dateTime;
    }
}


