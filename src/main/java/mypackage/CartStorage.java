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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(HEADER + "\n");
            productIdToQuantity.forEach((prodId, qty) -> {
                try {
                    writer.write(String.format("%d,%d,%d\n", userId, prodId, qty));
                } catch (IOException e) {
                    System.err.println("Failed to write cart item: " + e.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to save cart: " + e.getMessage());
        }
    }

    // Returns Map<ProductID, Quantity>
    public static Map<Integer, Integer> loadCart(int userId) {
        Map<Integer, Integer> cart = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine(); // Skip header
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
}