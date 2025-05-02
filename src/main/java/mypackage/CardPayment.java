/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author songl
 */
public class CardPayment extends Payment{

    private final String cardType;
        
    public CardPayment(Order order,String cardType) {
        super("Card", order);
        this.cardType=cardType;
    }

         @Override
    public boolean validation(){
        Scanner scanner = new Scanner(System.in);
        JLineMenu.printHeader("Card Payment",30);
          
        System.out.print("Enter your Card Number (**** **** **** ****): ");
        String cNInput=scanner.nextLine();
    
        System.out.print("Enter your Card Expire Date (exp: 01/30) : ");
        String eDinput=scanner.nextLine();
    
        System.out.print("Enter your CVV : ");
        String cvvInput=scanner.nextLine();
        cNInput = cNInput.replace(" ","");
        eDinput = eDinput.strip();
        String[] expDate = eDinput.split("/");
        if (cNInput.isEmpty() || eDinput.isEmpty()||cvvInput.isEmpty()) {
        System.out.println(JLineMenu.RED+"Username and password cannot be empty."+JLineMenu.RESET);
        return false;      
        }
        
        else if(!cNInput.matches("^[245]\\d{15}$") || ( Integer.parseInt(expDate[0]) < 6 &&  Integer.parseInt(expDate[1]) == 25) ||  Integer.parseInt(expDate[1]) < 25 ){
            return false;
        }
        
//        System.out.println( cNInput.compareTo(CARD_NUMBER)==0);
//        System.out.println( eDinput.compareTo(EXPIRE_DATE)==0);
//        System.out.println( cvvInput.compareTo(CVV)==0);
    return true;
    }
    
     @Override
     public void generateQR(){
     
     throw new UnsupportedOperationException("Not supported for card payment");
     }
    
          @Override
     public String failMessage(){
     return JLineMenu.RED+"Sorry your card detail is invalid"+JLineMenu.RESET;

     }
     @Override
         public void generateReceipt(Order order){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//to set the format of the date and time
        System.out.println("Order ID\t: "+order.getOrderId());
         System.out.println("Payment Method\t: "+getPaymentMethod());
          System.out.println("Card Type\t: "+cardType);
        System.out.println("Amount\t\t: RM "+JLineMenu.GREEN+ String.format("%.2f", order.getGrandTotal())+JLineMenu.RESET);
         System.out.println("Date\t\t: "+JLineMenu.GREEN+getDateTime().format(formatter)+JLineMenu.RESET);
                   System.out.println("-------------------------------------------------------------------\n");

            System.out.println("Tax\t\t: RM "+JLineMenu.GREEN+ String.format("%.2f", order.getTax())+JLineMenu.RESET);
            System.out.println("Delivery Fee\t: "+order.getDeliveryFee());
         System.out.println("-------------------------------------------------------------------\n");

        for (OrderItem item : order.getItems()) {
            System.out.printf("%-30s %-10d RM%-8.2f%n",item.getProduct().getName(),item.getQuantity(),item.getSubtotal());
        }
      
         System.out.println("===================================================================");
         JLineMenu.waitMsg();
    };
 @Override
public void generatePrintableReceipt(Order order){
        try {
                    // create file
                    File receiptFile = new File("Receipt.txt");
                    if (receiptFile.createNewFile()) {
                       //success create
                    }

            try ( // write to file
                    FileWriter writeReceipt = new FileWriter("Receipt.txt")) 
            {
                                writeReceipt.write("============================================\n");
                writeReceipt.write("\t\tRECEIPT\n");
                writeReceipt.write("============================================\n");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//to set the format of the date and time
                writeReceipt.write("Order ID\t: "+order.getOrderId()+"\n");
                writeReceipt.write("Payment Method\t: "+getPaymentMethod()+"\n");
                   writeReceipt.write("Card Type\t\t: "+cardType+"\n");
                writeReceipt.write("Amount\t\t: RM "+ String.format("%.2f", order.getGrandTotal())+"\n");
                writeReceipt.write("Date\t\t: "+getDateTime().format(formatter)+"\n");
       
                  writeReceipt.write("============================================\n");
                  
                           writeReceipt.write("Tax\t\t: RM "+ String.format("%.2f", order.getTax())+"\n");
    
                 writeReceipt.write("Delivery Fee\t: "+order.getDeliveryFee()+"\n");
        writeReceipt.write("============================================\n");

        for (OrderItem item : order.getItems()) {
            writeReceipt.write(item.getProduct().getName()+"\t"+item.getQuantity()+"\t"+item.getSubtotal()+"\n");
        }
         writeReceipt.write("============================================\n");
            }

                    System.out.println(JLineMenu.GREEN+"The receipt is generate successfully!"+JLineMenu.RESET);
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }



           };
}





