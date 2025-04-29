/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 *
 * @author wayne
 */
public class AuthServices {
    
    private static final String FILEPATH = "/credentials.csv";
    private static final File FILE = AuthServices.getFile();
    private static ArrayList<String> usernames = new ArrayList<>();
    
    static{
        setAllUsernames();
    }
    
    
    public static File getFile(){
        try{
            return new File(AuthServices.class.getResource(FILEPATH).toURI());
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public static int genUID(){
        
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            ;
        }
        
        //getting last line
        String lastLine = lines.get(lines.size()-1);
        int lastUID = Integer.parseInt(lastLine.split(",")[2]);
        
        return lastUID+1;
    }
    
    
    
    
   
    // Login for Admin
    public static Admin login(String username, String password){
        String line;

        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] values = line.split(",");
    
                if(values[0].equals(username) && values[1].equals(password) && (values[9].equals("main") || values[9].equals("admin")) ){
                    return new Admin(values[0],values[1],Integer.parseInt(values[2]),values[3],values[4],values[5],formatAddress(values[6], false),values[7],values[8],values[9],values[10]);
                }else{
                    continue;
                }
                
      
            }
        } catch (Exception e) {
            ;
        }
        
        return null;
    }
    
    public static Customer custlogin(String username, String password){
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] values = line.split(",");
                if(values[0].equals(username) && values[1].equals(password) && values[9].equals("customer") ){
                    return new Customer(values[0],values[1],Integer.parseInt(values[2]),values[3],values[4],values[5],formatAddress(values[6], false),values[7],values[8],values[9],values[10]);
                }else{
                    continue;
                }
                
      
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    public static boolean register(String userName, String password, String name, String email, String phoneNumber, String address, String birthdate, String gender){
        int UID = genUID();
        String role = "customer";
        String status = "active";
        String newLine = userName + "," + password + "," + String.valueOf(UID) + "," + name + "," + email + "," + phoneNumber + "," + formatAddress(address, true) + "," + birthdate + "," + gender + "," + role + "," + status;
        
        try (FileWriter fw = new FileWriter(FILE, true)) { // 'true' enables append mode
            fw.write("\n" + newLine); // add newline before writing
            return true;
        } catch (Exception e) {
            ;
        }
        return false;
    }
    
    public static boolean registerAdmin(String userName, String password, String name, String email, String phoneNumber, String address, String birthdate, String gender){
        int UID = genUID();
        String role = "admin";
        String status = "active";
        
        String newLine = userName + "," + password + "," + String.valueOf(UID) + "," + name + "," + email + "," + phoneNumber + "," + formatAddress(address, true) + "," + birthdate + "," + gender + "," + role + "," + status;
        
        try (FileWriter fw = new FileWriter(FILE, true)) { // 'true' enables append mode
            fw.write("\n" + newLine); // add newline before writing
            return true;
        } catch (Exception e) {
            ;
        }

        return false;
    }
    
    public static ArrayList<String> getUsernames(){
        return usernames;
    }
    
    public static void setAllUsernames(){
        usernames.clear();
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] values = line.split(",");
                String username = values[0];
                usernames.add(username);
                
      
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void changePassword(User x, String newPassword){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
            //finding the line to edit
            for(int i=0; i<lines.size(); i++){
                String currentLine = lines.get(i);
                String[] splitLine = currentLine.split(",");
                
                if(x.getUsername().equals(splitLine[0])){
                    splitLine[1] = newPassword;
                    String updatedLine = String.join(",", splitLine);
                    lines.set(i, updatedLine);
                }
                 
            }
            
            int counter=0;
            //writing back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                for (String l : lines) {
                    if(counter==0) bw.write(l);
                    else bw.write("\n"+l);
                    counter++;
                    
                }
                x.setPassword(newPassword);
            }
            
        } 
        
        
        catch (Exception e) {
            ;
        }
    }       
 
    public static void changeName(User x, String newName){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
            //finding the line to edit
            for(int i=0; i<lines.size(); i++){
                String currentLine = lines.get(i);
                String[] splitLine = currentLine.split(",");
                
                if(x.getUsername().equals(splitLine[0])){
                    splitLine[3] = newName;
                    String updatedLine = String.join(",", splitLine);
                    lines.set(i, updatedLine);
                }
                 
            }
            
            int counter=0;
            //writing back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                for (String l : lines) {
                    if(counter==0) bw.write(l);
                    else bw.write("\n"+l);
                    counter++;
                }
                x.setName(newName);
            }
            
        } 
        
        
        catch (Exception e) {
            ;
        }
    }
    
    public static void changeEmail(User x, String newEmail){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
            //finding the line to edit
            for(int i=0; i<lines.size(); i++){
                String currentLine = lines.get(i);
                String[] splitLine = currentLine.split(",");
                
                if(x.getUsername().equals(splitLine[0])){
                    splitLine[4] = newEmail;
                    String updatedLine = String.join(",", splitLine);
                    lines.set(i, updatedLine);
                }
                 
            }
            
            int counter=0;
            //writing back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                for (String l : lines) {
                    if(counter==0) bw.write(l);
                    else bw.write("\n"+l);
                    counter++;
                }
                x.setEmail(newEmail);
            }
            
        } 
        
        
        catch (Exception e) {
            ;
        }
    }
    
    public static void changeAddress(User x, String newAddress){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
            //finding the line to edit
            for(int i=0; i<lines.size(); i++){
                String currentLine = lines.get(i);
                String[] splitLine = currentLine.split(",");
                
                if(x.getUsername().equals(splitLine[0])){
                    splitLine[6] = formatAddress(newAddress, true);
                    String updatedLine = String.join(",", splitLine);
                    lines.set(i, updatedLine);
                }
                 
            }
            
            int counter=0;
            //writing back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                for (String l : lines) {
                    if(counter==0) bw.write(l);
                    else bw.write("\n"+l);
                    counter++;
                }
                x.setAddress(newAddress);
            }
            
        } 
        
        
        catch (Exception e) {
            ;
        }
    }
    
    public static void changePhone(User x, String newPhone){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
            //finding the line to edit
            for(int i=0; i<lines.size(); i++){
                String currentLine = lines.get(i);
                String[] splitLine = currentLine.split(",");
                
                if(x.getUsername().equals(splitLine[0])){
                    splitLine[5] = newPhone;
                    String updatedLine = String.join(",", splitLine);
                    lines.set(i, updatedLine);
                }
                 
            }
            
            int counter=0;
            //writing back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                for (String l : lines) {
                    if(counter==0) bw.write(l);
                    else bw.write("\n"+l);
                    counter++;
                }
                x.setPhone(newPhone);
            }
            
        } 
        
        
        catch (Exception e) {
            ;
        }
    }

    public static void suspend(int uid, boolean suspended){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
        String id = String.valueOf(uid);
        String status = "active";
        if(suspended) status = "suspended";
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
            //finding the line to edit
            for(int i=0; i<lines.size(); i++){
                String currentLine = lines.get(i);
                String[] splitLine = currentLine.split(",");
                
                //if empty line then we skip
                if(currentLine.length() == 0){
                    continue;
                }
                
                if(id.equals(splitLine[2])){
                    splitLine[10] = status;
                    String updatedLine = String.join(",", splitLine);
                    lines.set(i, updatedLine);
                }
                 
            }
            
            int counter = 0;
            //writing back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
                for (String l : lines) {
                    if(counter==0) bw.write(l);
                    else bw.write("\n"+l);
                    counter++;
                }
            }
            
        } 
        
        
        catch (Exception e) {
            ;
        }
    }
    
    public static String[] getUserDetails(int uid){
        
        String line;
        
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){
            while((line = br.readLine()) != null){
                if(line.length() == 0){
                    continue;
                }
                String[] lineSplit = line.split(",");
                lineSplit[6] = formatAddress(lineSplit[6], false);
                if(lineSplit[2].equals(String.valueOf(uid))) return lineSplit;
            }
        }
        catch(Exception e){
            ;
        }
        
        return null;
    }
    
    public static String formatAddress(String address, boolean mode){
        
        if(mode){
            // if true we format
            //replace the , in address with ;
            return address.replace(",", ";");
        }
        
        else{
            //if false we unformat
            //replace the ; in address with ,
            return address.replace(";", ",");
        }
        
        
    }
    
}
