/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

/**
 *
 * @author wayne
 */

public class Admin extends User{
    
    
    public Admin(String userName, String password, int UID, String name, String email, String phoneNumber, String address, String birthdate, String gender, String role,String status){
        super(userName, password, UID, name, email, phoneNumber, address, birthdate, gender, role, status);
    }
    //addprodct
    public void addProduct(Product x){
        if(isAdmin()){
//            Inventory.getAllProducts().add(x);
        } 
    }
    
}
