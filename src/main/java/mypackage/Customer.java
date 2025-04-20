/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author wayne
 */
public class Customer extends User{
    
    private List<Product> cartItems = new ArrayList<Product>();
    private List<Order> orderHistory = new ArrayList<Order>();
    private List<Product> wishList = new ArrayList<Product>();
    
    
    public Customer(String userName, String password, int UID, String name, String email, String phoneNumber, String address, String birthdate, String gender, String role,String status){
        super(userName, password, UID, name, email, phoneNumber, address, birthdate, gender, role, status);
    }
    
    
    public void addToCart(Product x){
        cartItems.add(x);
    }
    
    public void placeOrder(Order x){
        orderHistory.add(x);
    }
    
    public void addToWishlist(Product x){
        wishList.add(x);
    }
    
    public List<Product> getCartItems(){
        return cartItems;
    }
    
    public void addOrder(Order x){
        orderHistory.add(x);
    }
    
    public List<Order> getAllOrders(){
        return orderHistory;
    }
    
    public List<Product> getWishList(){
        return wishList;
    }
}
