/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class qrCodePayment extends Payment{
         private static final String CONFIRM_MSG="CONFIRM";
    
    
            public qrCodePayment(double amount, Order order) {
               super("QR code Payment", amount, order);
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
     return "Sorry, please try again...";
}
@Override
    public void generateReceipt(Order order){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//to set the format of the date and time
         System.out.println("Order ID\t: "+order.getOrderId());
         System.out.println("Payment Method\t: "+getPaymentMethod());
        System.out.println("Amount\t\t: RM "+JLineMenu.GREEN+ String.format("%.2f", getAmount())+JLineMenu.RESET);
         System.out.println("Date\t\t: "+JLineMenu.GREEN+getDateTime().format(formatter)+JLineMenu.RESET);
         System.out.println("=============================================");
         JLineMenu.waitMsg();
   
    };


}