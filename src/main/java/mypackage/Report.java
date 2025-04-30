/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mypackage;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author songl
 */
public class Report {
      public void generateSalesReport(List<Order> orderList, List<Product> productList, LocalDate startDate, LocalDate endDate) {
        System.out.println("Report " + startDate + " to " + endDate);

        // 统计产品的销售数量
        for (Product product : productList) {
            int totalSales = 0;
/////////////要有ifstatus is complete？
            for (Order order : orderList) {
                // 只考虑在给定日期范围内的订单
                LocalDate orderDate = order.getOrderDate();
                if ((orderDate.isEqual(startDate) || orderDate.isAfter(startDate)) &&
                    (orderDate.isEqual(endDate) || orderDate.isBefore(endDate))) {

                    // 统计该产品的销售数量
//                    for (OrderItem item : order.getOrderItems()) {
//                        if (item.getProduct().equals(product)) {
//                            totalSales += item.getQuantity();
//                        }
                    }
                }
            }

            //System.out.println("产品 " + product.getName() + " 销售数量: " + totalSales);
        }
    }

