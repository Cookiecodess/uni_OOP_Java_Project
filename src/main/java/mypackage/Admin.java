/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author wayne
 */
public class Admin extends User{
    
    //addprodct
    public void addProduct(Product x){
        if(isAdmin()){
            Inventory.getAllProducts().add(x);
        } 
    }
    
}
