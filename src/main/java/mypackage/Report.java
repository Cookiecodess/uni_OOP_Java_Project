/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mypackage;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author songl
 */
public class Report {
    
    
      public void generateSalesReport(List<Order> orderList, List<Product> productList, LocalDate startDate, LocalDate endDate) {
          JLineMenu.clearScreen();
          
          JLineMenu.printHeader("Report",40);
        System.out.println(JLineMenu.BLUE+JLineMenu.BOLD+JLineMenu.UNDERLINE+"From " + startDate + " to " + endDate+JLineMenu.RESET);
            System.out.println();
  if (orderList == null || productList == null) {
            System.out.println("No order or no product.");
            return;
        }
  
      Map<Product, Integer> productSalesMap = new HashMap<>();
      Map<Product, Double> productRevenueMap = new HashMap<>();
      double totalRevenue = 0;
        //product  integer=total salse quantity
        //product a   10
        //product b   200
        // new HashMap<>(): hash map use to save the data
        //Product mouse = new Product("P001", "keyboard1");
         // Product keyboard = new Product("P002", "keyboard2");
            //productSalesMap.put(keyboard1, 5);     // sold 5 
            //productSalesMap.put(keyboard2, 3);  // sold 3 
   //calculate sales data
        for (Product product : productList) {
            int totalSales = 0;
             double totalAmount = 0;
            for (Order order : orderList) {
                if (!order.getStatus().equalsIgnoreCase("Completed")) continue;
                        // only consider the product between start date end date
                        //convert order's LocalDateTime to LocalDate
                LocalDate orderDate = order.getOrderDate().toLocalDate();
                if ((orderDate.isEqual(startDate) || orderDate.isAfter(startDate)) &&
                    (orderDate.isEqual(endDate) || orderDate.isBefore(endDate))) {

                    // calculate the product sales quantity
                    for (OrderItem item : order.getItems()) {
                        if (item.getProduct().equals(product)) {
                            totalSales += item.getQuantity();
                            totalAmount += item.getQuantity() * item.getProduct().getPrice();
                        }
                    }
                }
            }
             productSalesMap.put(product, totalSales);
             productRevenueMap.put(product, totalAmount);
              totalRevenue += totalAmount;
        }
         List<Map.Entry<Product, Integer>> sortedSalesList = productSalesMap.entrySet().stream()
            .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
            .collect(Collectors.toList());

           System.out.printf("%-5s %-5s %-45s %-15s %-15s%n", "Rank","ID", "Product Name", "Quantity", "Sales (RM)");//%-50s means 50 space

        int rank = 1;
        for (Map.Entry<Product, Integer> entry : sortedSalesList) { //loop the product that already rank, and get the sales quantity
         Product product = entry.getKey();//get current product
        int qty = entry.getValue(); //get this product sales
        double revenue = productRevenueMap.get(product);    //sales=quantity*amount
        System.out.printf("%-5d %-5d %-45s %-15d RM %-12.2f%n", rank++,product.getId(), product.getName(), qty, revenue);
        }
        
        
            System.out.println("---------------------------------------------------------------------------------------");
          System.out.printf(JLineMenu.GREEN + "%-70s RM %.2f\n" + JLineMenu.RESET, "Total Sales Revenue:", totalRevenue);
    }
    
      
      
      public void generateUserRanking(List<Order> orderList) {
    // user ID -> total spend
      JLineMenu.clearScreen();
    Map<Integer, Double> userSpendingMap = new HashMap<>();

    for (Order order : orderList) {
       if (!order.getStatus().equalsIgnoreCase("Completed")) continue;

        int userId = order.getUserId();
        double amount = order.getGrandTotal();

        userSpendingMap.put(userId, userSpendingMap.getOrDefault(userId, 0.0) + amount);
    }

    // sort money
    List<Map.Entry<Integer, Double>> sortedUsers = userSpendingMap.entrySet()
        .stream()
        .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
        .toList();
  JLineMenu.printHeader("Customer Ranking",20);
    System.out.printf("%-10s %-15s %-25s%n","Rank", "User ID", "Total Spending");
    int rank = 1;
    for (Map.Entry<Integer, Double> entry : sortedUsers) {
        System.out.printf("%-10d %-20d RM%-25.2f%n",rank++, entry.getKey(), entry.getValue());
    }
}

      
}
