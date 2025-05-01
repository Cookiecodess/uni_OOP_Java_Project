/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

/**
 *
 * @author songl
 */
public class OrderItem {
    private Product product;
    private int quantity;
    private double subtotal;
    
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    
    public Product getProduct() { 
        return product; 
    }
    public int getQuantity() { 
        return quantity; 
    }
    public double getSubtotal() { 
        return subtotal; 
    }
    
    
}
