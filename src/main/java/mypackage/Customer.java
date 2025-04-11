/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
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
    
  
    public static void register(String username, String password, 
            String name, String email, String phone, 
            String address, String birthdate, String gender){
            User.addUser(new User(username, password, genUID(), name, email, phone, address, birthdate, gender, "customer", "active"));
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
    
    public List<Order> getAllOrders(){
        return orderHistory;
    }
    
    public List<Product> getWishList(){
        return wishList;
    }
}
