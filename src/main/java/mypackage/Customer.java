/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author wayne
 */
public class Customer extends User{
    
    //This records Cart Items and its quantities
    private final Map<Product, Integer> cartItems;
    
    private List<Order> orderHistory = new ArrayList<Order>();
    private List<Product> wishList = new ArrayList<Product>();
    
    
    public Customer(String userName, String password, int UID, String name, String email, String phoneNumber, String address, String birthdate, String gender, String role,String status){
        super(userName, password, UID, name, email, phoneNumber, address, birthdate, gender, role, status);
        this.cartItems = new HashMap<>();
        loadCartFromStorage();
    }
    
    //loads from cart.csv
    public void loadCartFromStorage() {
        Map<Integer, Integer> savedCart = CartStorage.loadCart(this.getUID());
        this.cartItems.clear(); // Clear existing items

        savedCart.forEach((prodId, qty) -> {
            Product p = Main.inventory.getProductById(prodId);
            if (p != null) {
                this.cartItems.put(p, qty);
            }
        });
    }
    
    //converts values and calls CartStorage.saveCart
    public void saveCart() {
        Map<Integer, Integer> productIdToQuantity = new HashMap<>();
        this.cartItems.forEach((product, qty) -> {
            productIdToQuantity.put(product.getId(), qty);
        });
        CartStorage.saveCart(this.getUID(), productIdToQuantity);
    }
    
    //Checks if Item quantities added the cart is below 10, returns true false
    public boolean canAddToCart(Product product, int requestedQty) {
        int currentQty = cartItems.getOrDefault(product, 0);
        return (currentQty + requestedQty) <= 10;
    }
    
    //adds to cartItems
    public void addToCart(Product product, int quantity) {
        cartItems.merge(product, quantity, Integer::sum);
    }
    
    public void placeOrder(Order x){
        orderHistory.add(x);
    }
    
    public void addToWishlist(Product x){
        wishList.add(x);
    }
  
    public Map<Product, Integer> getCartItems() {
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
