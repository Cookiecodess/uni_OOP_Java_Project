package mypackage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//THis Entire Class is used to do orders.csv crap 
public class OrderStorage {
    private static final String FILE_PATH = "orders.csv";
    private static final String HEADER = "OrderID,UserID,ProductID,Quantity,Subtotal,PaymentMethod,Status,GrandTotal,OrderDate";

    
    public static void saveOrder(Order order) throws IOException {
        File file = new File(FILE_PATH);
        boolean fileExists = file.exists();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Write header if new file
            if (!fileExists) {
                writer.write(HEADER);
                writer.newLine();
            }
            
            // Write each order item
            for (OrderItem item : order.getItems()) {
                writer.write(String.format("%s,%d,%d,%d,%.2f,%s,%s,%.2f,%s",
                    order.getOrderId(),
                    order.getUserId(),
                    item.getProduct().getId(),
                    item.getQuantity(),
                    item.getSubtotal(),
                    order.getPaymentMethod(),
                    order.getStatus(),
                    order.getGrandTotal(),
                    order.getFormattedOrderDate()));
                writer.newLine();
            }
        }
    }

    public static List<Order> loadOrdersForUser(int userId) throws IOException {
        List<Order> orders = new ArrayList<>();
        Map<String, Order> orderMap = new HashMap<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return orders;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Skip header
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 9) continue;

                int orderUserId = Integer.parseInt(parts[1]);
                if (orderUserId != userId) continue;

                String orderId = parts[0];
                Order order = orderMap.get(orderId);
                
                if (order == null) {
                    order = new Order(orderUserId);
                    order.setOrderId(orderId);
                    order.setPaymentMethod(parts[5]);
                    order.setStatus(parts[6]);
                    order.setGrandTotal(Double.parseDouble(parts[7]));
                    order.setOrderDate(LocalDateTime.parse(parts[8], 
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    orderMap.put(orderId, order);
                }

                Product product = Main.inventory.getProductById(Integer.parseInt(parts[2]));
                if (product != null) {
                    order.addItem(product, Integer.parseInt(parts[3]));
                }
            }
        }
        
        orders.addAll(orderMap.values());
        // Sort by date (newest first)
        orders.sort((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()));
        return orders;
    }

    public static List<Order> loadOrdersForAll() throws IOException {
        List<Order> orders = new ArrayList<>();
        Map<String, Order> orderMap = new HashMap<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) return orders;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Skip header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 9) continue;

                String orderId = parts[0];
                Order order = orderMap.get(orderId);

                if (order == null) {
                    order = new Order(Integer.parseInt(parts[1]));
                    order.setOrderId(orderId);
                    order.setPaymentMethod(parts[5]);
                    order.setStatus(parts[6]);
                    order.setGrandTotal(Double.parseDouble(parts[7]));
                    order.setOrderDate(LocalDateTime.parse(parts[8], 
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    orderMap.put(orderId, order);
                }

                Product product = Main.inventory.getProductById(Integer.parseInt(parts[2]));
                if (product != null) {
                    order.addItem(product, Integer.parseInt(parts[3]));
                }
            }
        }

        orders.addAll(orderMap.values());
        return orders;
    }
}