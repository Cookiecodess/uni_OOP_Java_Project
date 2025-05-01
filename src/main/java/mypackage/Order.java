/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author songl
 */
public class Order {
    private String orderId;
    private int userId;
    private LocalDateTime orderDate;
    private List<OrderItem> items;
    private double subtotal;
    private double deliveryFee = 20.00;
    private double tax;
    private double grandTotal;
    private String paymentMethod;
    private String status; // "Pending", "Processing", "Shipped", "Delivered", "Cancelled"
    
    public Order(int userId) {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.userId = userId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.status = "Pending";
    }
    
    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        subtotal += item.getSubtotal();
        calculateTotals();
    }
    
    private void calculateTotals() {
        tax = (subtotal + deliveryFee) * 0.06;
        grandTotal = subtotal + deliveryFee + tax;
    }
    
    
    public String getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public List<OrderItem> getItems() { return items; }
    public double getSubtotal() { return subtotal; }
    public double getDeliveryFee() { return deliveryFee; }
    public double getTax() { return tax; }
    public double getGrandTotal() { return grandTotal; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    
    public void setPaymentMethod(String paymentMethod) { 
        this.paymentMethod = paymentMethod; 
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    
    public void setOrderDate(LocalDateTime orderDate){
        this.orderDate = orderDate;
    }
    
    public void setGrandTotal(double grandTotal){
        this.grandTotal = grandTotal;
    }
    
    public String getFormattedOrderDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
        
}
