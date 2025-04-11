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
public class User {
    //current database
    private static List<User> usersList = new ArrayList<User>();
    
    private int UID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String birthDate;
    private String gender;
    private String status;
    private User thisUser; // assign User object to thisUser when log in
                           // so we can easily update the info in the List as well
    //for security
    private String userName;
    private String password;
    private String role;
    private boolean isLoggedIn;
    
    public User(){
        this("","",0,"","","","","","","","");
    }
    
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
    
    //initializing the List with some admin users first
    static {
        usersList.add(new User("admin","admin123",1,"John Apple Seed","john@admin.com","60126289399","Jalan Tunku Abdul Rahman","01-10-2003","Male","admin","active"));
    }
   
    public void login(String username, String password){
        
        for(User x : usersList){
            if(username.equals(x.userName) && password.equals(x.password)){
                //matched... 
                thisUser = x;
                this.isLoggedIn = x.isLoggedIn = true;
                return;
                /*
                this.userName =  x.getUsername();
                this.password = x.getPassword();
                this.UID = x.getUID();
                this.name = x.getName();
                this.email = x.getEmail();
                this.phoneNumber = x.getPhone();
                this.address = x.getAddress();
                this.birthDate = x.getBirthday();
                this.gender = x.getGender();
                this.role = x.isAdmin() ? "admin" : "customer";
                this.status = x.getStatus();*/
            }
         }
        //not match
    }
    
    //add user to the Users list
    public static void addUser(User x){
        usersList.add(x);
    }
    
    
    //=====================generate UID for customer registration===============================\\
    public static int genUID(){
        return usersList.getLast().UID + 1;
    }
    
    //=====================getter and setter for name===============================\\
    public String getName(){
        return thisUser.name;
    }
    
    public void setName(String x){
        if(isLoggedIn) thisUser.name = x;
    }
    
    
    //=====================getter and setter for email===============================\\
    public String getEmail(){
        return thisUser.email;
    }
    
    public void setEmail(String x){
        if(isLoggedIn) thisUser.email = x;
    }
    
    
    
    
    //=====================getter and setter for phonenumber===============================\\
    public String getPhone(){
        return thisUser.phoneNumber;
    }
    
    public void setPhone(String x){
        if(isLoggedIn) thisUser.phoneNumber = x;
    }
    
    
    
    //=====================getter and setter for address===============================\\
    public String getAddress(){
        return thisUser.address;
    }
    
    public void setAddress(String x){
        if(isLoggedIn) thisUser.address = x;
    }
    
    
    
    
    //=====================getter and setter for gender===============================\\
    public String getGender(){
        return thisUser.gender;
    }
    
    public void setGender(String x){
        if(isLoggedIn) thisUser.gender = x;
    }
    
    
    //=====================getter and setter for birthdate===============================\\
    public String getBirthday(){
        return thisUser.birthDate;
    }
    
    public void setBirthday(String x){
        if(isLoggedIn) thisUser.birthDate = x;
    }
    
    //=====================getter for username===============================\\
    public String getUsername(){
        return thisUser.userName;
    }
    
 
    
    //=====================getter and setter for password===============================\\
    public String getPassword(){
        return thisUser.password;
    }
    
    public void setPassword(String x){
        if(isLoggedIn) thisUser.password = x;
    }
    
    
    //=====================getter for role===============================\\
    public boolean isAdmin(){
        return (thisUser.role.equalsIgnoreCase("admin"));
    }
    
    
    
    //=====================getter for uid===============================\\
    public int getUID(){
        return thisUser.UID;
    }
    
    //====================get status======================\\
    public String getStatus(){
        return thisUser.status;
    }
    
    //only admin can access
    protected void suspend(int UID){
        if(isAdmin()){
            
            for(User x: usersList){
                x.thisUser = x;
                if(x.getUID() == UID) x.status = "suspended";

            }
        }
        
    }
    
    protected void activate(int UID){
        if(isAdmin()){
            for(User x: usersList){
                x.thisUser = x;
                if(x.getUID() == UID) x.status = "active";
            }
        }
    }
    
    protected void delete(int UID){
        if(isAdmin()){
            for(User x: usersList){
                x.thisUser = x;
                if(x.getUID() == UID) usersList.remove(x);
            }
        }
    }
}



