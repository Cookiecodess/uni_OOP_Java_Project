/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.io.*;
import java.util.*;

public class CartStorage {
    private static final String FILE_PATH = "carts.csv";
    private static final String HEADER = "UID,ProductID,Quantity";

    // Changed parameter type from Map<Product,Integer> to Map<Integer,Integer>
    public static void saveCart(int userId, Map<Integer, Integer> productIdToQuantity) {
        
        //load all existing carts
        Map<Integer, Map<Integer, Integer>> allCarts = loadAllCarts();
        
        //update current user cart
        allCarts.put(userId, new HashMap<>(productIdToQuantity));
     
        //save all carts back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(HEADER + "\n");
            allCarts.forEach((uid, cart) -> {
                cart.forEach((prodId, qty) -> {
                    try {
                        writer.write(String.format("%d,%d,%d\n", uid, prodId, qty));
                    } catch (IOException e) {
                        System.err.println("Failed to save cart: " + e.getMessage());
                    }
                });
            });
        }  catch (IOException e) {
            System.err.println("Failed to save cart: " + e.getMessage());
        }
    }

    // Load single user cart 
    public static Map<Integer, Integer> loadCart(int userId) {
        Map<Integer, Integer> cart = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && Integer.parseInt(parts[0]) == userId) {
                    cart.put(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                }
            }
        } catch (IOException e) {
            // File not found is expected on first run
        }
        return cart;
    }
    
    private static Map<Integer, Map<Integer, Integer>> loadAllCarts() {
        Map<Integer, Map<Integer, Integer>> allCarts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int uid = Integer.parseInt(parts[0]);
                    int pid = Integer.parseInt(parts[1]);
                    int qty = Integer.parseInt(parts[2]);
                    allCarts.computeIfAbsent(uid, k -> new HashMap<>()).put(pid, qty);
                }
            }
        } catch (IOException e) {
            // File not found = empty map
        }
        return allCarts;
    }
}