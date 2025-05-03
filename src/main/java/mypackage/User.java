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
public abstract class User {
    
    private int UID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String birthDate;
    private String gender;
    private String status;
    
    //for security
    private String userName;
    private String password;
    private String role;
    
    
    public User(String userName, String password, int UID, String name, String email, String phoneNumber, String address, String birthdate, String gender, String role,String status){
        this.userName = userName;
        this.password = password;
        this.UID = UID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthdate;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }
    
 
   
    
    //=====================getter and setter for name===============================\\
    public String getName(){
        return this.name;
    }
    
    public void setName(String x){
        this.name = x;
    }
    
    
    //=====================getter and setter for email===============================\\
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String x){
        this.email = x;
    }
    
    
    
    
    //=====================getter and setter for phonenumber===============================\\
    public String getPhone(){
        return this.phoneNumber;
    }
    
    public void setPhone(String x){
        this.phoneNumber = x;
    }
    
    
    
    //=====================getter and setter for address===============================\\
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String x){
         this.address = x;
    }
    
    
    
    
    //=====================getter and setter for gender===============================\\
    public String getGender(){
        return this.gender;
    }
    
    public void setGender(String x){
         this.gender = x;
    }
    
    
    //=====================getter and setter for birthdate===============================\\
    public String getBirthday(){
        return this.birthDate;
    }
    
    public void setBirthday(String x){
         this.birthDate = x;
    }
    
    //=====================getter for username===============================\\
    public void setUsername(String x){
        this.userName = x;
    }
    
    public String getUsername(){
        return this.userName;
    }
    
 
    
    //=====================getter and setter for password===============================\\
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String x){
        this.password = x;
    }
    
    
    //=====================getter for role===============================\\
    public boolean isAdmin(){
        return (this.role.equalsIgnoreCase("admin") || this.role.equalsIgnoreCase("main"));
    }
    
    public boolean isMain(){
        return this.role.equalsIgnoreCase("main");
    }
    
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }
    
    
    
    //=====================getter for uid===============================\\
    public int getUID(){
        return this.UID;
    }
    
    public void setUID(int uid){
        this.UID = uid;
    }
    
    //====================get status======================\\
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String stat){
        this.status = stat;
    }
    
}



