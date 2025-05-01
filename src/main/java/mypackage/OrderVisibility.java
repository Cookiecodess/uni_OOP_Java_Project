/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

/**
 *
 * @author Acer
 */
import java.io.*;
import java.util.*;

//THIS ENTIRE CLASS MANAGERS ORDER VISIBILITY FOR ORDER STATUS
public class OrderVisibility {
    private static final String FILE_PATH = "order_visibility.csv";
    private static final String HEADER = "OrderID,UserID,Visibility";
    private static final Map<String, String> visibilityMap = new HashMap<>(); // OrderID -> "show"/"hide"

    static {
        initializeFile();
        loadVisibility();
    }

    private static void initializeFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(HEADER + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize visibility file: " + e.getMessage());
        }
    }

    private static void loadVisibility() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    visibilityMap.put(parts[0], parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load visibility settings");
        }
    }

    public static void setVisible(String orderId, int userId) throws IOException {
        visibilityMap.put(orderId, "show");
        appendToFile(orderId, userId, "show");
    }

    public static void hideOrder(String orderId, int userId) throws IOException {
        visibilityMap.put(orderId, "hide");
        rewriteFile(); // Full rewrite to handle updates
    }

    public static boolean isVisible(String orderId) {
        return !"hide".equals(visibilityMap.get(orderId));
    }

    private static void appendToFile(String orderId, int userId, String visibility) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.format("%s,%d,%s\n", orderId, userId, visibility));
        }
    }

    private static void rewriteFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(HEADER + "\n");
            for (Map.Entry<String, String> entry : visibilityMap.entrySet()) {
                // We'd need to track UserIDs - see note below
                writer.write(String.format("%s,%d,%s\n", 
                    entry.getKey(), 
                    getUserIdForOrder(entry.getKey()), 
                    entry.getValue()));
            }
        }
    }

    private static int getUserIdForOrder(String orderId) throws IOException {
        // Query orders.csv to find UserID for this order
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[0].equals(orderId)) {
                    return Integer.parseInt(parts[1]); // UserID is 2nd column
                }
            }
        }
        throw new IOException("Order not found: " + orderId);
    }
}
