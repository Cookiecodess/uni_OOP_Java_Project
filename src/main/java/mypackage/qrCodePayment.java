/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
public class qrCodePayment extends Payment{
         private static final String CONFIRM_MSG="CONFIRM";
    
    
            public qrCodePayment(Order order) {
               super("QR code Payment", order);
    }
@Override    
    public void generateQR(){
        try {
      File myObj = new File("qrcode.txt");
            try (Scanner qrOutput = new Scanner(myObj)) {
                while (qrOutput.hasNextLine()) {
                    String data = qrOutput.nextLine();
                    System.out.println(data);
                }     }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    

    }

    }

            @Override
public boolean validation() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter 'CONFIRM' the payment  : ");
            String confirminput=scanner.nextLine();
     return CONFIRM_MSG.equalsIgnoreCase(confirminput);
}


@Override
     public String failMessage(){
     return JLineMenu.RED+"Sorry, please try again..."+JLineMenu.RESET;
}
@Override
    public void generateReceipt(Order order){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//to set the format of the date and time
         System.out.println("Order ID\t: "+order.getOrderId());
         System.out.println("Payment Method\t: "+getPaymentMethod());
        System.out.println("Amount\t\t: RM "+JLineMenu.GREEN+ String.format("%.2f", order.getGrandTotal())+JLineMenu.RESET);
         System.out.println("Date\t\t: "+JLineMenu.GREEN+getDateTime().format(formatter)+JLineMenu.RESET);
                   System.out.println("-------------------------------------------------------------------\n");
                    System.out.println("Tax\t\t: RM "+JLineMenu.GREEN+ String.format("%.2f", order.getTax())+JLineMenu.RESET);
            System.out.println("Delivery Fee\t: RM "+JLineMenu.GREEN+order.getDeliveryFee()+JLineMenu.RESET);
            
          System.out.println("-------------------------------------------------------------------\n");

        for (OrderItem item : order.getItems()) {
            System.out.printf("%-45s %-10d RM%-8.2f%n",item.getProduct().getName(),item.getQuantity(),item.getSubtotal());
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
        writeReceipt.write("Amount\t\t: RM "+ String.format("%.2f", order.getGrandTotal())+"\n");
        writeReceipt.write("Date\t\t: "+getDateTime().format(formatter)+"\n");
                          writeReceipt.write("============================================\n");
                  writeReceipt.write("Tax\t\t: RM "+ String.format("%.2f", order.getTax())+"\n");
    
                 writeReceipt.write("Delivery Fee\t: RM "+order.getDeliveryFee()+"\n");
        writeReceipt.write("============================================\n");

        for (OrderItem item : order.getItems()) {
           writeReceipt.write(item.getProduct().getName()+"\n");
            writeReceipt.write("\t\t\t* "+item.getQuantity()+"\t"+item.getSubtotal()+"\n");
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