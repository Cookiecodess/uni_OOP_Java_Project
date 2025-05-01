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
            for (Order order : orderList) {
               // if (!order.getStatus().equalsIgnoreCase("Complete")) continue;
                        // only consider the product between start date end date
                        //convert order's LocalDateTime to LocalDate
                LocalDate orderDate = order.getOrderDate().toLocalDate();
                if ((orderDate.isEqual(startDate) || orderDate.isAfter(startDate)) &&
                    (orderDate.isEqual(endDate) || orderDate.isBefore(endDate))) {

                    // 统计该产品的销售数量
                    for (OrderItem item : order.getItems()) {
                        if (item.getProduct().equals(product)) {
                            totalSales += item.getQuantity();
                        }
                    }
                }
            }
             productSalesMap.put(product, totalSales);
           // System.out.printf("%-50s %-10s%n",product.getName() ,totalSales);
        }
         List<Map.Entry<Product, Integer>> sortedSalesList = productSalesMap.entrySet().stream()
            .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
            .collect(Collectors.toList());

        System.out.printf("%-5s %-45s %-10s%n", "Rank", "Product Name", "Sales Quantity");//%-50s means 50 space

        int rank = 1;
        for (Map.Entry<Product, Integer> entry : sortedSalesList) {
            System.out.printf("%-5d %-50s %-10d%n", rank++, entry.getKey().getName(), entry.getValue());
        }
    }
    
}
